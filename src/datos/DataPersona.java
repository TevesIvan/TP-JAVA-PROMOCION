package datos;

import java.sql.*;
import java.util.ArrayList;
import java.security.KeyStore.ProtectionParameter;
import entidades.*;
import javax.swing.JOptionPane;
import util.AppDataException;

public class DataPersona {
	public ArrayList<Persona> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from persona p inner join categoria c on p.idCategoria=c.idCategoria");
			if(rs!=null){
				while(rs.next()){
					Persona p=new Persona();
					p.setCategoria(new Categoria());
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.setUsuario(rs.getString("usuario"));
					p.setContraseña(rs.getString("contraseña"));
					p.setId(rs.getInt("id"));
					p.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
					p.getCategoria().setNombre(rs.getString("nombre"));
					pers.add(p);
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
		
		return pers;
		
	}
	
	public void add(Persona p) throws Exception{
		
		PreparedStatement stmt =null;
		ResultSet keyResultSet=null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"insert into persona(dni,apellido,nombre,usuario,contraseña,habilitado,idCategoria) values (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(3, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(1, p.getDni());
			stmt.setBoolean(6, p.isHabilitado());
			stmt.setString(4, p.getUsuario());
			stmt.setString(5, p.getContraseña());
			stmt.setInt(7,p.getCategoria().getIdCategoria());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public Persona getByDni(Persona per) throws Exception{
		Persona p=null;
		ResultSet rs=null;
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"select p.nombre, apellido, dni, habilitado, usuario, contraseña, id, c.idCategoria from persona p inner join categoria c on p.idCategoria=c.idCategoria where dni=?");
			stmt.setString(1, per.getDni());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Persona();
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				p.setUsuario(rs.getString("usuario"));
				p.setContraseña(rs.getString("contraseña"));
				p.setId(rs.getInt("id"));
				p.setCategoria(new Categoria());
				p.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
				p.getCategoria().setNombre(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		return p;
	}
	
	
	
	public void delete(Persona p) throws Exception
	{
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"delete from persona where dni=?");
			stmt.setString(1, p.getDni());
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
	
	public Persona getByUsYCon(Persona per) throws Exception{
		Persona p=null;
		ResultSet rs=null;
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"select p.nombre, apellido, dni, habilitado, usuario, contraseña, id, c.idCategoria from persona p inner join categoria c on p.idCategoria=c.idCategoria where usuario=? and contraseña=?");
			stmt.setString(1, per.getUsuario());
			stmt.setString(2, per.getContraseña());
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Persona();
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				p.setUsuario(rs.getString("usuario"));
				p.setContraseña(rs.getString("contraseña"));
				p.setId(rs.getInt("id"));
				p.setCategoria(new Categoria());
				p.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
				p.getCategoria().setNombre(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		return p;
	}
}
