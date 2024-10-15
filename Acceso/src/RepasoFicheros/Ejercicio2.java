package RepasoFicheros;
import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {

		File dirActual = new File(System.getProperty("user.dir"));
		File[] hijos = dirActual.listFiles();
		System.out.println("Nombre del directorio de trabajo actual: " + dirActual.getAbsolutePath() + " tiene "
				+ hijos.length + " ficheros o directorios contenidos");
		for (File file : hijos) {
			System.out.println("\t" + file.getName() + "\t" + (file.isFile() ? "F" : "D"));
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Dame el nombre del fichero o del directorio a analizar");
		String hijos2 = scanner.nextLine();
		File file = new File(hijos2);
		if (!file.exists()) {
			System.out.println("El fichero o directorio no existe.");
		} else {
			// Comprobar si es un fichero o un directorio
			if (file.isFile()) {
				// Si es un fichero, mostrar su tamaño y permisos
				System.out.println("Es un fichero.");
				System.out.println("Tamaño: " + file.length() + " bytes");
				System.out.println("Puede leerse: " + (file.canRead() ? "Sí" : "No"));
				System.out.println("Puede modificarse: " + (file.canWrite() ? "Sí" : "No"));
				System.out.println("Puede ejecutarse: " + (file.canExecute() ? "Sí" : "No"));
			} else if (file.isDirectory()) {
				// Si es un directorio, mostrar los elementos que contiene
				System.out.println("Es un directorio.");
				File[] hijos3 = file.listFiles();
				if (hijos3 != null) {
					System.out.println("Contiene " + hijos3.length + " ficheros o directorios:");
					for (File f : hijos3) {
						System.out.println("\t" + f.getName() + "\t" + (f.isFile() ? "F" : "D"));
					}
				} else {
					System.out.println("No se puede acceder a los contenidos del directorio.");
				}
			}
		}
		scanner.close();
	}

}
