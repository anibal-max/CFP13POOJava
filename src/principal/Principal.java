package principal;



import ventanas.VentanaArticulos;
import ventanas.VentanaFabricantes;
import ventanas.Clientes;
import ventanas.Compras;


public class Principal {

	/**
	 * Llama la ventana principal
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		VentanaArticulos miVentana1;
		miVentana1 = new VentanaArticulos();
		miVentana1.setBounds(750,80,500,600);
		miVentana1.setVisible(true);

		VentanaFabricantes miVentana2;
		miVentana2 = new VentanaFabricantes();
		miVentana2.setBounds(1300,80,500,600);
		miVentana2.setVisible(true);
		
		Clientes miVentana3;
		miVentana3 = new Clientes();
		miVentana3.setBounds(750,80,500,600);
		miVentana3.setVisible(true);
		*/

		Menu formulario1 = new Menu();
		formulario1.setBounds(300,300,260,310);
		formulario1.setTitle("MENU PRINCIPAL");
		formulario1.setVisible(true);


	}
}
