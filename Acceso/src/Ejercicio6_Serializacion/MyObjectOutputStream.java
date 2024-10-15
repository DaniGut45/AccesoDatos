package Ejercicio6_Serializacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

	public MyObjectOutputStream(OutputStream out) throws IOException, SecurityException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {

	}

}
