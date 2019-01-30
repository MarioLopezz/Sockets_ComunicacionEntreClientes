/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacionEntreSockets;

import ClientesSimultaneos.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cursom
 */
public class ServidorHilo extends Thread{
    Socket socket;
    String linea, nombre;
    
    int n;
    public ServidorHilo(Socket socket,int n){
        this.socket=socket;
        this.n=n;
    }

    @Override
    public void run() {
        try {
            DataInputStream entrada=new DataInputStream(socket.getInputStream());
            nombre=entrada.readUTF();  
            System.out.println("Acceso cliente "+(n+1)+" : "+nombre+" aceptado");
            
            do {
                linea=entrada.readUTF();
                for (int i = 0; i < Servidor.clientes.size(); i++) {
                    if(i!=n){
                        DataOutput salida=new DataOutputStream(Servidor.clientes.get(i).getOutputStream());
                        System.out.println(nombre+" : "+linea);
                        salida.writeUTF(nombre+" : "+linea);
                    }   
                }
            } while (!linea.equals("adios"));
            System.out.println("El cliente "+nombre+" ha salido");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
}
