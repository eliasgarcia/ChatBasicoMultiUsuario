package ChatBasico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBasico {

	public ServidorBasico(int puerto) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream entrada;
		DataOutputStream salida;
		try {
			serverSocket = new ServerSocket(puerto);
			System.out.println("Soy un Servidor y escucho en puerto: " + puerto);
			socket = serverSocket.accept();
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
			System.out.println(entrada.readUTF());//mensaje del cliente
			salida.writeUTF("Te hablo desde el Servidor");//mensaje al cliente
			salida.close();
			entrada.close();
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new ServidorBasico(5099);
	}
}
