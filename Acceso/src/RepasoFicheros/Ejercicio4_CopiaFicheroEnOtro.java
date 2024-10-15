package RepasoFicheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio4_CopiaFicheroEnOtro {

	private final static String DATOSFILEIN = "imagen.jpg";

	private final static String DATOSFILEOUT = "imagen_copia.jpg";

	public static void main(String[] args) {
		try {
			FileInputStream fIS = new FileInputStream(new File(Ejercicio3_LeerFichero.RUTA_DATOS + DATOSFILEIN));
			FileOutputStream fOS = new FileOutputStream(new File(Ejercicio3_LeerFichero.RUTA_DATOS + DATOSFILEOUT));

			int leido;

			while ((leido = fIS.read()) != -1) {
				fOS.write(leido);
			}

			fIS.close();
			fOS.close();

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("El fichero no se puede leer o escribir");
			e.printStackTrace();
		}

	}

}