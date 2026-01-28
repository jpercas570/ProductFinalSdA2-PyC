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
                // Llamada al segundo subproblema para analizar los datos
                analizarAlertas(dia, temp, viento);
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
    public static void analizarAlertas(String dia, double temp, int viento) {
        // --- TAREA PARA EL ALUMNO ---
        // 1. Si la temperatura es mayor de 35, mostrar alerta de calor.
        // 2. Si el viento es mayor de 50, mostrar alerta de viento fuerte.
        // 3. (Opcional) Si la temperatura es menor de 0, mostrar alerta de helada.
        System.out.println("Analizando " + dia + "..."); // Mensaje de control
        // ESCRIBE AQUÍ TUS "IF"

        // 1. Si la temperatura es mayor de 35, mostrar alerta de calor.
        if (temp > 35)
        {
        System.out.println("---ALERTA DE CALOR---");
        }
        // 2. Si el viento es mayor de 50, mostrar alerta de viento fuerte.
        if (viento > 50)
        {
        System.out.println("---ALERTA DE VIENTO FUERTE---");
        }
        // 3. (Opcional) Si la temperatura es menor de 0, mostrar alerta de helada.
        if (temp < 0)
        {
        System.out.println("---ALERTA DE HELADA---");
        }
    }
}