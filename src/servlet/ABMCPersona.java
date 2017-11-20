package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		//response.getWriter().append("Buscar, requested action: ").append(request.getPathInfo()).append(" through post");
		Persona p=new Persona();
		//System.out.println(request.getParameter("dni"));
		p.setDni(request.getParameter("dni"));
		CrtlABMCPersona ctrl= new CrtlABMCPersona();
		try {
			p=ctrl.getByDni(p);
			response.getWriter().append("Persona encontrada: ").append(p.getNombre());
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Persona no encontrada");
		}
		//request.getRequestDispatcher("WEB-INF/ABMCPersona.jsp").forward(request, response);
/*		if(p==null)
		{
			request.setAttribute("url", "WEB-INF/ABMCPersona.jsp");
			request.setAttribute("error", "Persona no encontrada");
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		else*/
	//	{
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
