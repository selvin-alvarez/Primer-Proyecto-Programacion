package proyecto;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Inventario {
	
	private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    // Verificar si existe el archivo Inventario.txt
    private boolean existeArchivoInventario(String archivoInventario) {
        File archivo = new File(archivoInventario);
        return archivo.exists();
    }

    // Cargar datos desde OrdenCompra.txt
    public void cargarDatosDesdeOrdenCompra(String archivoOrdenCompra) {
        Map<String, Integer> cantidadesOrdenCompra = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOrdenCompra))) {
            String linea;
            Producto productoActual = null;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("DATOS DE ")) {
                    String nombreProducto = linea.substring(9, linea.length() - 1);
                    productoActual = new Producto(nombreProducto, 0.0, "", 0) {
                        @Override
                        public void mostrarInformacion() {
                            System.out.println("Producto: " + getNombre());
                            System.out.println("Cantidad: " + getCantidad());
                            System.out.println("Origen: " + getOrigen());
                            System.out.println("Modificada: " + (isModificada() ? "Sí" : "No"));
                        }
                    };
                    productos.add(productoActual);
                } else if (productoActual != null) {
                    if (linea.startsWith("Cantidad: ")) {
                        int cantidad = Integer.parseInt(linea.substring(10));
                        productoActual.setCantidad(cantidad);
                        cantidadesOrdenCompra.put(productoActual.getNombre().toUpperCase(), cantidad);
                    } else if (linea.startsWith("Origen: ")) {
                        productoActual.setOrigen(linea.substring(8));
                    } else if (linea.startsWith("Modificada: ")) {
                        productoActual.setModificada(linea.substring(12).equalsIgnoreCase("Sí"));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de orden de compra.");
            e.printStackTrace();
        }

        // Verificar y actualizar Inventario.txt
        if (existeArchivoInventario("Inventario.txt")) {
            actualizarInventario("Inventario.txt", cantidadesOrdenCompra);
        } else {
            guardarDatosEnInventario("Inventario.txt");
        }
    }

    // Actualizar Inventario.txt con las cantidades de OrdenCompra.txt
    private void actualizarInventario(String archivoInventario, Map<String, Integer> cantidadesOrdenCompra) {
        Map<String, Producto> inventarioActual = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoInventario))) {
            String linea;
            Producto productoActual = null;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("DATOS DE ")) {
                    String nombreProducto = linea.substring(9, linea.length() - 1);
                    productoActual = new Producto(nombreProducto, 0.0, "", 0) {
                        @Override
                        public void mostrarInformacion() {
                            System.out.println("Producto: " + getNombre());
                            System.out.println("Cantidad: " + getCantidad());
                            System.out.println("Origen: " + getOrigen());
                            System.out.println("Modificada: " + (isModificada() ? "Sí" : "No"));
                        }
                    };
                    inventarioActual.put(nombreProducto.toUpperCase(), productoActual);
                } else if (productoActual != null) {
                    if (linea.startsWith("Cantidad: ")) {
                        int cantidad = Integer.parseInt(linea.substring(10));
                        productoActual.setCantidad(cantidad);
                    } else if (linea.startsWith("Origen: ")) {
                        productoActual.setOrigen(linea.substring(8));
                    } else if (linea.startsWith("Modificada: ")) {
                        productoActual.setModificada(linea.substring(12).equalsIgnoreCase("Sí"));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de inventario.");
            e.printStackTrace();
        }

        // Sumar cantidades de OrdenCompra.txt a Inventario.txt
        for (Map.Entry<String, Integer> entry : cantidadesOrdenCompra.entrySet()) {
            String nombreProducto = entry.getKey();
            int cantidadOrdenCompra = entry.getValue();
            if (inventarioActual.containsKey(nombreProducto)) {
                Producto producto = inventarioActual.get(nombreProducto);
                producto.setCantidad(producto.getCantidad() + cantidadOrdenCompra);
            } else {
                Producto nuevoProducto = new Producto(nombreProducto, 0.0, "", cantidadOrdenCompra) {
                    @Override
                    public void mostrarInformacion() {
                        System.out.println("Producto: " + getNombre());
                        System.out.println("Cantidad: " + getCantidad());
                        System.out.println("Origen: " + getOrigen());
                        System.out.println("Modificada: " + (isModificada() ? "Sí" : "No"));
                    }
                };
                inventarioActual.put(nombreProducto, nuevoProducto);
            }
        }

        // Guardar datos actualizados en Inventario.txt
        try (FileWriter writer = new FileWriter(archivoInventario)) {
            writer.write("INVENTARIO\n");
            for (Producto producto : inventarioActual.values()) {
                writer.write("\nDATOS DE " + producto.getNombre().toUpperCase() + ":\n");
                writer.write("Cantidad: " + producto.getCantidad() + "\n");
                writer.write("Origen: " + producto.getOrigen() + "\n");
                writer.write("Modificada: " + (producto.isModificada() ? "Sí" : "No") + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los datos en el archivo de inventario.");
            e.printStackTrace();
        }

        // Mostrar inventario actualizado
        mostrarInventario(inventarioActual);
    }

    // Mostrar inventario actualizado
    private void mostrarInventario(Map<String, Producto> inventarioActual) {
        System.out.println("INVENTARIO ACTUALIZADO");
        for (Producto producto : inventarioActual.values()) {
            System.out.println("\nDATOS DE " + producto.getNombre().toUpperCase() + ":");
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Origen: " + producto.getOrigen());
            System.out.println("Modificada: " + (producto.isModificada() ? "Sí" : "No"));
        }
    }

    // Guardar datos en Inventario.txt
    public void guardarDatosEnInventario(String archivoInventario) {
        try (FileWriter writer = new FileWriter(archivoInventario)) {
            writer.write("INVENTARIO\n");
            for (Producto producto : productos) {
                writer.write("\nDATOS DE " + producto.getNombre().toUpperCase() + ":\n");
                writer.write("Cantidad: " + producto.getCantidad() + "\n");
                writer.write("Origen: " + producto.getOrigen() + "\n");
                writer.write("Modificada: " + (producto.isModificada() ? "Sí" : "No") + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los datos en el archivo de inventario.");
            e.printStackTrace();
        }
    }
}
    