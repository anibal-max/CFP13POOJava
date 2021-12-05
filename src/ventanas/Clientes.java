package ventanas;


import java.awt.AWTException;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.ClienteDao;
import vo.ArticuloVo;
import vo.ClienteVo;

public class Clientes extends JFrame implements ActionListener {
	
	private ClienteDao cliente;
	
	private JLabel lblTitulo, lblTabla1;
	
	private JLabel lblCodCliente, lblNomCliente, lblApeCliente;
	private JLabel lblDirCliente, lblLocCliente, lblProvCliente;
	private JLabel lblCpCliente, lblTelCliente, lblDniCliente;
	
	private JLabel empty;
	
	private JTextField tfCodCliente, tfNomCliente, tfApeCliente;
	private JTextField tfDirCliente, tfLocCliente, tfProvCliente;
	private JTextField tfCpCliente, tfTelCliente, tfDniCliente;
	
	private JButton btnRegistrar, btnCancelar;
	
	JTable tabla1;
	JScrollPane barra1;

	public Clientes() {
		cliente = new ClienteDao();
		
		abrirVentana();
		this.revalidate();
		clearTf();
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	private void abrirVentana() {
		createNewJButton(btnRegistrar, "Guardar", 130, 240, 100, 30);
		createNewJButton(btnCancelar, "Cancelar", 240, 240, 100, 30);
		
		setSize(480, 650);
		setTitle("TABLA CLIENTES");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		createNewJLabelTitle(lblTitulo, "REGISTRO DE CLIENTES", 120, 20, 380, 30, "Verdana", 1, 18);
		
		createNewJLabel(lblCodCliente, "Codigo", 20, 80, 80, 15);
		createNewJLabel(lblNomCliente, "Nombre", 20, 110, 80, 15);
		createNewJLabel(lblApeCliente, "Apellido", 20, 140, 80, 15);
		createNewJLabel(lblTelCliente, "Teléfono", 20, 170, 80, 15);
		createNewJLabel(lblDniCliente, "DNI", 20, 200, 80, 15);
		
		createNewJTextField(tfCodCliente, 85, 76, 100, 25);
		createNewJTextField(tfNomCliente, 85, 106, 100, 25);
		createNewJTextField(tfApeCliente, 85, 136, 100, 25);
		createNewJTextField(tfTelCliente, 85, 166, 100, 25);
		createNewJTextField(tfDniCliente, 85, 196, 100, 25);
		
		createNewJLabel(lblDirCliente, "Direccion", 220, 110, 80, 15);
		createNewJLabel(lblLocCliente, "Localidad", 220, 140, 80, 15);
		createNewJLabel(lblProvCliente, "Provincia", 220, 170, 80, 15);
		createNewJLabel(lblCpCliente, "Codigo Postal", 220, 200, 80, 15);
		
		createNewJTextField(tfDirCliente, 315, 106, 100, 25);
		createNewJTextField(tfLocCliente, 315, 136, 100, 25);
		createNewJTextField(tfProvCliente, 315, 166, 100, 25);
		createNewJTextField(tfCpCliente, 315, 196, 100, 25);
		
		
		
		createNewJLabel(empty, "", 0, 360, 1, 1);		
		
		
		
	}
	
	public void createNewJLabel(JLabel name, String text, int x, int y, int wdt, int hgt) {
		name = new JLabel();
		name.setText(text);
		name.setBounds(x, y, wdt, hgt);
		add(name);
		//Metodo para crear nuevas etiquetas de forma mas práctica
	}
	public void createNewJLabelTitle(JLabel name, String text, int x, int y, int wdt, int hgt, String font, int style, int size) {
		name = new JLabel();
		name.setText(text);
		name.setBounds(x, y, wdt, hgt);
		name.setFont(new java.awt.Font(font, style, size));
		add(name);
		//Metodo para crear nuevas etiquetas de titulos de forma mas práctica
	}
	
	public void createNewJTextField(JTextField name, int x, int y, int wdt, int hgt) {
		name = new JTextField();
		name.setBounds(x, y, wdt, hgt);
		name.setText("");	
		add(name);
		
			
		//Metodo para crear nuevos campos de texto de forma mas práctica
	}
	
	public void createNewJButton(JButton name, String text, int x, int y, int wdt, int hgt) {
		name = new JButton();
		name.setText(text);
		name.setBounds(x, y, wdt, hgt);
		add(name);
		name.addActionListener(this);
		//Metodo para crear nuevos botones de forma mas práctica
	}
	
	
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == botonGuardar) {

			try {
				ArticuloVo miArticulo = new ArticuloVo();
				miArticulo.setIdCodigoArt(Integer.parseInt(textCod.getText()));
				miArticulo.setNombreArt(textNombre.getText());
				miArticulo.setPrecioArt(Double.parseDouble(textPrecio.getText()));
				miArticulo.setFabricanteCodArt(Integer.parseInt(textFabricante.getText()));
				miArticulo.setStockArt(Integer.parseInt(textStock.getText()));







				miArticuloDao.registrarArticulo(miArticulo);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en el Ingreso de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} finally {
			*/
				/* Actualizamos siempre las tablas despues del registro */
		/*		mostrarDatosUsandoLogica1();
				limpiar();
			}
		} */
	/*
		if (e.getSource() == btnRegistrar) {
			
		}
			if (e.getSource() == btnCancelar) {
				clearTf();
			}
	}*/
	
	private void clearTf() {
		//System.out.print(tfCodCliente.getText());
		
		tfCodCliente.setText("");
		tfNomCliente.setText("");
		tfApeCliente.setText("");
		tfDirCliente.setText("");
		tfLocCliente.setText("");
		tfProvCliente.setText("");
		tfTelCliente.setText("");
		tfDniCliente.setText("");
		tfCpCliente.setText("");	
		tfCodCliente.setText("");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancelar) {
			clearTf();
		}
		
	}

}
