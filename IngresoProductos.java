package proyecto;


import java.util.Scanner;

class IngresoProductos {
	
	// Inicializar variables
	private static double totalPlayeras = 0.0;
    private static double totalSombrillas = 0.0;
    private static double totalGorras = 0.0;
    private static double totalPachones = 0.0;
    private static double totalChumpas = 0.0;

    // Datos de Playera
    public static Playera ingresarDatosPlayera(Scanner entrada) {
        System.out.println("DATOS DE PLAYERA:");
        System.out.println();
        
        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Playera playera = new Playera(cantidad);
        playera.setOrigen(origen);
        playera.setModificada(modificada);
        
        double precioPlayeras = playera.getPrecio(); 
        totalPlayeras += precioPlayeras * cantidad;

        GuardarProductos.guardarEnArchivo(playera);

        return playera;
    }

    // Datos de Sombrilla
    public static Sombrilla ingresarDatosSombrilla(Scanner entrada) {
        System.out.println("DATOS DE SOMBRILLA");
        System.out.println();
        
        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Sombrilla sombrilla = new Sombrilla(cantidad);
        sombrilla.setOrigen(origen);
        sombrilla.setModificada(modificada);
        
        double precioSombrilla = sombrilla.getPrecio(); 
        totalSombrillas += precioSombrilla * cantidad;

        GuardarProductos.guardarEnArchivo(sombrilla);

        return sombrilla;
    }

    // Datos de Gorra
    public static Gorra ingresarDatosGorra(Scanner entrada) {
        System.out.println("DATOS DE GORRA");
        System.out.println();
        
        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Gorra gorra = new Gorra(cantidad);
        gorra.setOrigen(origen);
        gorra.setModificada(modificada);
        
        double precioGorra = gorra.getPrecio(); 
        totalGorras += precioGorra * cantidad;

        GuardarProductos.guardarEnArchivo(gorra);

        return gorra;
    }

    // Datos de Pachón
    public static Pachon ingresarDatosPachon(Scanner entrada) {
        System.out.println("DATOS DE PACHÓN");
        System.out.println();
        
        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Pachon pachon = new Pachon(cantidad);
        pachon.setOrigen(origen);
        pachon.setModificada(modificada);
        
        double precioPachon = pachon.getPrecio(); 
        totalPachones += precioPachon * cantidad;

        GuardarProductos.guardarEnArchivo(pachon);

        return pachon;
    }

    // Datos de Chumpa
    public static Chumpa ingresarDatosChumpa(Scanner entrada) {
        System.out.println("DATOS DE CHUMPA");
        System.out.println();
        
        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Chumpa chumpa = new Chumpa(cantidad);
        chumpa.setOrigen(origen);
        chumpa.setModificada(modificada);
        
        double precioChumpa = chumpa.getPrecio(); 
        totalChumpas += precioChumpa * cantidad;

        GuardarProductos.guardarEnArchivo(chumpa);

        return chumpa;
    }

    private static int obtenerCantidad(Scanner entrada) {
        System.out.print("Ingresa la cantidad: ");
        int cantidad = entrada.nextInt();

        while (cantidad <= 0) {
            System.out.println("Cantidad no válida. Debe ser mayor a 0.");
            System.out.print("Ingresa la cantidad: ");
            cantidad = entrada.nextInt();
        }
        return cantidad;
    }

    private static String obtenerOrigen(Scanner entrada) {
        System.out.print("¿Es local o importada? (local/importada): ");
        String origen = entrada.next().toLowerCase();

        while (!origen.matches("local|importada")) {
            System.out.println("Opción no válida. Debe ser 'local' o 'importada'.");
            System.out.print("¿Es local o importada? (local/importada): ");
            origen = entrada.next().toLowerCase();
        }
        return origen;
    }

    private static boolean obtenerModificacion(Scanner entrada) {
        System.out.print("¿Debe ser modificada? (si/no): ");
        String respuestaModificada = entrada.next().toLowerCase();

        while (!respuestaModificada.matches("si|no")) {
            System.out.println("Respuesta no válida. Debe ser 'si' o 'no'.");
            System.out.print("¿Debe ser modificada? (si/no): ");
            respuestaModificada = entrada.next().toLowerCase();
        }
        return respuestaModificada.equalsIgnoreCase("si");
    }

    public static double calcularTotalCombinado() {
        return totalPlayeras + totalSombrillas + totalGorras + totalPachones + totalChumpas;
    }
}
	
	