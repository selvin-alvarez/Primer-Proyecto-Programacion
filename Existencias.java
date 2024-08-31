package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Existencias {
	
	public void leerInventario(String archivoInventario) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoInventario))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de inventario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


