package ChatConHilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	ReplicadorMensajes replicadorMensaje;
	public Servidor(int puerto) {
		System.out.println("Soy un Servidor escuchando en: " + puerto);
		replicadorMensaje = new ReplicadorMensajes();
		int idCliente= 0;
		try {
			ServerSocket server = new ServerSocket(puerto);
			while(true) {
				Socket cliente = server.accept();
				replicadorMensaje.addCliente(++idCliente, cliente);
				ConexionCliente cc = new ConexionCliente(idCliente, cliente, replicadorMensaje);
				cc.start(); //ahora me desligo de este cliente y sigo esperando por otro
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor(5020);
	}
}
