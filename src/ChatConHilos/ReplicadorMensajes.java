package ChatConHilos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ReplicadorMensajes {
	private Map<Integer, Socket> clienteMap;
	
	public ReplicadorMensajes() {
		clienteMap = new HashMap<>();
	}
	
	public void addCliente(Integer id,Socket cliente) {
		clienteMap.put(id, cliente);
	}
	
	public void replicarMensajes(Integer id,String mensaje) {
		for (Integer idCliente: clienteMap.keySet()) {
			if(idCliente!=id && !clienteMap.get(idCliente).isClosed()) {
				try {
					DataOutputStream salida = new DataOutputStream(clienteMap.get(idCliente).getOutputStream());
					salida.writeUTF(mensaje);
//					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
