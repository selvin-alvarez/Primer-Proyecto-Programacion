package proyecto;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		// Instancias
        Pedido pedido = new Pedido();
        OrdenCompra ordenCompra = new OrdenCompra();
        CalculoExistencia calculoExistencia = new CalculoExistencia();
        Factura factura = new Factura();
        Inventario inventario = new Inventario();
        Existencias existencias = new Existencias();
        
        int opcion = 0;

        do {
            System.out.println("\tMENÚ:");
            System.out.println();
            System.out.println("1. Crear Pedido");
            System.out.println("2. Crear Orden de Compra");
            System.out.println("3. Cargar Existencias");
            System.out.println("4. Factura");
            System.out.println("5. Descargar Existencias");
            System.out.println("6. Ver Existencias");
            System.out.println("7. Salir");
            System.out.print("Elige una opción (1-7): ");

            // Verifica si se ingresó un número
            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine(); 
                System.out.println("--------------------------------------------");

                switch (opcion) {
                    case 1:
                        System.out.println("Has elegido Crear Pedido.");
                        pedido.crearPedido();
                        break;
                    case 2:
                    	System.out.println("Has elegido Crear Orden de Compra.");
                    	ordenCompra.solicitarDatosCliente();
                        Producto playera = OrdenCompra.ingresarDatosProducto(entrada, "Playera");
                        Producto sombrilla = OrdenCompra.ingresarDatosProducto(entrada, "Sombrilla");
                        Producto gorra = OrdenCompra.ingresarDatosProducto(entrada, "Gorra");
                        Producto pachon = OrdenCompra.ingresarDatosProducto(entrada, "Pachón");
                        Producto chumpa = OrdenCompra.ingresarDatosProducto(entrada, "Chumpa");
                        ordenCompra.guardarDatosEnArchivo(playera, sombrilla, gorra, pachon, chumpa);
                        System.out.println("Datos de la orden de compra guardados en OrdenCompra.txt");
                        break;
                    case 3:
                    	System.out.println("Has elegido Cargar Existencias.");
                    	System.out.println("Has elegido Cargar Existencias.");
                        try {
                            inventario.cargarDatosDesdeOrdenCompra("OrdenCompra.txt");
                            System.out.println("Existencias cargadas correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al cargar existencias: " + e.getMessage());
                        }
                        break;
                    case 4:
                    	System.out.println("Has elegido Facturar.");
                        factura.solicitarDatosCliente();
                        factura.generarFactura("factura.txt");
                        factura.mostrarFactura("factura.txt");
                        break;
                    case 5:
                    	System.out.println("Has elegido Descargar Existencias.");
                    	try {
                            calculoExistencia.actualizarInventario("factura.txt", "Inventario.txt");
                            System.out.println("Existencias actualizadas correctamente.");
                        } catch (IOException e) {
                            System.out.println("Error al actualizar existencias: " + e.getMessage());
                        }
                        break;
                    case 6:
                    	System.out.println("Has elegido Ver Existencias.");
                    	existencias.leerInventario("Inventario.txt");
                        break;
                    case 7:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } else {
                // Si no se ingresó un número, solicitamos nuevamente
                System.out.println("Por favor, ingresa un número válido.");
                System.out.println();
                System.out.println("--------------------------------------------");
                entrada.nextLine();
            }
        } while (opcion != 7);

        entrada.close();
    }
}
	