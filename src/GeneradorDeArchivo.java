import java.io.FileWriter;
import java.io.IOException;


class GeneradorDeArchivo {
    public static void generarArchivo(String contenido) {
        try (FileWriter writer = new FileWriter("resultado.txt")) {
            writer.write(contenido);
            System.out.println("El resultado ha sido guardado en 'resultado.txt'.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}