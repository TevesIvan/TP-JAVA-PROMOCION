package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.CtrlABMCElemento;
import controlador.CtrlTipoElemento;
import entidades.Elemento;
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
				} catch (Exception ex) {
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

}
