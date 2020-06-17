/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.smm.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Tiago Ribeiro
 */
public class ClientTimeoutUDP {
    static InetAddress targetIP; 
	
    public static void run() throws Exception { 
		
        byte[] data = new byte[300]; 
	String frase; 
	targetIP = InetAddress.getByName("255.255.255.255"); 
	DatagramSocket sock = new DatagramSocket(); 
	sock.setBroadcast(true); 
	DatagramPacket udpPacket = new DatagramPacket(data, data.length, targetIP, 9999); 

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 

	while(true) { 
            System.out.print("Request sentence to send (\"exit\" to quit): "); 
            frase = in.readLine(); 
	
            if(frase.compareTo("exit") == 0){
                break;
            } 
			
            udpPacket.setData(frase.getBytes()); 
            udpPacket.setLength(frase.length()); 
            sock.send(udpPacket); 
            udpPacket.setData(data); 
            udpPacket.setLength(data.length); 
            sock.receive(udpPacket); 
            frase = new String( udpPacket.getData(), 0, udpPacket.getLength()); 
            System.out.println("Received reply: " + frase); 
	} 
		
        sock.close(); 
    } 
    
    
}
