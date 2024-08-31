package proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class GuardarProductos {
	
	// Método para guardar producto
    private static void guardarEnArchivo(Producto producto) {
        File archivo = new File("pedido.txt");

        try (FileWriter escritor = new FileWriter(archivo, true);
             BufferedWriter bufferEscritura = new BufferedWriter(escritor)) {
            
            if (archivo.length() > 0) {
                bufferEscritura.newLine(); // Verificar si el archivo contiene datos
            }
            
            bufferEscritura.write(producto.getNombre().toUpperCase());
            bufferEscritura.newLine();
            bufferEscritura.write("Id: " + producto.getId());
            bufferEscritura.newLine();
            bufferEscritura.write("Cantidad: " + producto.getCantidad());
            bufferEscritura.newLine();
            bufferEscritura.write("Origen: " + producto.getOrigen());
            bufferEscritura.newLine();
            bufferEscritura.write("Modificada: " + producto.isModificada());
            bufferEscritura.newLine();
            bufferEscritura.write("Precio: " + producto.getPrecio());
            bufferEscritura.newLine();
            bufferEscritura.write("Total: " + (producto.getPrecio() * producto.getCantidad()));
            bufferEscritura.newLine();
            bufferEscritura.write("--------------------------------------------");

            System.out.println("Datos guardados en la Base de Datos pedidos.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar Playera
    public static void guardarEnArchivo(Playera playera) {
        guardarEnArchivo((Producto) playera);
    }

    // Guardar Sombrilla
    public static void guardarEnArchivo(Sombrilla sombrilla) {
        guardarEnArchivo((Producto) sombrilla);
    }

    // Guardar Gorra
    public static void guardarEnArchivo(Gorra gorra) {
        guardarEnArchivo((Producto) gorra);
    }

    // Guardar Pachón
    public static void guardarEnArchivo(Pachon pachon) {
        guardarEnArchivo((Producto) pachon);
    }

    // Guardar Chumpa
    public static void guardarEnArchivo(Chumpa chumpa) {
        guardarEnArchivo((Producto) chumpa);
    }

    // Guardar el total combinado
    public static void guardarTotalCombinado(double totalCombinado) {
        File archivo = new File("pedido.txt");

        try (FileWriter escritor = new FileWriter(archivo, true);
             BufferedWriter bufferEscritura = new BufferedWriter(escritor)) {
            
            bufferEscritura.newLine();
            bufferEscritura.write("Total Combinado: " + totalCombinado);
            bufferEscritura.newLine();
            bufferEscritura.write("============================================");

            System.out.println("Total combinado guardado en la Base de Datos pedidos.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
