import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class WeatherData {
    private int clima = -1;
    private int dias = 0;
    private int estacion = -1;
    private char eleccion;
    private double simutemp = 0.0;
    private int simuviento = 0;
    private double simuhumedad = 0.0;
    private char estado;
    private String rutaCompleta = System.getProperty("user.dir") + File.separator + "JaviProject" + File.separator + "ProductFinalSdA2-PyC" + File.separator + "src" + File.separator + "datos.txt";
    private File archivo = new File(rutaCompleta);

    public WeatherData() {}

    // Getters
    public int getClima() { return clima; }
    public int getDias() { return dias; }
    public int getEstacion() { return estacion; }
    public char getEleccion() { return eleccion; }
    public double getSimutemp() { return simutemp; }
    public int getSimuviento() { return simuviento; }
    public double getSimuhumedad() { return simuhumedad; }
    public char getEstado() { return estado; }
    public String getRutaCompleta() { return rutaCompleta; }
    public File getArchivo() { return archivo; }

    // Setters
    public void setClima(int clima) { this.clima = clima; }
    public void setDias(int dias) { this.dias = dias; }
    public void setEstacion(int estacion) { this.estacion = estacion; }
    public void setEleccion(char eleccion) { this.eleccion = eleccion; }
    public void setSimutemp(double simutemp) { this.simutemp = simutemp; }
    public void setSimuviento(int simuviento) { this.simuviento = simuviento; }
    public void setSimuhumedad(double simuhumedad) { this.simuhumedad = simuhumedad; }
    public void setEstado(char estado) { this.estado = estado; }
}

public class WeatherGenerator {
    public static void main(String[] args) {
        WeatherData data = new WeatherData();

        System.out.println("=== GENERADOR DE CLIMA WEATHERGEN Alpha 0.1 ===");
        generarArchivo(data.getArchivo(), "datos.txt");
        preguntaUser(data);
        escribirArchivo(data.getArchivo(), data);
        System.out.println("=========================================");
        System.out.println("Prediccion generada.");
    }

    // GENERAR ARCHIVO
    public static void generarArchivo(File archivo, String nombreArchivo) {
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo generado: " + archivo.getName());
            } else {
                archivo.delete();
                archivo.createNewFile();
                System.out.println("Archivo borrado y posteriormente generado: " + archivo.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    // PREGUNTAS AL USUARIO
    public static void preguntaUser(WeatherData data) {
        try (Scanner scn = new Scanner(System.in)) {
            while (data.getDias() <= 0) {
                System.out.println("Introduce el numero de dias que quieras generar");
                data.setDias(scn.nextInt());
            }
            while (data.getEleccion() != 'Y' && data.getEleccion() != 'N' && data.getEleccion() != 'n' && data.getEleccion() != 'y') {
                System.out.println("Deseas aleatorizar los parametros? (Y/N)");
                System.out.println("Y = Si, la maquina hara todo por mi");
                System.out.println("N = No, deseo elegir clima, estacion, ...");
                data.setEleccion(scn.next().charAt(0));
            }
            if (data.getEleccion() == 'Y' || data.getEleccion() == 'y') {
                Random rnd = new Random();
                data.setClima(rnd.nextInt(3));
                data.setEstacion(rnd.nextInt(4));
            } else if (data.getEleccion() == 'N' || data.getEleccion() == 'n') {
                while (data.getClima() < 0 || data.getClima() > 2) {
                    System.out.println("En que clima quieres desarrollar tu prediccion?");
                    System.out.println("0 = Clima frio");
                    System.out.println("1 = Clima templado");
                    System.out.println("2 = Clima calido");
                    data.setClima(scn.nextInt());
                }
                while (data.getEstacion() < 0 || data.getEstacion() > 3) {
                    System.out.println("En que estacion del año quieres desarrollar tu prediccion?");
                    System.out.println("0 = Primavera");
                    System.out.println("1 = Verano");
                    System.out.println("2 = Otoño");
                    System.out.println("3 = Invierno");
                    data.setEstacion(scn.nextInt());
                }
            }
        }
    }

    // DATOS DE CAMBIOS DE LAS ESTACIONES
    public static int[] elecEstacion(WeatherData data) {
        int temp = 0, lluvia = 0, viento = 0, humedad = 50;
        switch (data.getEstacion()) {
            case 0 -> {
                temp = 1;
                lluvia = 3;
                viento = 5;
                humedad = 10;
            }
            case 1 -> {
                temp = 5;
                lluvia = 0;
                viento = 0;
                humedad = -20;
            }
            case 2 -> {
                temp = -3;
                lluvia = 5;
                viento = 5;
                humedad = 20;
            }
            case 3 -> {
                temp = -7;
                lluvia = 5;
                viento = 10;
                humedad = 10;
            }
            default -> {}
        }
        return new int[]{temp, lluvia, viento, humedad};
    }

    // SIMULADOR DE TEMPERATURAS ORIGINALES
    public static void tempOrigSim(WeatherData data) {
        Random rnd = new Random();
        double simutemp = 0.0;
        switch (data.getClima()) {
            case 0:  // Clima frío
                simutemp = rnd.nextDouble() * 20;
                break;
            case 1:  // Clima templado
                simutemp = 10 + rnd.nextDouble() * 15;
                break;
            case 2:  // Clima cálido
                simutemp = 25 + rnd.nextDouble() * 10;
                break;
            default:
                break;
        }
        simutemp += elecEstacion(data)[0];
        simutemp = Math.round(simutemp * 100.0) / 100.0;
        data.setSimutemp(simutemp);
    }

    // SIMULADOR DE VIENTO
    public static void vientoOrigSim(WeatherData data) {
        Random rnd = new Random();
        int simuviento = 0;
        switch (data.getClima()) {
            case 0:  // Clima frío
                simuviento = rnd.nextInt(75);
                break;
            case 1:  // Clima templado
                simuviento = rnd.nextInt(60);
                break;
            case 2:  // Clima cálido
                simuviento = rnd.nextInt(40);
                break;
            default:
                break;
        }
        simuviento += elecEstacion(data)[2];
        data.setSimuviento(simuviento);
    }

    // SIMULADOR DE HUMEDAD
    public static void humedadOrigSim(WeatherData data) {
        Random rnd = new Random();
        double simuhumedad = 0;
        switch (data.getClima()) {
            case 0:  // Clima frío
                simuhumedad = rnd.nextDouble() * 75;
                break;
            case 1:  // Clima templado
                simuhumedad = rnd.nextDouble() * 60;
                break;
            case 2:  // Clima cálido
                simuhumedad = rnd.nextDouble() * 40;
                break;
            default:
                break;
        }
        simuhumedad += elecEstacion(data)[3];
        simuhumedad = Math.round(simuhumedad * 100.0) / 100.0;
        data.setSimuhumedad(simuhumedad);
    }

    // GENERADOR DE DATOS POR DÍA
    public static void escribirArchivo(File archivo, WeatherData data) {
        try (FileWriter escritor = new FileWriter(archivo)) {
            String[] diasSemana = {"Domingo", "Sábado", "Viernes", "Jueves", "Miércoles", "Martes", "Lunes"};
            // Simulaciones iniciales
            tempOrigSim(data);
            vientoOrigSim(data);
            humedadOrigSim(data);
            
            for (int i = data.getDias(); i > 0; i--) {
                double tempDia = data.getSimutemp();
                int vientoDia = data.getSimuviento();
                double humedadDia = data.getSimuhumedad();
                
                double[] modificados = modificarDatos(tempDia, vientoDia, humedadDia);
                tempDia = modificados[0];
                vientoDia = (int) modificados[1];
                humedadDia = modificados[2];
                char estadoDia = (char) modificados[3];
                
                String dia = diasSemana[i % 7];
                // Escribe los valores temporales para este día
                escritor.write(dia + ", " + tempDia + ", " + vientoDia + ", " + humedadDia + ", " + estadoDia + "\n");
                
                data.setSimutemp(Math.round((data.getSimutemp() + (-2 + new Random().nextDouble() * 4)) * 100.0) / 100.0);
                data.setSimuhumedad(Math.max(0, Math.round((data.getSimuhumedad() + (-10 + new Random().nextDouble() * 20)) * 100.0) / 100.0));
                data.setSimuviento(Math.max(0, data.getSimuviento() + (-10 + new Random().nextInt(21))));
            }
        } catch (IOException e) {
            System.out.println("Por algun motivo no se ha podido escribir en el archivo.");
        }
    }

    public static double[] modificarDatos(double tempActual, int vientoActual, double humedadActual) {
        Random rnd = new Random();
        String[] estados = {"S", "S", "S", "S", "N", "N", "N", "M", "M", "L", "T"}; // Estados: S=soleado, N=nublado, M=mixto, L=lluvioso, T=tormenta
        char estado = estados[rnd.nextInt(estados.length)].charAt(0);

        // Valores temporales para este día
        double tempDia = tempActual;
        int vientoDia = vientoActual;
        double humedadDia = humedadActual;

        // Aplicar cambios temporales si hay lluvia o tormenta
        if (estado == 'T' || estado == 'L') {
            tempDia -= rnd.nextInt(1, 4);
            humedadDia = Math.min(100, humedadDia + rnd.nextDouble() * 20);
            vientoDia += rnd.nextInt(11);
        }

        // Aplicar cambios aleatorios temporales
        double cambioTemp = -2 + rnd.nextDouble() * 4;
        int cambioViento = -10 + rnd.nextInt(21);
        double cambioHumedad = -10 + rnd.nextDouble() * 20;

        tempDia = Math.round((tempDia + cambioTemp) * 100.0) / 100.0;
        humedadDia = Math.max(0, Math.round((humedadDia + cambioHumedad) * 100.0) / 100.0);
        vientoDia = Math.max(0, vientoDia + cambioViento);

        // Devuelve los valores modificados temporalmente + estado
        return new double[]{tempDia, vientoDia, humedadDia, estado};
    }
}