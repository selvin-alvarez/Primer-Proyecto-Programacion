package proyecto;

abstract class Persona {
	
	private String nombre;
    private String nit;
    private String direccion;

    // Constructor
    public Persona(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
    }

    // Métodos abstractos (puedes agregar más según tus necesidades)
    public abstract void mostrarInformacion();
    
    // Getters y setters (opcional)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}  
}


