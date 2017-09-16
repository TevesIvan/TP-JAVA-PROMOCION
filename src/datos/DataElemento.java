package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.TipoElemento;
import util.AppDataException;

public class DataElemento {
	public ArrayList<Elemento> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> ele= new ArrayList<Elemento>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select e.idElemento,e.nombre,e.idTipoElemento,t.idTipoElemento,t.nombre,t.cantMax from elemento e inner join tipo_elemento t on e.idTipoElemento=t.idTipoElemento");
			if(rs!=null){
				while(rs.next()){
					Elemento e=new Elemento();
					e.setTipoElemento(new TipoElemento());
					e.setNombre(rs.getString("e.nombre"));
					e.setId(rs.getInt("e.idElemento"));
					e.getTipoElemento().setId(rs.getInt("t.idTipoElemento"));
					e.getTipoElemento().setNombre(rs.getString("t.nombre"));
					e.getTipoElemento().setCantMax(rs.getInt("t.cantMax"));
					ele.add(e);
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
		
		return ele;
		
	}
	
	public void add(Elemento e) throws Exception{
		
		PreparedStatement stmt =null;
		ResultSet keyResultSet=null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"insert into elemento(nombre,idTipoElemento) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(2, e.getTipoElemento().getId());
			stmt.setString(1, e.getNombre());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				e.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException | AppDataException ex) {
			throw ex;
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	public Elemento getByNomYTip(Elemento ele) throws Exception{
		Elemento e=null;
		ResultSet rs=null;
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"select e.nombre,e.idElemento,t.idTipoElemento,t.nombre from elemento e inner join tipo_elemento t on t.idTipoElemento=e.idTipoElemento where e.nombre=? and t.idTipoElemento=?");
			stmt.setString(1, ele.getNombre());
			stmt.setInt(2,ele.getTipoElemento().getId());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				e=new Elemento();
				e.setNombre(rs.getString("e.nombre"));
				e.setId(rs.getInt("e.idElemento"));
				e.setTipoElemento(new TipoElemento());
				e.getTipoElemento().setId(rs.getInt("t.idTipoElemento"));
				e.getTipoElemento().setNombre(rs.getString("t.nombre"));
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException ex) {
				throw ex;
			}
		}
		return e;
	}
	
	
	
	public void delete(Elemento e) throws Exception
	{
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"delete from elemento where nombre=? and idTipoElemento=?");
			stmt.setString(1, e.getNombre());
			stmt.setInt(2,e.getTipoElemento().getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		}
		try {
			
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			
			throw ex;
		}
	}
}
