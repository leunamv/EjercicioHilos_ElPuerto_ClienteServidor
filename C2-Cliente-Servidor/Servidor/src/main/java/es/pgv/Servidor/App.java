package es.pgv.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App 
{
    public static void main( String[] args )
    {
        final int PORT = 40080;
        
        try {
            @SuppressWarnings("resource")
			ServerSocket sk = new ServerSocket(PORT);
            while(true){
                Socket socket = sk.accept();
                System.out.println("Alguien se conectó");
                HiloParaAntenderUnCliente hilo = new HiloParaAntenderUnCliente(socket);
                hilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
