package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.Reserva.Estado;
import entidades.TipoElemento;
import util.AppDataException;

public class DataReserva {

	public void registrarReserva(Reserva r) throws Exception{
		PreparedStatement stmt =null;
		ResultSet keyResultSet=null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"insert into reserva(idPersona,idElemento,detalle,estado,fechaHoraReserva,fechaHoraDesde,fechaHoraHasta) values (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			

			stmt.setString(3, r.getDetalle());
			stmt.setString(4, r.getEstado().toString());
			stmt.setDate(5,new java.sql.Date(r.getFechaHoraReserva().getTime()));
			stmt.setDate(6, new java.sql.Date(r.getFechaHoraDesde().getTime()));
			stmt.setDate(7,new java.sql.Date(r.getFechaHoraHasta().getTime()));
			stmt.setInt(1, r.getPersona().getId());
			stmt.setInt(2, r.getElemento().getId());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId(keyResultSet.getInt(1));
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

	public ArrayList<Elemento> buscaElementosDisp(Reserva r) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> ele= new ArrayList<Elemento>();
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select e.idElemento,e.nombre,t.idTipoElemento,t.nombre,t.cantMax from elemento e inner join tipo_elemento t on e.idTipoElemento=t.idTipoElemento where t.nombre=? and e.idElemento not in(select e.idElemento from elemento e inner join reserva r on r.idElemento=e.idElemento where ((? between r.fechaHoraDesde and r.fechaHoraHasta) or (? between r.fechaHoraDesde and r.fechaHoraHasta)or(?<r.fechaHoraDesde and ?>r.fechaHoraHasta))and (r.estado=? or r.estado=?))");
			stmt.setString(1,r.getElemento().getTipoElemento().getNombre());
			stmt.setDate(2,new java.sql.Date(r.getFechaHoraDesde().getTime()));
			stmt.setDate(3,new java.sql.Date(r.getFechaHoraHasta().getTime()));
			stmt.setDate(4,new java.sql.Date(r.getFechaHoraDesde().getTime()));
			stmt.setDate(5,new java.sql.Date(r.getFechaHoraHasta().getTime()));
			stmt.setString(6,"Reservado");
			stmt.setString(7,"Comenzado");
			rs=stmt.executeQuery();
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
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				
				throw e;
			}
		}
		return ele;
		}

	public ArrayList<Reserva> getAll(Persona usu) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> reservas= new ArrayList<Reserva>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().prepareStatement("select * from reserva r inner join persona p on p.id=r.idPersona inner join elemento e on e.idElemento=r.idElemento inner join categoria c on c.idCategoria=p.idCategoria inner join tipo_elemento t on t.idTipoElemento=e.idTipoElemento where p.id=? and r.estado=?");
			stmt.setInt(1, usu.getId());
			stmt.setString(2,"Reservado");
			rs=stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reserva r=new Reserva();
					r.setPersona(new Persona());
					r.setElemento(new Elemento());
					r.getPersona().setCategoria(new Categoria());
					r.getElemento().setTipoElemento(new TipoElemento());
					r.setDetalle(rs.getString("r.detalle"));
					r.setEstado(Enum.valueOf(Reserva.Estado.class,rs.getString("r.estado")));
					r.setFechaHoraReserva(rs.getDate("r.fechaHoraReserva"));
					r.setFechaHoraDesde(rs.getDate("r.fechaHoraDesde"));
					r.setFechaHoraHasta(rs.getDate("r.fechaHoraHasta"));
					r.setId(rs.getInt("r.id"));
					r.getPersona().setApellido(rs.getString("p.apellido"));
					r.getPersona().setId(rs.getInt("p.id"));
					r.getPersona().setNombre(rs.getString("p.nombre"));
					r.getPersona().setDni(rs.getString("p.dni"));
					r.getPersona().setHabilitado(rs.getBoolean(("p.habilitado")));
					r.getPersona().setUsuario(rs.getString("p.usuario"));
					r.getPersona().setContraseña(rs.getString("p.contraseña"));
					r.getPersona().getCategoria().setIdCategoria(rs.getInt("c.idCategoria"));
					r.getPersona().getCategoria().setNombre(rs.getString("c.nombre"));
					r.getElemento().setId(rs.getInt("e.idElemento"));
					r.getElemento().setNombre(rs.getString("e.nombre"));
					r.getElemento().getTipoElemento().setId(rs.getInt("t.idTipoElemento"));
					r.getElemento().getTipoElemento().setCantMax(rs.getInt("t.cantMax"));
					r.getElemento().getTipoElemento().setNombre(rs.getString("t.nombre"));
					reservas.add(r);
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
		
		return reservas;
	}

	public void cancelarReserva(Reserva r) throws Exception {
		PreparedStatement stmt =null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(		
					"update reserva set estado=? where id=?"
					);
			stmt.setString(1,"Cancelado");
			stmt.setInt(2, r.getId());
			stmt.executeUpdate();
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}

}
