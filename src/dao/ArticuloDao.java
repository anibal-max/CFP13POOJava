package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.ArticuloVo;

import conexion.Conn;

/**
 * Clase que permite el acceso a la base de datos
 * 
 */
public class ArticuloDao {

	private Conn conex;

	public ArticuloDao() {
		conex = new Conn();
	}

	/**
	 * Usa el objeto enviado para insertar un articulo en tabla desde VentanaArticulos
	 */
	public void registrarArticulo(ArticuloVo miArticulo) {
		ArticuloVo miArticuloVo = new ArticuloVo();
		ArticuloDao miArticuloDao = new ArticuloDao();
		
		miArticuloVo = miArticuloDao.consultarArticulo(miArticulo.getIdCodigoArt()).get(0);
		if(miArticuloVo.getIdCodigoArt()!=null) {
			//Actualizar el stock si el articulo ya existe
			try {
				PreparedStatement estatuto = conex.getConexion().prepareStatement("UPDATE articulos "
						+ "SET articulos.stock = ?, articulos.precio = ? WHERE codigo = ?");
				estatuto.setInt(1, (miArticulo.getStockArt() + miArticuloVo.getStockArt()));
				estatuto.setBigDecimal(2, BigDecimal.valueOf(miArticulo.getPrecioArt()));
				estatuto.setInt(3, miArticuloVo.getIdCodigoArt());
				estatuto.executeUpdate();
				
				JOptionPane.showMessageDialog(null,
						"Se ha registrado Exitosamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,
						"No se Registro, verifique la consola para ver el error",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else {		
			//crear el articulo si no existe
			try {
				Statement estatuto = conex.getConexion().createStatement();
				estatuto.executeUpdate("INSERT INTO articulos VALUES ('"
						+ miArticulo.getIdCodigoArt() + "', '"
						+ miArticulo.getNombreArt() + "', '"
						+ miArticulo.getPrecioArt() + "', '"	
						+ miArticulo.getFabricanteCodArt() + "', '"
						+ miArticulo.getStockArt() + "') ");
				JOptionPane.showMessageDialog(null,
						"Se ha registrado Exitosamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null,
						"No se Registro, verifique la consola para ver el error",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	/**
	 * Retorna una lista con los datos de la BD, para luego ser 
	 * recorrida y almacenada en la tabla por medio de la Matriz
	 * @return
	 */
	public ArrayList<ArticuloVo> buscarArticulosConMatriz() {
		ArrayList<ArticuloVo> miLista = new ArrayList<ArticuloVo>();
		ArticuloVo articulo;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM articulos ");

			while (rs.next()) {
				articulo = new ArticuloVo();
				articulo.setIdCodigoArt(Integer.parseInt(rs.getString("codigo")));
				articulo.setNombreArt(rs.getString("nombre"));
				articulo.setPrecioArt(Double.parseDouble(rs.getString("precio")));
				articulo.setFabricanteCodArt(Integer.parseInt(rs.getString("fabricantes_codigo")));
				articulo.setStockArt(Integer.parseInt(rs.getString("stock")));  
				miLista.add(articulo);
			}
			rs.close();
			estatuto.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return miLista;
	}

		/**
	 * permite consultar el empleado asociado al documento enviado
	 * como parametro 
	 * @param documento 
	 * @return
	 */
	public ArrayList<ArticuloVo> consultarArticulo(int codigo) {
		ArrayList< ArticuloVo> miEmpleado = new ArrayList< ArticuloVo>();

		try {
			PreparedStatement consulta = conex.getConexion().prepareStatement("SELECT * FROM articulos where codigo = ? ");
			consulta.setInt(1, codigo);
			ResultSet res = consulta.executeQuery(); 

			if(res.next()){
				ArticuloVo articulo= new ArticuloVo();
				articulo.setIdCodigoArt(Integer.parseInt(res.getString("codigo")));
				articulo.setNombreArt(res.getString("nombre"));
				articulo.setPrecioArt(Double.parseDouble(res.getString("precio")));
				articulo.setFabricanteCodArt(Integer.parseInt(res.getString("fabricantes_codigo")));   
				articulo.setStockArt(Integer.parseInt(res.getString("stock")));  


				miEmpleado.add(articulo);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el Articulo\n"+e);
		}
		return miEmpleado;
	}
	
	public void venderArticulo(int codArt, int cantidad) {
		try {
			PreparedStatement estatuto = conex.getConexion().prepareStatement("UPDATE articulos SET stock = ? WHERE codigo = ?");
			estatuto.setInt(1, cantidad);
			estatuto.setInt(2, codArt);
			estatuto.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"No se Registro, verifique la consola para ver el error",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
