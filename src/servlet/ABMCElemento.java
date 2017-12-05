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
import controlador.CtrlTipoElemento;
import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.TipoElemento;

/**
 * Servlet implementation class ABMCElemento
 */
@WebServlet({"/elementos/*", "/Elementos/*", "/ELEMENTOS/*"})
public class ABMCElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCElemento() {
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
			Elemento e=new Elemento();
			request.setAttribute("accion", "buscar");
			e.setId(Integer.parseInt(request.getParameter("id")));
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			Elemento ele=new Elemento();
				try {
					ele=ctrl.getById(e);
				} catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				if(ele==null)
				{
					request.setAttribute("url", "start");
					request.setAttribute("error", "Elemento no encontrado");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
					dispatcher.forward(request,response);
				}
				else
				{
					request.setAttribute("elemento", ele);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionElemento.jsp");
					dispatcher.forward(request,response);
				}
		}
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("nombre").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "El campo 'nombre' no debe estar vacio.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			Elemento e=new Elemento();
			TipoElemento t=new TipoElemento();
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			CtrlTipoElemento ctrlTip= new CtrlTipoElemento();
			request.setAttribute("accion", "insertar");
			t.setNombre(request.getParameter("tipo"));
			e.setNombre(request.getParameter("nombre"));
			try {
				e.setTipoElemento(ctrlTip.getByNombre(t));
			} catch (Exception ex) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			try {
				ctrl.add(e);
			} catch (Exception ex) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionElemento.jsp");
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
			Elemento e=new Elemento();
			Elemento ele=new Elemento();
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			request.setAttribute("accion", "eliminar");
			e.setId(Integer.parseInt(request.getParameter("id")));
			try {
				ele=ctrl.getById(e);
			} catch (Exception e1) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			if(ele==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Elemento no encontrado.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				try {
					ctrl.delete(e);
				} catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionElemento.jsp");
				dispatcher.forward(request,response);
			}	
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(request.getParameter("id").equals("") || request.getParameter("nombre").equals(""))
		{
			request.setAttribute("url", "start");
			request.setAttribute("error", "No debe haber campos vacíos.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		else
		{
			Elemento e=new Elemento();
			Elemento ele=new Elemento();
			TipoElemento t=new TipoElemento();
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			CtrlTipoElemento ctrlTip= new CtrlTipoElemento();
			request.setAttribute("accion", "modificar");
			e.setNombre(request.getParameter("nombre"));
			e.setId(Integer.parseInt(request.getParameter("id")));
			t.setNombre(request.getParameter("tipo"));
			try {
				e.setTipoElemento(ctrlTip.getByNombre(t));
			} catch (Exception ex) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			try {
				ele=ctrl.getById(e);
			} catch (Exception e1) {
				response.setStatus(502);
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
			}
			if(ele==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Elemento no encontrado.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				try {
					ctrl.update(e);
				} catch (Exception ex) {
					response.setStatus(502);
					response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionElemento.jsp");
				dispatcher.forward(request,response);
			}	
		}
	}

}
