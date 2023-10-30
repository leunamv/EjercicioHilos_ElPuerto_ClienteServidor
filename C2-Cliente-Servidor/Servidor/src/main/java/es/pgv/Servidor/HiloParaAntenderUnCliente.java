package es.pgv.Servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloParaAntenderUnCliente extends Thread{
    Socket sk;
    public HiloParaAntenderUnCliente(Socket sk){
        this.sk = sk;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            os = sk.getOutputStream(); 
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
         
			
             
            while(true){
                
                String linea1 = br.readLine();
                String linea2 = br.readLine();
                int A = Integer.parseInt(linea1.trim());
                int B = Integer.parseInt(linea2.trim());
                bw.write(Integer.toString(A+B));
                bw.newLine();
                bw.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HiloParaAntenderUnCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is != null) is.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloParaAntenderUnCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}