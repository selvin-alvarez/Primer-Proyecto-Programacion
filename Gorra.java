package proyecto;

class Gorra extends Producto {
	
	// Constructor
	public Gorra(int cantidad) {
        super("Gorra", 249.0, "003", cantidad); // Valores predeterminados
    }
    
    
    // Implementación del método abstracto mostrarInformacion
    @Override
    public void mostrarInformacion() {
    	
        System.out.println("Gorra " + getNombre() + ":");
        System.out.println("Precio: $" + getPrecio());
        System.out.println("Id: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}
