package vo;

public class VentasVo {
	private Integer cod_cliente;
	private Integer cod_art;
	private java.sql.Timestamp fecha;
	private Integer cantidad;

	public Integer getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public Integer getCod_art() {
		return cod_art;
	}

	public void setCod_art(Integer cod_art) {
		this.cod_art = cod_art;
	}

	public java.sql.Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public VentasVo() {
		// TODO Auto-generated constructor stub
	}

}
