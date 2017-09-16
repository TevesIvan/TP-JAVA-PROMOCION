package entidades;

import java.io.Serializable;

public class Persona implements Serializable{
	public String dni,nombre,apellido,usuario,contraseña;
	public int id;
	public Categoria categoria;
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean habilitado;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Persona(String dni,String nombre,String apellido,String usuario,String contraseña,boolean habilitado)
	{
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.habilitado=habilitado;
		this.usuario=usuario;
		this.contraseña=contraseña;
	}
	public Persona(){}

	@Override
	public boolean equals(Object p){
		return (p instanceof Persona) &&
			 (((Persona)p).getDni().equals(this.getDni()));
					

	}
}
