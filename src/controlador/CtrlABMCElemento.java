package controlador;

import java.util.ArrayList;

import datos.DataElemento;
import datos.DataTipoElemento;
import entidades.Categoria;
import entidades.Elemento;
import entidades.TipoElemento;

public class CtrlABMCElemento {
	private DataElemento dataEle;
	private DataTipoElemento dataTip;

	public CtrlABMCElemento(){
		dataEle = new DataElemento();
		dataTip=new DataTipoElemento();
	}
	
	public void add(Elemento el) throws Exception{
		dataEle.add(el);
	}
	

	
	public void delete(Elemento el) throws Exception{
		dataEle.delete(el);
	}
	
	public void update(Elemento el) throws Exception{
		dataEle.delete(el);
		dataEle.add(el);
	}
	
	public ArrayList<Elemento> getAll() throws Exception{
		return dataEle.getAll();
	}
	
	public ArrayList<TipoElemento> getAllTipoElemento() throws Exception
	{
		return dataTip.getAll();
	}
	
	public Elemento getByNomYTip(Elemento el) throws Exception
	{
		return dataEle.getByNomYTip(el);
	}
}
