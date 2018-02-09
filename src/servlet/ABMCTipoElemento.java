package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
import entidades.Elemento;
import entidades.Persona;
import entidades.TipoElemento;

/**
 * Servlet implementation class ABMCTipoElemento
 */
@WebServlet({"/tiposElementos/*", "/TiposElementos/*", "/TIPOSELEMENTOS/*"})
public class ABMCTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCTipoElemento() {
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
		switch (request.getPathInfo()){
		case "/buscar":
			this.buscar(request, response);
			break;
		
		case "/insertar":
			this.insertar(request, response);
			break;
			
		case "/eliminar":
			this.eliminar(request, response);
			break;
			
		case "/modificar":
			this.modificar(request, response);
			break;
		
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
			this.error(request, response);
			break;
		}
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(404);
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "La pagina solicitada no fue encontrada");
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("id").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "El campo 'ID' no debe estar vacio.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			TipoElemento t=new TipoElemento();
			request.setAttribute("accion", "buscar");
			t.setId(Integer.parseInt(request.getParameter("id")));
			CtrlTipoElemento ctrl= new CtrlTipoElemento();
			TipoElemento tip=new TipoElemento();
				try {
					tip=ctrl.getById(t);
				} catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				if(tip==null)
				{
					request.setAttribute("url", "start");
					request.setAttribute("error", "Tipo de Elemento no encontrado");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
					dispatcher.forward(request,response);
				}
				else
				{
					request.setAttribute("tipoElemento", tip);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionTipoElemento.jsp");
					dispatcher.forward(request,response);
				}
		}
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("nombre").equals("")||request.getParameter("cantMax").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "Los campos 'nombre' y 'cantMax' no deben estar vacios.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			TipoElemento t=new TipoElemento();
			CtrlTipoElemento ctrl= new CtrlTipoElemento();
			request.setAttribute("accion", "insertar");
			t.setNombre(request.getParameter("nombre"));
			t.setCantMax(Integer.parseInt(request.getParameter("cantMax")));
			t.setMaxTiempo(Integer.parseInt(request.getParameter("maxTiempo")));
			t.setDiasAntMax(Integer.parseInt(request.getParameter("diasAntMax")));
			if(request.getParameter("encargado").equals("si"))
			{
				t.setEncargado(true);
			}
			else
			{
				t.setEncargado(false);
			}
			try {
				ctrl.add(t);
			} catch (Exception ex) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionTipoElemento.jsp");
			dispatcher.forward(request,response);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("id").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "El campo 'ID' no debe estar vacio.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			TipoElemento tip=new TipoElemento();
			TipoElemento t=new TipoElemento();
			CtrlTipoElemento ctrl= new CtrlTipoElemento();
			request.setAttribute("accion", "eliminar");
			t.setId(Integer.parseInt(request.getParameter("id")));
			try {
				tip=ctrl.getById(t);
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			if(tip==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Tipo de Elemento no encontrado.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				try {
					ctrl.delete(t);
				}catch (SQLException e)
				{
					request.setAttribute("url", "start");
					request.setAttribute("error", "Tipo de Elemento imposible de eliminar. Revise los elementos reservados del tipo a eliminar.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
					dispatcher.forward(request,response);
				}
				catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionTipoElemento.jsp");
				dispatcher.forward(request,response);
			}	
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("id").equals("") || request.getParameter("nombre").equals("")|| request.getParameter("cantMax").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "No debe haber campos vacíos.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			TipoElemento t=new TipoElemento();
			TipoElemento tip=new TipoElemento();
			CtrlTipoElemento ctrl= new CtrlTipoElemento();
			request.setAttribute("accion", "modificar");
			t.setNombre(request.getParameter("nombre"));
			t.setId(Integer.parseInt(request.getParameter("id")));
			t.setCantMax(Integer.parseInt(request.getParameter("cantMax")));
			t.setMaxTiempo(Integer.parseInt(request.getParameter("maxTiempo")));
			t.setDiasAntMax(Integer.parseInt(request.getParameter("diasAntMax")));
			if(request.getParameter("encargado").equals("si"))
			{
				t.setEncargado(true);
			}
			else
			{
				t.setEncargado(false);
			}
			try {
				tip=ctrl.getById(t);
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			if(tip==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Tipo de Elemento no encontrado.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				try {
					ctrl.update(t);
				} catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionTipoElemento.jsp");
				dispatcher.forward(request,response);
			}		
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

}
