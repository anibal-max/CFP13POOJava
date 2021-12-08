package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import vo.VentasVo;

import conexion.Conn;

public class VentasDao {
	
	private Conn conex;
	
	public VentasDao() {
		conex = new Conn();
		// TODO Auto-generated constructor stub
	}
	
	public void venderProducto(VentasVo venta) {
		try {
			Statement st = conex.getConexion().createStatement();
			st.executeUpdate("INSERT INTO compra VALUES ('"
					+ venta.getCod_cliente() + "','"
					+ venta.getCod_art() + "','"
					+ venta.getFecha() + "','"
					+ venta.getCantidad() + "')"
					);
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
	
	public ArrayList<VentasVo> mostrarVentas (Integer month) {
		ArrayList<VentasVo> ventas = new ArrayList<VentasVo>();
		VentasVo venta;
		
		if(month != 0) {
			try {
				PreparedStatement st = conex.getConexion().prepareStatement("SELECT * FROM compra WHERE month(fecha)=?");
				st.setInt(1, month);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					venta = new VentasVo();
					venta.setCod_cliente(Integer.parseInt(rs.getString("clientes_codcliente")));
					venta.setCod_art(Integer.parseInt(rs.getString("articulos_codigo")));
					venta.setFecha(rs.getTimestamp("fecha"));
					venta.setCantidad(Integer.parseInt(rs.getString("unidades")));		
					ventas.add(venta);
				}
				rs.close();
				st.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			try {
				Statement st = conex.getConexion().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM compra ORDER BY fecha DESC");
				while(rs.next()) {
					venta = new VentasVo();
					venta.setCod_cliente(Integer.parseInt(rs.getString("clientes_codcliente")));
					venta.setCod_art(Integer.parseInt(rs.getString("articulos_codigo")));
					venta.setFecha(rs.getTimestamp("fecha"));
					venta.setCantidad(Integer.parseInt(rs.getString("unidades")));		
					ventas.add(venta);
				}
				rs.close();
				st.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		return ventas;
	}

}
