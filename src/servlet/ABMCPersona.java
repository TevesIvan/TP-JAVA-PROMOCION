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
@WebServlet("/ABMCPersona")
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
		// TODO Auto-generated method stub
	//	if("Buscar".equals(request.getParameter("accion")))
	//	{
			Persona p=new Persona();
			System.out.println(request.getParameter("dni"));
			p.setDni(request.getParameter("dni"));
			CrtlABMCPersona ctrl= new CrtlABMCPersona();
			try {
				request.setAttribute("listaPer", ctrl.getAll());
			} catch (Exception e1) {
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");			}
			try {
				p=ctrl.getByDni(p);
				System.out.println(p.getNombre());
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de Servidor");
			}
			request.getSession().setAttribute("per",p);
			request.getRequestDispatcher("WEB-INF/ABMCPersona.jsp").forward(request, response);
	//	}
	}

}
