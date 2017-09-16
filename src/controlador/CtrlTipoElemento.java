package controlador;

import java.util.ArrayList;

import datos.DataCategoria;
import datos.DataPersona;
import datos.DataTipoElemento;
import entidades.Persona;
import entidades.TipoElemento;

public class CtrlTipoElemento {
	private DataTipoElemento dataTip;
	
	public CtrlTipoElemento(){
		dataTip = new DataTipoElemento();
	}
	
	public void add(TipoElemento t) throws Exception{
		dataTip.add(t);
	}
	

	
	public void delete(TipoElemento t) throws Exception{
		dataTip.delete(t);
	}
	
	public void update(TipoElemento t) throws Exception{
		dataTip.delete(t);
		dataTip.add(t);
	}
	
	public ArrayList<TipoElemento> getAll() throws Exception{
		return dataTip.getAll();
	}
	
	public TipoElemento getByNombre(TipoElemento t) throws Exception{
		return this.dataTip.getByNombre(t);
	}
	
	public TipoElemento getByNombre(String nombre) throws Exception{
		TipoElemento t=new TipoElemento();
		t.setNombre(nombre);
		return getByNombre(t);
	}
}
