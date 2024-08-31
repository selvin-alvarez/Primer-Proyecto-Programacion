package proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Factura {
	
	// Atributos
	private String numeroFactura;
    private String nombreCliente;
    private String direccionCliente;
    private String nitCliente;
	private Scanner entrada;

    public Factura() {
        this.numeroFactura = generarNumeroFactura();
    }

    private String generarNumeroFactura() {
        Random random = new Random();
        int numero = random.nextInt(1000000); // Genera un número aleatorio de 6 dígitos
        return String.format("%06d", numero);
    }
    
    // Datos para factura
    public void solicitarDatosCliente() {
        entrada = new Scanner(System.in);
        System.out.print("Ingresa el nombre del cliente: ");
        nombreCliente = entrada.nextLine();
        while (!nombreCliente.matches("[a-zA-Z\\s]+")) {
            System.out.println("Nombre no válido. Debe contener solo letras.");
            System.out.print("Ingresa el nombre del cliente: ");
            nombreCliente = entrada.nextLine();
        }

        System.out.print("Ingresa la dirección del cliente: ");
        direccionCliente = entrada.nextLine();
        while (!direccionCliente.matches("[a-zA-Z0-9\\s,.-]+")) {
            System.out.println("Dirección no válida. Puede contener letras, números y símbolos (,.-).");
            System.out.print("Ingresa la dirección del cliente: ");
            direccionCliente = entrada.nextLine();
        }

        System.out.print("Ingresa el NIT del cliente: ");
        nitCliente = entrada.nextLine();
        while (!nitCliente.matches("\\d+")) {
            System.out.println("NIT no válido. Debe contener solo números.");
            System.out.print("Ingresa el NIT del cliente: ");
            nitCliente = entrada.nextLine();
        }
    }

    private void leerPedido(String archivoPedido, BufferedWriter escritor) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoPedido))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine();
            }
        }
    }
    
    // Facturar
    public void generarFactura(String archivoFactura) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoFactura))) {
            escritor.write("No. Factura: " + numeroFactura);
            escritor.newLine();
            escritor.write("Nombre del Cliente: " + nombreCliente);
            escritor.newLine();
            escritor.write("Dirección del Cliente: " + direccionCliente);
            escritor.newLine();
            escritor.write("NIT del Cliente: " + nitCliente);
            escritor.newLine();
            escritor.write("============================================");
            escritor.newLine();
            leerPedido("pedido.txt", escritor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Mostrar en pantalla factura
    public void mostrarFactura(String archivoFactura) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoFactura))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}