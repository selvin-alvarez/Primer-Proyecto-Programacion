package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ImprimirPedido {
	
	public static void imprimir() {
	
		try {
	        File archivo = new File("pedido.txt");
	        Scanner scanner = new Scanner(archivo);
	
	        while (scanner.hasNextLine()) {
	            String linea = scanner.nextLine();
	            System.out.println(linea);
	        }
	
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        System.err.println("Error: Archivo no encontrado.");
	    }
	}
}

