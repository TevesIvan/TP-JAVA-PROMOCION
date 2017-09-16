package controlador;

import java.util.ArrayList;

import datos.DataCategoria;
import datos.DataPersona;
import entidades.Categoria;
import entidades.Persona;

public class CrtlABMCPersona {
	private DataPersona dataPer;
	private DataCategoria dataCat;
	
	private ArrayList<Persona> pers;
	
	
	public CrtlABMCPersona(){
		dataPer = new DataPersona();
		dataCat=new DataCategoria();
	//	pers= new ArrayList<Persona>();
	}
	
	public void add(Persona p) throws Exception{
		//this.pers.add(p);
		dataPer.add(p);
	}
	
	public void delete(Persona p) throws Exception{
		//this.pers.remove(this.getByDni(p));
		//this.pers.remove(p);
		dataPer.delete(p);
	}
	
	public void update(Persona p) throws Exception{
		//this.delete(p);
		//this.add(p);
		dataPer.delete(p);
		dataPer.add(p);
	}
	
	public Persona getByDni(Persona p) throws Exception{
		return this.dataPer.getByDni(p);
		//return this.getByDni(p.getDni());
	}
	
	public Persona getByDni(String dni) throws Exception{
		Persona p=new Persona();
		p.setDni(dni);
		return getByDni(p);
	}
	
	public ArrayList<Persona> getAll() throws Exception{
		//return this.pers;
		return dataPer.getAll();
	}
	
	public ArrayList<Categoria> getCategorias() throws Exception
	{
		return dataCat.getAll();
	}
	
	public Persona getByUsYCon(Persona p) throws Exception
	{
		return dataPer.getByUsYCon(p);
	}
}
