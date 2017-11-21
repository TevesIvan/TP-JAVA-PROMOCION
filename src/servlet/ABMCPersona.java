package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.CrtlABMCPersona;
import entidades.Persona;

/**
 * Servlet implementation class ABMCPersona
 */
@WebServlet({"/personas/*", "/Personas/*", "/PERSONAS/*"})
public class ABMCPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCPersona() {
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
			
		default:
			this.error(request, response);
			break;
		}
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Persona p=new Persona();
		request.setAttribute("accion", "buscar");
		p.setDni(request.getParameter("dni"));
		CrtlABMCPersona ctrl= new CrtlABMCPersona();
		Persona pers=new Persona();
	//	try {
			try {
				pers=ctrl.getByDni(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pers==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Persona no encontrada");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				request.setAttribute("persona", pers);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionPersona.jsp");
				dispatcher.forward(request,response);
			}
	//	}
	//	catch (Exception e) {
	//		response.setStatus(502);
	//		response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
	//	}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().append("Insertar, requested action: ").append(request.getPathInfo()).append(" through post");
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().append("Eliminar, requested action: ").append(request.getPathInfo()).append(" through post");
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().append("Modificar, requested action: ").append(request.getPathInfo()).append(" through post");
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(404);
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "La pagina solicitada no fue encontrada");
	}
}
