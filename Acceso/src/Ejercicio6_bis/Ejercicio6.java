package Ejercicio6_bis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import RepasoFicheros.Ejercicio3_LeerFichero;

public class Ejercicio6 {

	private final static String DATOSFILEOUT = "datosPersonasv2.dat";
	private static Scanner sc = new Scanner(System.in);
	private static ObjectOutputStream oOs;

	public static void main(String[] args) {
		inicializar();
		escribirObjeto(obtenerDatos());
		escribirObjeto(obtenerDatos());
		leerObjetos();
		try {
			oOs.close();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		}

	}

	public static void inicializar() {
		try {
			File file = new File(Ejercicio3_LeerFichero.RUTA_DATOS + DATOSFILEOUT);

			if (file.exists() && file.length() >= 0) {

				oOs = new MyObjectOutputStream(new FileOutputStream(file, true));

			} else {
				oOs = new ObjectOutputStream(new FileOutputStream(file, true));
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		}

	}

	public static Persona obtenerDatos() {

		Persona persona = new Persona();
		System.out.println("Datos de usuario:");
		System.out.println("\tNombre");
		persona.setNombre(new StringBuilder(sc.nextLine()));
		System.out.println("\tPrimer Apellido");
		persona.setApellido1(new StringBuilder(sc.nextLine()));
		System.out.println("\tSegundo Apellido");
		persona.setApellido2(new StringBuilder(sc.nextLine()));
		System.out.println("\tFecha de nacimiento (dd-MM-yyyy)");
		persona.setEdad((sc.nextInt()));
		return persona;
	}

	public static void escribirObjeto(Persona persona) {
		try {

			oOs.writeObject(persona);
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de escritura");
			e.printStackTrace();
		}

	}

	public static void leerObjetos() {
		try (ObjectInputStream oIS = new ObjectInputStream(
				new FileInputStream(new File(Ejercicio3_LeerFichero.RUTA_DATOS + DATOSFILEOUT)))) {
			while (true) {
				try {
					System.out.println((Persona) oIS.readObject());
				} catch (ClassNotFoundException e) {
					System.out.println();
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
		}

	}

}
