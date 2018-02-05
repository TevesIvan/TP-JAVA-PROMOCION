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
	public int id,cantMax,maxTiempo,diasAntMax;
	public int getMaxTiempo() {
		return maxTiempo;
	}
	public void setMaxTiempo(int maxTiempo) {
		this.maxTiempo = maxTiempo;
	}
	public int getDiasAntMax() {
		return diasAntMax;
	}
	public void setDiasAntMax(int diasAntMax) {
		this.diasAntMax = diasAntMax;
	}
	public boolean encargado;
	public boolean isEncargado() {
		return encargado;
	}
	public void setEncargado(boolean encargado) {
		this.encargado = encargado;
	}
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
