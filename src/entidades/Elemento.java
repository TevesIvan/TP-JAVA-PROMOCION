package entidades;

import java.io.Serializable;

public class Elemento implements Serializable{
	public TipoElemento tipoElemento;
	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}
	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int id;
	public String nombre;
	public Elemento(TipoElemento tipoElemento,int id,String nombre)
	{
		this.tipoElemento=tipoElemento;
		this.id=id;
		this.nombre=nombre;
	}
	public Elemento(){}
}
