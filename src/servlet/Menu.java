package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.CrtlABMCPersona;
import controlador.CtrlABMCElemento;
import controlador.CtrlReserva;
import controlador.CtrlTipoElemento;
import entidades.Persona;

/**
 * Servlet implementation class Menu
 */
@WebServlet({"/menu/*", "/Menu/*", "/MENU/*"})
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/personas":
			this.personas(request,response);
			break;
		
		case "/elementos":
			this.elementos(request,response);
			break;
		
		case "/tipos":
			this.tipos(request,response);
			break;
			
		case "/reservas":
			this.reservas(request,response);
			break;
			
		case "/salir":
			this.salir(request,response);
			break;
			
		default:
			this.error(request,response);
			break;
		}
	}
	private void personas(HttpServletRequest request, HttpServletResponse response) throws IOException{
		CrtlABMCPersona ctrl= new CrtlABMCPersona();
		try {
			request.setAttribute("listaPer", ctrl.getAll());
			request.setAttribute("listaCat", ctrl.getCategorias());
		} catch (Exception e1) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");			
			}
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/ABMCPersona.jsp");
			  dispatcher.forward(request,response);
		} catch (ServletException e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
	}
	
	private void elementos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CtrlABMCElemento ctrl=new CtrlABMCElemento();
		try {
			request.setAttribute("listaEle", ctrl.getAll());
			request.setAttribute("listaTip", ctrl.getAllTipoElemento());
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/ABMCElemento.jsp");
			  dispatcher.forward(request,response);
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
	}
	
	private void tipos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtrlTipoElemento ctrl=new CtrlTipoElemento();
		try {
			request.setAttribute("listaTip", ctrl.getAll());
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/ABMCTipoElemento.jsp");
			  dispatcher.forward(request,response);
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
	}
	
	private void reservas(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtrlReserva ctrl=new CtrlReserva();
		try {
			request.setAttribute("listaRes", ctrl.getAllUsuario((Persona) request.getSession().getAttribute("user")));
			request.setAttribute("listaTip", ctrl.getTiposElementos((Persona) request.getSession().getAttribute("user")));
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Reservas.jsp");
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
		}
	}
	
	private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("/TP_JAVA_WEB/");
		return;
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(404);
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "La pagina solicitada no fue encontrada");
	}
}
