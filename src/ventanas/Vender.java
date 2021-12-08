package ventanas;

import javax.swing.*;

import vo.ArticuloVo;
import vo.ClienteVo;
import vo.VentasVo;

import java.sql.Statement;

import conexion.Conn;
import dao.ArticuloDao;
import dao.ClienteDao;
import dao.VentasDao;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import java.text.SimpleDateFormat;  
import java.util.Date;


public class Vender extends JFrame implements ItemListener, ActionListener{

	private ArticuloDao miArticuloDao;
	private ClienteDao miClienteDao;
	private VentasDao miVentaDao = new VentasDao();

	private Conn conex;
	private JComboBox comboArticulo = new JComboBox();
	private JComboBox comboCliente = new JComboBox();
	
	private JLabel stockTitle = new JLabel(); 
	private JLabel precio = new JLabel(); 
	private JLabel precioTitle = new JLabel();
	private JLabel codCliente = new JLabel(); 
	private JLabel codClienteTitle = new JLabel(); 
	private JLabel cantidadTitle = new JLabel(); 
	private JLabel costoTotal = new JLabel(); 
	private JLabel costoTotalTitle = new JLabel(); 
	private JLabel stock = new JLabel();
	
	private JButton vender = new JButton();
	private JButton calcular = new JButton();
	
	private JTextField cantidad = new JTextField();
	
	private JTable tabla1;
	private JScrollPane barra1;

	public Vender() {
		miArticuloDao = new ArticuloDao();
		ArrayList<ArticuloVo> articulos = miArticuloDao.buscarArticulosConMatriz();
		miClienteDao = new ClienteDao();
		ArrayList<ClienteVo> clientes = miClienteDao.listarClientes();

		setLayout(null);		
		
		comboArticulo.addItem("-");
		for(ArticuloVo articulo : articulos) {
			comboArticulo.addItem(articulo);
		}
				
		comboCliente.addItem("-");
		for(ClienteVo cliente : clientes) {
			comboCliente.addItem(cliente);
		}
		
		createNewJComboBox(comboArticulo, 10,10,200,20);
		createNewJComboBox(comboCliente, 10,60,200,20);
		
		createNewJLabel(stockTitle, "stock:", 230, 10, 50, 20);
		createNewJLabel(stock, "", 280, 10, 80, 20);
		
		createNewJLabel(precioTitle, "precio:", 300, 10, 50, 20);
		createNewJLabel(precio, "", 350, 10, 80, 20);
		
		createNewJLabel(codClienteTitle, "codigo:", 230, 60, 80, 20);
		createNewJLabel(codCliente, "", 330, 60, 80, 20);
		
		createNewJLabel(cantidadTitle, "Ingrese Cantidad a vender", 10, 110, 150, 20);
		createNewJTextField(cantidad, 180,110,100,20);
		
		createNewJLabel(costoTotalTitle, "Costo Total:", 10, 160, 80, 20);
		createNewJLabel(costoTotal, "-", 220, 160, 80, 20);
		createNewJButton(calcular,"CALCULAR", 300, 150, 100, 40);
		
		createNewJButton(vender,"VENDER", 120, 210, 80, 40);
		
		barra1 = new JScrollPane();
		barra1.setBounds(40, 265, 320, 100);
		crearTabla();		
		add(barra1);

	}
	

	public void itemStateChanged(ItemEvent e) {		
		if (e.getSource()==comboArticulo) {
			ArticuloVo seleccionado1=(ArticuloVo)comboArticulo.getSelectedItem();
			stock.setText(seleccionado1.getStockArt().toString());
			precio.setText(seleccionado1.getPrecioArt().toString());
		}
		if (e.getSource()==comboCliente) {
			ClienteVo seleccionado2=(ClienteVo)comboCliente.getSelectedItem();
			codCliente.setText(seleccionado2.getCodcliente().toString());
		}
	}
	
	public void createNewJLabel(JLabel name, String text, int x, int y, int wdt, int hgt) {
		//name = new JLabel();
		name.setText(text);
		name.setBounds(x, y, wdt, hgt);
		add(name);
		//Metodo para crear nuevas etiquetas de forma mas práctica
	}
	
	public void createNewJComboBox(JComboBox name, int x, int y, int wdt, int hgt) {
		//name = new JComboBox();
		name.setBounds(x,y,wdt,hgt);
		name.addItemListener(this);
		add(name);
		//Metodo para crear JComboBox de forma mas práctica
	}
	
	public void createNewJButton(JButton name, String text, int x, int y, int wdt, int hgt) {
		//name = new JButton();
		name.setText(text);
		name.setBounds(x, y, wdt, hgt);
		add(name);
		name.addActionListener(this);
		//Metodo para crear nuevos botones de forma mas práctica
	}
	
	public void createNewJTextField(JTextField name, int x, int y, int wdt, int hgt) {
		//name = new JTextField();
		name.setBounds(x, y, wdt, hgt);
		name.setText("");	
		add(name);			
		name.addActionListener(this);
		//Metodo para crear nuevos campos de texto de forma mas práctica
	}

public void crearTabla() {
		
		String titulos[] = {"clientes_codcliente", "articulos_codigo", "fecha", "unidades"};
		ArrayList<VentasVo> listaVentas = miVentaDao.mostrarVentas(0);
		String informacion[][] = new String[listaVentas.size()][titulos.length];
		
		for(int i = 0; i < informacion.length; i++) {
			informacion[i][0] = listaVentas.get(i).getCod_cliente() + "";
			informacion[i][1] = listaVentas.get(i).getCod_art() + "";
			informacion[i][2] = listaVentas.get(i).getFecha() + "";
			informacion[i][3] = listaVentas.get(i).getCantidad() + "";
					
		}
		
		
		tabla1 = new JTable(informacion, titulos);
		tabla1.setEnabled(false);
		tabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		barra1.setViewportView(tabla1);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == calcular) {
			Float resultado = Integer.parseInt(cantidad.getText()) * Float.parseFloat(precio.getText());
			costoTotal.setText(resultado.toString());
		}
		if(e.getSource() == vender) {
			
			//Restar articulos vendidos del Stock de Articulos
			ArticuloDao provisorio = new ArticuloDao();
			ArticuloVo art = (ArticuloVo)comboArticulo.getSelectedItem();
			ArrayList<ArticuloVo> artStock = new ArrayList();
			artStock = provisorio.consultarArticulo(art.getIdCodigoArt());
			Integer stockActual = artStock.get(0).getStockArt();
			
			if(stockActual>=Integer.parseInt(cantidad.getText())) {
				System.out.println(art.getIdCodigoArt()+" "+Integer.parseInt(cantidad.getText()));
				provisorio.venderArticulo(art.getIdCodigoArt(), (stockActual-Integer.parseInt(cantidad.getText())));
				//Actualizar Stock en pantalla
				stock.setText((Integer.valueOf(stockActual-Integer.parseInt(cantidad.getText()))).toString());
				//------------------------------------
				//Agregar transaccion a la tabla compras
				VentasVo nuevaVenta = new VentasVo();
				
				Date date = new Date(); 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String strDate= formatter.format(date);
				
				Timestamp ts = Timestamp.valueOf(strDate);
				
				nuevaVenta.setFecha(ts);
				System.out.println(ts);
				nuevaVenta.setCod_cliente(Integer.parseInt(codCliente.getText()));
				nuevaVenta.setCod_art(art.getIdCodigoArt());
				nuevaVenta.setCantidad(Integer.parseInt(cantidad.getText()));
				VentasDao efectuarVenta = new VentasDao();
				efectuarVenta.venderProducto(nuevaVenta);
				
				
				//Actualizar tabla
				crearTabla();
				
				
			}
			else {
				System.out.println("No hay stock suficiente para realizar la venta");
			}
			
		}
	}

}  



