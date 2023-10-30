package es.pgv.Cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App 
{
	   final static int PORT = 40080;
	    final static String HOST = "localhost";
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        try {
	            Socket sk = new Socket(HOST, PORT);
	            
	            enviarMensajesAlServidor(sk);
	        } catch (IOException ex) {
	            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    private static void enviarMensajesAlServidor(Socket sk) {
	        OutputStream os = null;
	        InputStream is = null;
	        try {
	            os = sk.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);
	            
	            is = sk.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            
	            @SuppressWarnings({ "resource", "unused" })
				Scanner sc = new Scanner(System.in);
	            String linea;
	            int i = 1;
	            while(i <= 6){
	            	
	                bw.write(Integer.toString(i));
	                bw.newLine();
	                bw.write(Integer.toString(i));
	                bw.newLine();
	                bw.flush();
	                linea = br.readLine();
	                System.out.println("El servidor dice que la suma de " + i + "+" + i + " es igual a " + linea);
	                i++;
	            }
	            
	            
	        } catch (IOException ex) {
	            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                if(os != null) os.close();
	            } catch (IOException ex) {
	                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
}
