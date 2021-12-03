package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.ClienteVo;

import conexion.Conn;

public class ClienteDao {
	
	private Conn conex;

	public ClienteDao() {
		conex = new Conn();
		// TODO Auto-generated constructor stub
	}
	
	public void registrarCliente(ClienteVo newClient) {
		try {
			Statement st = conex.getConexion().createStatement();
			st.executeUpdate("INSERT INTO clientes VALUES ('"
					+ newClient.getCodcliente() + "','"
					+ newClient.getNombre() + "','"
					+ newClient.getApellido() + "','"
					+ newClient.getDireccion() + "','"
					+ newClient.getLocalidad() + "','"
					+ newClient.getProvincia() + "','"
					+ newClient.getCod_postal() + "','"
					+ newClient.getTelefono() + "','"
					+ newClient.getDNI() + "')");
			JOptionPane.showMessageDialog(null,
					"Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			st.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"No se Registro, verifique la consola para ver el error",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<ClienteVo> listarClientes() {
		ArrayList<ClienteVo> listaClientes = new ArrayList<ClienteVo>();
		ClienteVo cliente;
		try {
			Statement st = conex.getConexion().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Fabricantes");
			
			while(rs.next()) {
				cliente = new ClienteVo();
				cliente.setCodcliente(Integer.parseInt(rs.getString("codcliente")));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setLocalidad(rs.getString("localidad"));
				cliente.setProvincia(rs.getString("Provincia"));
				cliente.setCod_postal(rs.getString("cod_postal"));
				cliente.setTelefono(rs.getString("Telefono"));
				cliente.setDNI(rs.getString("DNI"));
				listaClientes.add(cliente);
			}
			rs.close();
			st.close();
			
		}
		
		catch (SQLException e){
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaClientes;
	}

}
