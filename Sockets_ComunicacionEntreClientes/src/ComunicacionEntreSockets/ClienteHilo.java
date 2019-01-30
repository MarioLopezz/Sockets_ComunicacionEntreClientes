/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacionEntreSockets;

import chat.ChatServer;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cursom
 */
public class ClienteHilo extends Thread{
    private Socket canaldecomunicacion;
    public ClienteHilo(Socket canaldecomunicacion){
        this.canaldecomunicacion=canaldecomunicacion;
    }

    @Override
    public void run() {
        while(!canaldecomunicacion.isClosed()){
            try {
                DataInputStream entrada=new DataInputStream(canaldecomunicacion.getInputStream());
                System.out.println(entrada.readUTF());
            }catch(SocketException e){
                try {
                    System.out.println("Cliente desconectado");
                    canaldecomunicacion.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
