package ChatConHilos;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionCliente extends Thread {
	// esta clase solo tiene que escucha mensajes del cliente
	private Integer id;
	private Socket cliente;
	private DataInputStream entrada;
	private ReplicadorMensajes replicadorMensaje;

	public ConexionCliente(Integer id, Socket cliente, ReplicadorMensajes replicadorMensaje) {
		this.id = id;
		this.cliente = cliente;
		this.replicadorMensaje = replicadorMensaje;
	}

	public void run() {
		try {
			replicadorMensaje.replicarMensajes(id, "Se ha unido un participante.");//se avisa a los demas clientes
			entrada = new DataInputStream(cliente.getInputStream());
			while (true) {
				replicadorMensaje.replicarMensajes(id, entrada.readUTF()); // replica el mensaje a los demas clientes
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
