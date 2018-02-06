package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
import entidades.Reserva;
import entidades.TipoElemento;

/**
 * Servlet implementation class Reserva
 */
@WebServlet({"/reservas/*", "/Reservas/*", "/RESERVAS/*"})
public class Reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservas() {
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
		switch(request.getPathInfo()){
		case "/buscar":
			this.buscar(request,response);
			break;
			
		case "/cancelar":
			this.cancelar(request,response);
			break;
			
		case "/reservar":
			this.reservar(request,response);
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
			this.error(request,response);
			break;
		}
		
		
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Reserva r=new Reserva();
		CtrlReserva ctrl=new CtrlReserva();
		CtrlTipoElemento ctrlEl=new CtrlTipoElemento();
		request.setAttribute("accion", "buscar");
		r.setElemento(new Elemento());
		r.getElemento().setTipoElemento(new TipoElemento());
		r.setDetalle(request.getParameter("detalle"));
		DateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault());
		try {
			r.setFechaHoraDesde(format.parse(request.getParameter("fechaHoraDesde")));
		} catch (ParseException e) {
			request.setAttribute("url", "start");
			request.setAttribute("error", "Formato incorrecto de Fecha y Hora ingresado");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		try {
			r.setFechaHoraHasta(format.parse(request.getParameter("fechaHoraHasta")));
		} catch (ParseException e) {
			request.setAttribute("url", "start");
			request.setAttribute("error", "Formato incorrecto de Fecha y Hora ingresado");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		if(r.getFechaHoraDesde().before(r.getFechaHoraReserva()) || r.getFechaHoraHasta().before(r.getFechaHoraReserva())){
			request.setAttribute("url", "start");
			request.setAttribute("error", "Fecha/s anteriores al día de hoy ingresada/s. Ingrese fechas futuras al día de hoy.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		r.setPersona((Persona) request.getSession().getAttribute("user"));
		r.getElemento().getTipoElemento().setNombre(request.getParameter("tipoElemento"));
		try {
			r.getElemento().setTipoElemento(ctrlEl.getByNombre(r.getElemento().getTipoElemento()));
		} catch (Exception e1) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		try {
			request.setAttribute("listaResDisp", ctrl.buscaElementosDisp(r));
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		request.getSession().setAttribute("reservaActual", r);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionReserva.jsp");
		dispatcher.forward(request,response);
	}
	
	private void reservar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Reserva r=new Reserva();
		Elemento e=new Elemento();
		CtrlReserva ctrl=new CtrlReserva();
		CtrlABMCElemento ctrlEl=new CtrlABMCElemento();
		request.setAttribute("accion", "reservar");
		r=(Reserva) request.getSession().getAttribute("reservaActual");
		e.setId(Integer.parseInt(request.getParameter("id")));
		try {
			e=ctrlEl.getById(e);
		} catch (Exception e1) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		r.setElemento(e);
		try {
			ctrl.registrarReserva(r);
		} catch (Exception e1) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionReserva.jsp");
		dispatcher.forward(request,response);
	}
	
	private void cancelar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Reserva r=new Reserva();
		CtrlReserva ctrl=new CtrlReserva();
		request.setAttribute("accion", "cancelar");
		try {
			r.setId(Integer.parseInt(request.getParameter("id")));
		} catch (NumberFormatException e2) {
			request.setAttribute("url", "start");
			request.setAttribute("error", "Formato incorrecto. ID debe ser numérico.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request,response);
		}
		try {
			if(ctrl.getById(r)==null)
			{
				request.setAttribute("url", "start");
				request.setAttribute("error", "Reserva no encontrada.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request,response);
			}
		} catch (Exception e1) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		try {
			ctrl.cancelarReserva(r);
		} catch (Exception e) {
			response.setStatus(502);
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Error de servidor");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AccionReserva.jsp");
		dispatcher.forward(request,response);
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
