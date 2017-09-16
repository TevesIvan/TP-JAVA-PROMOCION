package entidades;
import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;

public class Reserva implements Serializable{
	public java.util.Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(java.util.Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Date getFechaHoraReserva() {
		return fechaHoraReserva;
	}

	public void setFechaHoraReserva(Date fechaHoraReserva) {
		this.fechaHoraReserva = fechaHoraReserva;
	}

	public Date getFechaHoraDesde() {
		return fechaHoraDesde;
	}

	public void setFechaHoraDesde(Date fechaHoraDesde) {
		this.fechaHoraDesde = fechaHoraDesde;
	}

	public Date getFechaHoraHasta() {
		return fechaHoraHasta;
	}

	public void setFechaHoraHasta(Date fechaHoraHasta) {
		this.fechaHoraHasta = fechaHoraHasta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public java.util.Date fechaActual = new java.util.Date();
	java.util.Date fechaHoraReserva;
	Date fechaHoraDesde;
	public Date fechaHoraHasta;
	public int id;
	public Persona persona;
	public Elemento elemento;
	public String detalle;
	public enum Estado
	{
		Reservado,Comenzado,Cancelado,Finalizado
	}
	public Estado estado;

public Reserva(Persona p,Elemento e,String detalle,Estado estado, java.sql.Date fechaHoraDesde, Date fechaHoraHasta){
	this.persona=p;
	this.elemento=e;
	this.detalle=detalle;
	this.estado=Estado.Reservado;
	this.fechaHoraDesde=fechaHoraDesde;
	this.fechaHoraHasta=fechaHoraHasta;
	this.fechaHoraReserva.getTime();
}

public Reserva() {
	this.estado=Estado.Reservado;
	this.fechaHoraReserva = Calendar.getInstance().getTime();
}
	
}

