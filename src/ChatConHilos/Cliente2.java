package ChatConHilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
	String nombreCliente;
	public Cliente2(String nombreCliente,String host, int puerto) {
		this.nombreCliente = nombreCliente;
		Socket clienteSocket = null;
		DataOutputStream salida;
		try {
			clienteSocket = new Socket(host, puerto);
			System.out.println("Soy el cliente "+nombreCliente);
			salida = new DataOutputStream(clienteSocket.getOutputStream());
			Thread hiloEscuchar = new Thread(new Escuchar(new DataInputStream(clienteSocket.getInputStream())));
			hiloEscuchar.start();
			Scanner teclado = new Scanner(System.in);
			while(true) {
				salida.writeUTF(nombreCliente +" dice: "+teclado.nextLine());// mensaje del servidor
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public class Escuchar implements Runnable {
		DataInputStream entrada;
		public Escuchar(DataInputStream entrada) {
			this.entrada = entrada;
		}
	    @Override
	    public void run() {
	    	try {
				while(true) {
					System.out.println(entrada.readUTF());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
	}
	
	public static void main(String[] args) {
		new Cliente2("Cliente2","localhost", 5020);
	}
}
