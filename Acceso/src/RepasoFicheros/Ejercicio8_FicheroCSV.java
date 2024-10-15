package RepasoFicheros;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio8_FicheroCSV {
	public final static String FICHEROCSV = "ejercicio08.csv";
	public static void main(String[] args) {
	    String csvFile = Ejercicio3_LeerFichero.RUTA_DATOS+FICHEROCSV;
	    String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Leer y limpiar la cabecera
            if ((line = br.readLine()) != null) {
                String cleanedHeader = line.replace("\"", "").replace(",", " ");
                System.out.println(cleanedHeader.trim()); // Imprimir cabecera
            }
            
            // Leer el resto de las líneas
            while ((line = br.readLine()) != null) {
                String cleanedLine = line.replace("\"", "").replace(",", " ");
                System.out.println(cleanedLine.trim()); // Imprimir sin comillas ni separadores
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




