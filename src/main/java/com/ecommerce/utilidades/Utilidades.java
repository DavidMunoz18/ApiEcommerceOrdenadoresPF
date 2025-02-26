package com.ecommerce.utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {

    // Ruta base donde se guardarán los archivos de log
    private static final String RUTA_BASE = "fichero\\";
    
    /**
     * Método para obtener el nombre del archivo de log con la fecha actual.
     * @return Nombre del archivo con fecha.
     */
    private static String obtenerNombreArchivoLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(new Date());
        return RUTA_BASE + "api_" + fecha + ".txt";
    }

    /**
     * Método para escribir los logs en el archivo.
     * @param nivel Nivel del log (INFO, ERROR, etc.).
     * @param clase Clase desde donde se llama el log.
     * @param metodo Método desde donde se llama el log.
     * @param mensaje Mensaje a registrar en el log.
     */
    public static void escribirLog(String nivel, String clase, String metodo, String mensaje) {
        // Obtener el nombre del archivo con la fecha
        String rutaArchivo = obtenerNombreArchivoLog();
        // Verificar si el archivo de log existe y si tiene más de 1 mes de antigüedad
        eliminarArchivosAntiguos(rutaArchivo);

        // Escribir el log en el archivo
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Obtener la traza de error si existe.
            String trazaError = new Throwable().getStackTrace()[1].toString();

            // Escribir en el archivo el log con el formato deseado.
            pw.println(nivel + " - " + clase + " - " + metodo + " - " + mensaje + " - " + trazaError);
        } catch (IOException e) {
            // Si ocurre un error escribiendo el log, se registra en un archivo de error.
            System.err.println("No se pudo escribir en el archivo de log: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar archivos de log antiguos (más de 1 mes de antigüedad).
     * @param archivo Log que se va a verificar.
     */
    private static void eliminarArchivosAntiguos(String archivo) {
        File archivoLog = new File(archivo);

        // Verificar si el archivo existe y si tiene más de 1 mes de antigüedad
        if (archivoLog.exists()) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1); // Restamos 1 mes a la fecha actual
            Date fechaLimite = cal.getTime();

            if (archivoLog.lastModified() < fechaLimite.getTime()) {
                // El archivo tiene más de 1 mes de antigüedad, lo eliminamos
                boolean eliminado = archivoLog.delete();
                if (eliminado) {
                    System.out.println("Archivo de log antiguo eliminado: " + archivoLog.getName());
                } else {
                    System.err.println("No se pudo eliminar el archivo de log antiguo: " + archivoLog.getName());
                }
            }
        }
    }
}
