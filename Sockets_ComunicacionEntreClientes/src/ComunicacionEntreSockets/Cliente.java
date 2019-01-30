/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacionEntreSockets;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cursom
 */
public class Cliente{
    private Socket canaldecomunicacion;
    public Cliente(Socket canaldecomunicacion){
        this.canaldecomunicacion=canaldecomunicacion;
    }
    public static void main(String[] args) {
        String texto=null;
        
        try {
            Scanner sc=new Scanner(System.in);
            Socket canaldecomunicacion=new Socket("localhost",5000);
            Cliente cliente=new Cliente(canaldecomunicacion);
            ClienteHilo ch=new ClienteHilo(canaldecomunicacion);
            ch.start();
            System.out.println("Conectado. Escribe \"adios\" Para desconectarte");
            DataOutput salida=new DataOutputStream(canaldecomunicacion.getOutputStream());
            System.out.println("Introduce tu nickname");
            texto=sc.nextLine();
            salida.writeUTF(texto);
            do{
                texto=sc.nextLine();
               salida.writeUTF(texto);
            }while(!texto.equals("adios"));
            canaldecomunicacion.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
