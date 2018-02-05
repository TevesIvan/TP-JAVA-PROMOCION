package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.*;
import util.AppDataException;

public class DataTipoElemento {
	public ArrayList<TipoElemento> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tip= new ArrayList<TipoElemento>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from tipo_elemento t");
			if(rs!=null){
				while(rs.next()){
					TipoElemento t=new TipoElemento();
					t.setId(rs.getInt("idTipoElemento"));
					t.setNombre(rs.getString("nombre"));
					t.setCantMax(rs.getInt("cantMax"));
					t.setEncargado(rs.getBoolean("encargado"));
					t.setMaxTiempo(rs.getInt("maxTiempo"));
					t.setDiasAntMax(rs.getInt("diasAntMax"));
					tip.add(t);
				}
			}
		} catch (SQLException e) {
			
			throw e;
		} catch (AppDataException ade){
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			throw e;
		}
		
		return tip;
	}
	
	public ArrayList<TipoElemento> getByUsuario(Persona p) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tip= new ArrayList<TipoElemento>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
							"select * from tipo_elemento t where (t.encargado=? or t.encargado=?) and t.idTipoElemento not in (select t.idTipoElemento from tipo_elemento t inner join elemento e on e.idTipoElemento=t.idTipoElemento inner join reserva r on r.idElemento=e.idElemento where r.idPersona=? and r.estado=? group by t.idTipoElemento having count(t.idTipoElemento)>=t.cantMax)");
			if(p.getCategoria().getNombreCat().equals("Encargado")){
				stmt.setInt(1, 0);
				stmt.setInt(2, 1);
			}
			else{
				stmt.setInt(1, 0);
				stmt.setInt(2, 0);
			}
			stmt.setInt(3, p.getId());
			stmt.setString(4, "Reservado");
			rs=stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					TipoElemento t=new TipoElemento();
					t.setId(rs.getInt("idTipoElemento"));
					t.setNombre(rs.getString("nombre"));
					t.setCantMax(rs.getInt("cantMax"));
					t.setEncargado(rs.getBoolean("encargado"));
					t.setMaxTiempo(rs.getInt("maxTiempo"));
					t.setDiasAntMax(rs.getInt("diasAntMax"));
					tip.add(t);
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (AppDataException ade){
			throw ade;
		}
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {		
			throw e;
		}
		return tip;
	}
	
	public void add(TipoElemento t) throws Exception{
		
		PreparedStatement stmt =null;
		ResultSet keyResultSet=null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"insert into tipo_elemento(nombre,cantMax,encargado,maxTiempo,diasAntMax) values (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, t.getNombre());
			stmt.setInt(2, t.getCantMax());
			stmt.setBoolean(3, t.isEncargado());
			stmt.setInt(4, t.getMaxTiempo());
			stmt.setInt(5, t.getDiasAntMax());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				t.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public void update(TipoElemento t) throws Exception{
		
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"update tipo_elemento set nombre=?, cantMax=?, encargado=?, maxTiempo=?, diasAntMax=? where idTipoElemento=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, t.getNombre());
			stmt.setInt(2, t.getCantMax());
			stmt.setBoolean(3, t.isEncargado());
			stmt.setInt(4, t.getMaxTiempo());
			stmt.setInt(5, t.getDiasAntMax());
			stmt.setInt(6, t.getId());
			stmt.executeUpdate();
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public void delete(TipoElemento t) throws Exception
	{
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"delete from tipo_elemento where idTipoElemento=?");
			stmt.setInt(1, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		try {
			
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			throw e;
		}
	}
	
	public TipoElemento getByNombre(TipoElemento tip) throws Exception{
		TipoElemento t=null;
		ResultSet rs=null;
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"select nombre,cantMax,idTipoElemento,encargado,maxTiempo,diasAntMax from tipo_elemento where nombre=?");
			stmt.setString(1, tip.getNombre());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				t=new TipoElemento();
				t.setNombre(rs.getString("nombre"));
				t.setCantMax(rs.getInt("cantMax"));
				t.setId(rs.getInt("idTipoElemento"));
				t.setEncargado(rs.getBoolean("encargado"));
				t.setMaxTiempo(rs.getInt("maxTiempo"));
				t.setDiasAntMax(rs.getInt("diasAntMax"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return t;
	}

	public TipoElemento getById(TipoElemento tip) throws Exception{
		TipoElemento t=null;
		ResultSet rs=null;
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"select nombre,cantMax,idTipoElemento,encargado,maxTiempo,diasAntMax from tipo_elemento where idTipoElemento=?");
			stmt.setInt(1, tip.getId());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				t=new TipoElemento();
				t.setNombre(rs.getString("nombre"));
				t.setCantMax(rs.getInt("cantMax"));
				t.setId(rs.getInt("idTipoElemento"));
				t.setEncargado(rs.getBoolean("encargado"));
				t.setMaxTiempo(rs.getInt("maxTiempo"));
				t.setDiasAntMax(rs.getInt("diasAntMax"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return t;
	}
}
