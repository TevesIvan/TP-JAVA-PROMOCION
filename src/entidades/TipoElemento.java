package entidades;

import java.io.Serializable;

public class TipoElemento implements Serializable{
	
	public String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantMax() {
		return cantMax;
	}
	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}
	public int id,cantMax;
	public TipoElemento(String nombre,int id,int cantMax)
	{
		this.nombre=nombre;
		this.id=id;
		this.cantMax=cantMax;
	}
	public TipoElemento()
	{}
	
	@Override
	public String toString()
	{
		return this.getNombre();
	}
}
