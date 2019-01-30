/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacionEntreSockets;

import ClientesSimultaneos.*;
import EcoSocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cursom
 */
public class Servidor extends Thread{

    
    final int puerto=5000;
    static ArrayList<Socket> clientes=new ArrayList<Socket>();
    public Servidor(){
        try {
            int x=0;
            ServerSocket server=new ServerSocket(puerto);
            System.out.println("Servidor activo");
            while(true){
                Socket socket=server.accept();
                clientes.add(socket);
                ServidorHilo c=new ServidorHilo(socket,x);
                c.start();
                x+=1;
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
    
}
