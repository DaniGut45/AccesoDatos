package RepasoFicheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_Optimizado_Temperaturas {
	private static RandomAccessFile RFile;
	private final static String DATOSFILEOUT = "FicheroAleatorioTemperaturas.dat";
	private final static int TAMANIO = 13;

	public static void main(String[] args) {
		try {
			RFile = new RandomAccessFile(new File(Ejercicio3_LeerFichero.RUTA_DATOS + DATOSFILEOUT), "rw");
			escribir("");
			menu();
			RFile.close();
		} catch (IOException e) {
			System.out.println("Error apertura/creación fichero");
			e.printStackTrace();
		}
	}

	/***
	 * Escribe el contenido de todo el fichero mes, temperatura minima y máxima) o
	 * modifica el valor de una posición determinada (mes, posición y nueva
	 * temperatura minima y/0 máxima se pasarán como parámetros) o añade
	 * temperaturas al final
	 * 
	 * @param mes     tres letras indicando el mes
	 * @param valores si vacio, inicializa el fichero con los valores mes,
	 *                temperatura maxima y minima de las 3 matrices si.
	 * @hidden
	 * @param Si tiene 2 valores, se toman como temperatura minima y maxima y se
	 *           añade al final.
	 * @hidden
	 * @param Si tiene 3 valores, el primero es tomado como posición a sobreescribir
	 *           con el String (mes) y los otros 2 enteros (temperatura minima y
	 *           maxima)
	 * 
	 * @return true si la operacion se realiza con exito, false en caso de error
	 * 
	 */

	public static boolean escribir(String mes, int... valores) {
		try {

			if (valores.length == 0) {
				String[] meses = { "ene", "feb", "mar", "abr", "may", "jun" };
				int[] temp_minima = { 0, -1, 4, 10, 15, 15 };
				int[] temp_maxima = { 17, 18, 20, 22, 24, 28 };
				for (int i = 0; i < meses.length; i++) {
					RFile.writeUTF(meses[i]);
					RFile.writeInt(temp_minima[i]);
					RFile.writeInt(temp_maxima[i]);
				}
				return true;
			} else if (valores.length == 2) {
				// Añadimos al final del fichero
				RFile.seek(RFile.length());
				RFile.writeUTF(mes);
				RFile.writeInt(valores[0]);
				RFile.writeInt(valores[1]);
				return true;
			} else if (valores.length == 3) {
				long posicion = (valores[0] - 1) * TAMANIO;
				if (posicion >= RFile.length() || valores[0] <= 0) {
					System.out.println("No existe ningún valor en esa posición");
					return false;
				}
				RFile.seek(posicion);
				RFile.writeUTF(mes);
				RFile.writeInt(valores[1]);
				RFile.writeInt(valores[2]);
				return true;
			} else {
				throw new Exception("Número de parametros incorrecto");
			}

		} catch (IOException e) {
			System.out.println("Error de escritura");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lee y muestra por consola el contenido de todo el fichero de acceso aleatorio
	 * o el valor de la posición pasada como parámetro
	 * 
	 * @param lugar
	 * @return número de elementos leidos
	 * @return o 0 en caso de leer de una posicióin pasada como parametro
	 * @return o -1 si error
	 */

	public static int leer(int... lugar) {

		try {

			if (lugar.length == 0) {
				int i = 0;
				RFile.seek(0);
				while (RFile.getFilePointer() != RFile.length()) {
					System.out.println(
							"mes:" + RFile.readUTF() + "\tmínima:" + RFile.readInt() + "\tmáxima:" + RFile.readInt());
					i++;
				}
				return i;
			}
			long posicion = (lugar[0] - 1) * TAMANIO;
			if (posicion >= RFile.length() || lugar[0] <= 0) {
				System.out.println("No existe ningún valor en esa posición");
				return -1;
			}
			RFile.seek(posicion);
			System.out
					.println("mes:" + RFile.readUTF() + "\tmínima:" + RFile.readInt() + "\tmáxima:" + RFile.readInt());

			return 0;
		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * muestra el menú de opciones
	 */
	public static void menu() {

		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int posicion, minima, maxima;
		String mes;

		while (!salir) {
			System.out.println("\n1. Leer fichero");
			System.out.println("2. Consultar una posición");
			System.out.println("3. Añadir al final");
			System.out.println("4. Modificar valor");
			System.out.println("5. Salir");
			try {
				System.out.println("Escribe una de las opciones: ");
				switch (sn.nextInt()) {
				case 1:
					System.out.println("LEYENDO EL CONTENIDO DEL FICHERO");
					leer();
					break;
				case 2:
					System.out.println("CONSULTANDO POSICIÓN DEL FICHERO");
					System.out.println("indique la posición a consultar (valor numérico): ");
					posicion = sn.nextInt();
					leer(posicion);
					break;
				case 3:
					System.out.println("AÑADIENDO ELEMENTO AL FINAL DEL FICHERO");
					System.out.println("indique el valor del mes(3 letras): ");
					mes = sn.next();

					System.out.println("indique el valor de la temperatura mínima: ");
					minima = sn.nextInt();

					System.out.println("indique el valor de la temperatura máxima: ");
					maxima = sn.nextInt();
					System.out.println(escribir(mes, minima, maxima) ? "Temperaturas añadidas correctamente"
							: "ERROR: Temperaturas no añadidas");
					break;
				case 4:
					System.out.println("MODIFICANDO VALOR EN POSICIÓN");
					System.out.println("indique posición: ");
					posicion = sn.nextInt();
					System.out.println("indique el valor del mes(3 letras): ");
					mes = sn.next();

					System.out.println("indique el valor de la temperatura mínima: ");
					minima = sn.nextInt();

					System.out.println("indique el valor de la temperatura máxima: ");
					maxima = sn.nextInt();
					System.out
							.println(escribir(mes, posicion, minima, maxima) ? "Temperaturas modificadas correctamente"
									: "ERROR: Valor no modificado");
					break;
				case 5:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 5");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes escribir un número");
				sn.next();
			}
		}
		sn.close();
	}
}