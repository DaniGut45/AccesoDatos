package RepasoFicheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio7_FicheroConfiguracion {
	private static Properties conf = new Properties();
	public final static String FICHEROTRABAJO = "configuracion.props";

	public static void main(String[] args) {
		creaConfiguracion();
		leerConfiguracion();
		modificaConfiguracion();
		leerConfiguracion();
	}

	private static void modificaConfiguracion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MODIFICANDO CONFIGURACION");
		System.out.println("user: ");
		String usuario = sc.nextLine();
		System.out.println("password: ");
		String password = sc.nextLine();
		System.out.println("server: ");
		String server = sc.nextLine();
		System.out.println("Valor a sumar al puerto: ");
		int puerto = Integer.valueOf(sc.nextLine());
		System.out.println("Nuevo mes para la fecha: ");
		int mes = Integer.valueOf(sc.nextLine());
		System.out.println("power: ");
		boolean power = Boolean.valueOf(sc.nextLine());
		sc.close();
		try {
			conf.store(new FileOutputStream(Ejercicio3_LeerFichero.RUTA_DATOS + FICHEROTRABAJO),
					"Fichero de configucarión");
			conf.setProperty("user", usuario);
			conf.setProperty("password", password);
			conf.setProperty("server", server);
			conf.setProperty("port", String.valueOf(puerto));
			// Hago lo mismo que ella pero con format que si no no funciona
			// Usar un DateTimeFormatter para leer la fecha en formato "dd-MM-yyyy"
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			// Parsear la fecha actual usando el formato adecuado y cambiar el mes
			conf.setProperty("date", String
					.valueOf(LocalDate.parse(conf.getProperty("date"), formatter).withMonth(mes).format(formatter)));

			conf.setProperty("power", String.valueOf(power));

			// Con store se guardan las modificaciones
			conf.store(new FileOutputStream(Ejercicio3_LeerFichero.RUTA_DATOS + FICHEROTRABAJO),
					"Fichero de configucarión");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void leerConfiguracion() {
		try {
			conf.load(new FileInputStream(Ejercicio3_LeerFichero.RUTA_DATOS + FICHEROTRABAJO));
			conf.list(System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void creaConfiguracion() {
		conf.setProperty("user", "usuario");
		conf.setProperty("password", "micontraseña");
		conf.setProperty("server", "localhost");
		conf.setProperty("port", "3306");
		conf.setProperty("date", "11-08-2022");
		conf.setProperty("power", "true");

		try {
			conf.store(new FileOutputStream(Ejercicio3_LeerFichero.RUTA_DATOS + FICHEROTRABAJO),
					"Fichero de configucarión");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
