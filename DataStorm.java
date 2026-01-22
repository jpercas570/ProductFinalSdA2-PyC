import java.io.File;
import java.util.Scanner;

public class DataStorm {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ALERTAS DATASTORM 1.0 ===");
        // Llamada al primer subproblema: Procesar el archivo
        procesarArchivo("datos.txt");

        System.out.println("=========================================");
        System.out.println("Análisis finalizado.");
    }

    /**
     * SUBPROBLEMA 1: Leer el archivo línea a línea
     */
    public static void procesarArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);
            // El bucle "while" recorre el archivo hasta que no queden más líneas
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                // Dividimos la línea por las comas
                String[] partes = linea.split(",");
                // Guardamos cada trozo en una variable
                String dia = partes[0];
                double temp = Double.parseDouble(partes[1]);
                int viento = Integer.parseInt(partes[2]);
                double humedad = Double.parseDouble(partes[3]);
                char state = partes[4].charAt(0);
                // Llamada al segundo subproblema para analizar los datos
                analizarAlertas(dia, temp, viento, humedad, state);
            }
            lector.close(); // Cerramos el archivo al terminar
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo leer el archivo. Comprueba que 'datos.txt' existe.");
        }
    }

    /**
     * SUBPROBLEMA 2: Lógica de decisiones
     * Aquí es donde el alumnado debe aplicar los conocimientos de
     * Programación Estructurada
     */
    public static void analizarAlertas(String dia, double temp, int viento, double humedad, char state) {
        // --- TAREA PARA EL ALUMNO ---
        // 1. Si la temperatura es mayor de 35, mostrar alerta de calor.
        // 2. Si el viento es mayor de 50, mostrar alerta de viento fuerte.
        // 3. (Opcional) Si la temperatura es menor de 0, mostrar alerta de helada.
        System.out.println("Analizando " + dia + "..."); // Mensaje de control
        // ESCRIBE AQUÍ TUS "IF"
        if (temp > 35) {
            System.out.println("ALERTA DE CALOR: La temperatura alcanzará los " + temp + "°C.");
        }
        if (temp < 0) {
            System.out.println("ALERTA DE HELADA: La temperatura es de " + temp + "°C.");
        }
        if (viento > 50) {
            System.out.println("ALERTA DE VIENTO FUERTE: El viento alcanza velocidades de " + viento + " km/h.");
        }
        if (state == 'L') {
            System.out.println("ALERTA DE LLUVIA: Hoy va a hacer un día lluvioso. No olvides tu paraguas.");
        }
        if (state == 'S') {
            System.out.println("Hoy va a hacer un día soleado. Disfrute.");
        }
        if (state == 'N') {
            System.out.println("Hoy va a hacer un día nublado.");
        }
        if (state == 'M') {
            System.out.println("Se alternarán momentos de sol y nubes.");
        }
        if (state == 'T') {
            System.out.println("ALERTA DE TORMENTA: Hoy va a hacer un día tormentoso. Con cuidado.");
        }
        // ----------------------------
    }
}