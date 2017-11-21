package entidades;

import java.io.Serializable;

public class Categoria implements Serializable {
	public String nombre;
	public int idCategoria;
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Categoria && ((Categoria)o).getIdCategoria()==this.getIdCategoria());
	}
	
	@Override
	public String toString()
	{
		return this.getNombreCat();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCat() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getIdCategoria()).hashCode();
	}
}
