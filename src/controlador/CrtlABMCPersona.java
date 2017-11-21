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
	}
	
	public void add(Persona p) throws Exception{
		dataPer.add(p);
	}
	
	public void delete(Persona p) throws Exception{
		dataPer.delete(p);
	}
	
	public void update(Persona p) throws Exception{
		dataPer.delete(p);
		dataPer.add(p);
	}
	
	public Persona getByDni(Persona p) throws Exception{
		return this.dataPer.getByDni(p);
	}
	
	public Persona getByDni(String dni) throws Exception{
		Persona p=new Persona();
		p.setDni(dni);
		return getByDni(p);
	}
	
	public ArrayList<Persona> getAll() throws Exception{
		return dataPer.getAll();
	}
	
	public ArrayList<Categoria> getCategorias() throws Exception
	{
		return dataCat.getAll();
	}
	
	public Persona getByUsYCon(Persona p) throws Exception
	{
		try {
			return dataPer.getByUsYCon(p);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String personaListText() throws Exception {
		String texto="Dni\tApellido\tNombre\tCategoria\n";
		ArrayList<Persona> personas =this.getAll();
		for (Persona p : personas){
			texto= texto + p.getDni() +"\t"+p.getApellido()+"\t"+p.getNombre()+"\t"+
					p.getCategoria().getNombreCat()+"\n";
		}
		return texto;
	}
	
	public Categoria getByNombre(Categoria c) throws Exception
	{
		return dataCat.getByNombre(c);
	}
}
