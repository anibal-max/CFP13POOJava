package vo;

public class ClienteVo {
	private Integer codcliente;
	private String nombre;
	private String apellido;
	private String direccion;
	private String localidad;
	private String provincia;
	private String cod_postal;
	private String telefono;
	private String DNI;
	
	public ClienteVo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Integer getCodcliente() {
		return codcliente;
	}


	public void setCodcliente(Integer codcliente) {
		this.codcliente = codcliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getCod_postal() {
		return cod_postal;
	}


	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dni) {
		DNI = dni;
	}
	
	@Override  // representacion en texto del obj
	public String toString() {
		return nombre+" "+apellido;
	}

}
