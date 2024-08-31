package proyecto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class CalculoExistencia {

	public void actualizarInventario(String archivoFactura, String archivoInventario) throws IOException {
        // Leer datos de factura.txt
        Map<String, Integer> factura = leerFactura(archivoFactura);
        System.out.println("Datos de factura leídos: " + factura);

        // Leer datos de Inventario.txt
        Map<String, Integer> inventario = leerInventario(archivoInventario);
        System.out.println("Datos de inventario leídos: " + inventario);

        // Actualizar inventario
        for (String producto : factura.keySet()) {
            if (inventario.containsKey(producto)) {
                int nuevaCantidad = inventario.get(producto) - factura.get(producto);
                inventario.put(producto, nuevaCantidad);
                System.out.println("Actualizando " + producto + ": " + inventario.get(producto));
            } else {
                System.out.println("Producto " + producto + " no encontrado en inventario.");
            }
        }

        // Guardar datos actualizados en Inventario.txt
        guardarInventario(archivoInventario, inventario);
        System.out.println("Inventario actualizado guardado.");
    }

    private Map<String, Integer> leerFactura(String archivo) throws IOException {
        Map<String, Integer> factura = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("PLAYERA") || linea.startsWith("SOMBRILLA") || linea.startsWith("GORRA") || linea.startsWith("PACHON") || linea.startsWith("CHUMPA")) {
                String producto = linea.split(" ")[0];
                br.readLine(); // Saltar línea de Id
                String cantidadLinea = br.readLine();
                int cantidad = Integer.parseInt(cantidadLinea.split(": ")[1]);
                factura.put(producto, cantidad);
            }
        }
        br.close();
        return factura;
    }

    private Map<String, Integer> leerInventario(String archivo) throws IOException {
        Map<String, Integer> inventario = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("DATOS DE")) {
                String producto = linea.split(" ")[2].replace(":", "");
                String cantidadLinea = br.readLine();
                int cantidad = Integer.parseInt(cantidadLinea.split(": ")[1]);
                inventario.put(producto, cantidad);
                br.readLine(); 
            }
        }
        br.close();
        return inventario;
    }

    private void guardarInventario(String archivo, Map<String, Integer> inventario) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("INVENTARIO\n\n");
        for (String producto : inventario.keySet()) {
            bw.write("DATOS DE " + producto.toUpperCase() + ":\n");
            bw.write("Cantidad: " + inventario.get(producto) + "\n");
            bw.write("Origen: local\n");
            bw.write("Modificada: Sí\n\n");
        }
        bw.close();
    }
}