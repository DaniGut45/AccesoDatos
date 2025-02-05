package Ejercicio6_bis;

import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private StringBuilder nombre;
	private StringBuilder apellido1;
	private StringBuilder apellido2;
	private int edad;

	@Override
	public String toString() {
		return apellido1 + " " + apellido2 + ", " + nombre + ", " + "(" + edad + ")";
	}

	public StringBuilder getNombre() {
		return nombre;
	}

	public Persona() {
		super();
	}

	public Persona(StringBuilder nombre, StringBuilder apellido1, StringBuilder apellido2, int edad) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
	}

	public void setNombre(StringBuilder nombre) {
		this.nombre = nombre;
	}

	public StringBuilder getApellido1() {
		return apellido1;
	}

	public void setApellido1(StringBuilder apellido1) {
		this.apellido1 = apellido1;
	}

	public StringBuilder getApellido2() {
		return apellido2;
	}

	public void setApellido2(StringBuilder apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
