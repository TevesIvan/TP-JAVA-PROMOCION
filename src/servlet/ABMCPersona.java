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
import entidades.Categoria;
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
			try {
				pers=ctrl.getByDni(p);
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
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
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("dni").equals("") || request.getParameter("apellido").equals("") || request.getParameter("nombre").equals("") || request.getParameter("pass").equals("") || request.getParameter("user").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "No debe haber campos vacíos.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			Persona p=new Persona();
			Categoria c=new Categoria();
			CrtlABMCPersona ctrl= new CrtlABMCPersona();
			request.setAttribute("accion", "insertar");
			c.setNombre(request.getParameter("categoria"));
			p.setDni(request.getParameter("dni"));
			p.setApellido(request.getParameter("apellido"));
			p.setNombre(request.getParameter("nombre"));
			try {
				p.setCategoria(ctrl.getByNombre(c));
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			p.setContraseña(request.getParameter("pass"));
			if(request.getParameter("habilitado").equals("si"))
			{
				p.setHabilitado(true);
			}
			else
			{
				p.setHabilitado(false);
			}
			p.setUsuario(request.getParameter("user"));
			try {
				ctrl.add(p);
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionPersona.jsp");
			dispatcher.forward(request,response);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//response.getWriter().append("Eliminar, requested action: ").append(request.getPathInfo()).append(" through post");
		Persona p=new Persona();
		CrtlABMCPersona ctrl= new CrtlABMCPersona();
		request.setAttribute("accion", "eliminar");
		p.setDni(request.getParameter("dni"));
		try {
			ctrl.delete(p);
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionPersona.jsp");
		dispatcher.forward(request,response);
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//response.getWriter().append("Modificar, requested action: ").append(request.getPathInfo()).append(" through post");
		if(request.getParameter("dni").equals("") || request.getParameter("apellido").equals("") || request.getParameter("nombre").equals("") || request.getParameter("pass").equals("") || request.getParameter("user").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "No debe haber campos vacíos.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			Persona p=new Persona();
			Categoria c=new Categoria();
			CrtlABMCPersona ctrl= new CrtlABMCPersona();
			request.setAttribute("accion", "modificar");
			c.setNombre(request.getParameter("categoria"));
			p.setDni(request.getParameter("dni"));
			p.setApellido(request.getParameter("apellido"));
			p.setNombre(request.getParameter("nombre"));
			try {
				p.setCategoria(ctrl.getByNombre(c));
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			p.setContraseña(request.getParameter("pass"));
			if(request.getParameter("habilitado").equals("si"))
			{
				p.setHabilitado(true);
			}
			else
			{
				p.setHabilitado(false);
			}
			p.setUsuario(request.getParameter("user"));
			try {
				ctrl.update(p);
			} catch (Exception e) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionPersona.jsp");
			dispatcher.forward(request,response);
		}
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(404);
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "La pagina solicitada no fue encontrada");
	}
}
