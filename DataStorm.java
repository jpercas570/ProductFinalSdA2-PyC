import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataStorm {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ALERTAS DATASTORM 1.0 ===");
        // Llamada al primer subproblema: Procesar el archivo
        procesarArchivo("datos.txt");

        System.out.println("=========================================");
        System.out.println("Analisis finalizado.");
    }

    /**
     * SUBPROBLEMA 1: Leer el archivo línea a línea
     */
    @SuppressWarnings("ConvertToTryWithResources") //esto me lo ha recordado vs code yo no tengo ni idea jajajajaj
    public static void procesarArchivo(String nombreArchivo) {
        try {
            //NO TOCAR ESTO DE AQUI ABAJO PORQUE LLEVO COMO 2 HORAS INTENTANDO QUE FUNCIONE Y ME DUELE LA CABEZA DIOS
            String rutaCompleta = System.getProperty("user.dir") + File.separator + "JaviProject" + File.separator + "ProductFinalSdA2-PyC" + File.separator + "src" + File.separator + nombreArchivo;
            File archivo = new File(rutaCompleta);
            Scanner lector = new Scanner(archivo);
            // El bucle "while" recorre el archivo hasta que no queden más líneas
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                // Dividimos la línea por las comas
                String[] partes = linea.split(", ");
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
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se pudo leer el archivo. Comprueba que 'datos.txt' existe.");
        }
        catch (NumberFormatException e) {
            System.out.println("ERROR: Algun dato esta mal. Mira bien los puntos, las comas y que no haya doubles en vez de enteros en sus sitios correspondientes.");
        }
        // He tenido que generar este último pq puse un dato mal dios y me tiré como 30 minutos buscando el error pensando que era del archivo jajajajaja
    }

    /**
     * SUBPROBLEMA 2: Analizar los datos y generar alertas
     */
    public static void analizarAlertas(String dia, double temp, int viento, double humedad, char state) {
        System.out.println("Analisis para " + dia + ":");
        
        //ALERTAS
        boolean alertaTormenta = (state == 'T');
        boolean alertaCalor = (temp > 35);
        boolean alertaViento = (viento > 30);

        //ESTADOS DEL DIA
        boolean soleado = (state == 'S');
        boolean nublado = (state == 'N');
        boolean lluvioso = (state == 'L');
        boolean mixto = (state == 'M');

         // Mostrar condiciones del día
        
        if (alertaTormenta) {
            System.out.println("  ALERTA DE TORMENTA! Cuidado con los rayos.");
        }
        if (alertaCalor) {
            System.out.println("  ALERTA DE CALOR EXTREMO! Temperaturas superiores a los 35°C.");
        }
        if (alertaViento) {
            System.out.println("  ALERTA DE VIENTO FUERTE! Vientos superiores a los 30 km/h.");
        }
        if (soleado) {
            System.out.println("  Dia soleado, disfrute.");
        }
        if (nublado) {
            System.out.println("  Dia nublado.");
        }
        if (lluvioso && temp >= 5) {
            System.out.println("  Dia lluvioso.");
        }
        else if (lluvioso && temp < 5) {
            System.out.println("  ALERTA DE NIEVE O GRANIZO! Temperaturas bajo 5°C con lluvia.");
        }
        if (mixto) {
            System.out.println("  Dia mixto, se alternaran momentos de Sol y nubes.");
        }
        if (!alertaTormenta && !alertaCalor && !alertaViento) {
            System.out.println("  Condiciones normales. No hay alertas.");
        }
        System.out.println(); // Línea en blanco para separar días
    }
}