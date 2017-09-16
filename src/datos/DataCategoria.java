package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.AppDataException;
import entidades.Categoria;

public class DataCategoria {
public ArrayList<Categoria> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> cats=new ArrayList<Categoria>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from categoria");
			if(rs!=null){
				while(rs.next()){
					Categoria c=new Categoria();
					c.setIdCategoria(rs.getInt("idCategoria"));
					c.setNombre(rs.getString("nombre"));
					cats.add(c);
				}
			}
		} catch (Exception e) {
			
			throw e;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cats;
		
	}
public Categoria getByNombre(String nom) throws Exception{
	Categoria c=null;
	ResultSet rs=null;
	PreparedStatement stmt =null;
	try {
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
				"select idCategoria, nombre from categoria where nombre=?");
		stmt.setString(1, nom);
		rs = stmt.executeQuery();
		if(rs!=null && rs.next()){
			c=new Categoria();
			c.setNombre(rs.getString("nombre"));
			c.setIdCategoria(rs.getInt("idCategoria"));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	try {
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		FactoryConexion.getInstancia().releaseConn();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return c;
}
}
