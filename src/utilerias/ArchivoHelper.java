package utilerias;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ArchivoHelper {
    private static final String RUTA_ARCHIVOS = "/Users/rubenchilonllanos/Documents/UPN/V CICLO/POO/sgis_archivos/";
//    private static final String RUTA_ARCHIVOS = System.getProperty("user.home") + "/Users/rubenchilonllanos/Downloads/sgis_archivos";

    // Guardar archivo de texto plano
    public static boolean guardarArchivoTexto(String nombreArchivo, String contenido) {
        try {
            File carpeta = new File(RUTA_ARCHIVOS);
            if (!carpeta.exists()) {
                carpeta.mkdirs(); // Crea la carpeta si no existe
            }

            FileWriter fw = new FileWriter(RUTA_ARCHIVOS + nombreArchivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();

            System.out.println("Archivo guardado correctamente: " + nombreArchivo);
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
            return false;
        }
    }

    // Leer archivo de texto
    public static String leerArchivoTexto(String nombreArchivo) {
        try {
            return new String(Files.readAllBytes(Paths.get(RUTA_ARCHIVOS + nombreArchivo)));
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
            return null;
        }
    }

    // Eliminar archivo
    public static boolean eliminarArchivo(String nombreArchivo) {
        File archivo = new File(RUTA_ARCHIVOS + nombreArchivo);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return false;
    }

    // Verificar si archivo existe
    public static boolean archivoExiste(String nombreArchivo) {
        File archivo = new File(RUTA_ARCHIVOS + nombreArchivo);
        return archivo.exists();
    }

    // NUEVO: Copiar archivo desde ruta externa (JFileChooser)
    public static boolean copiarArchivoDesdeRuta(String rutaOrigenCompleta) {
        try {
            File archivoOriginal = new File(rutaOrigenCompleta);
            if (!archivoOriginal.exists()) return false;

            File carpeta = new File(RUTA_ARCHIVOS);
            if (!carpeta.exists()) carpeta.mkdirs();

            Path origen = archivoOriginal.toPath();
            Path destino = Paths.get(RUTA_ARCHIVOS + archivoOriginal.getName());

            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente.");
            return true;
        } catch (IOException e) {
            System.out.println("Error al copiar archivo: " + e.getMessage());
            return false;
        }
    }
    public static String encriptarMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al encriptar con MD5: " + e.getMessage());
            return "Error al encriptar con MD5: ";
        }
    }




}
