package ChatBasico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteBasico {

	public ClienteBasico(String host, int puerto) {
		Socket clienteSocket = null;
		DataInputStream entrada;
		DataOutputStream salida;
		try {
			clienteSocket = new Socket(host, puerto);
			System.out.println("Soy un cliente");
			entrada = new DataInputStream(clienteSocket.getInputStream());
			salida = new DataOutputStream(clienteSocket.getOutputStream());
			salida.writeUTF("Te hablo desde el Cliente");//mensaje al servidor
			System.out.println(entrada.readUTF());//mensaje del servidor
			salida.close();
			entrada.close();
			clienteSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ClienteBasico("localhost",5099);
	}
}
