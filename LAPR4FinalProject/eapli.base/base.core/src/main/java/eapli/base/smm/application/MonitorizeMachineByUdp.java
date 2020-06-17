/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.smm.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * --------------------------------------------------------------------------------------------------------------------------
 * offset 0 - 1 byte : VERSION
 * offset 1 - 1 byte : MESSAGE CODE (0/3)
 * offset 2 - 2 byte : MACHINE ID (0)
 * offset 4 - 2 byte : Number of bytes stored in the following RAW DATA field.
 *                     Number = (first byte + 256 x second byte)
 *                     The total length of the message is (6 + DATA LENGTH) bytes.
 *                     DATA LENGTH may be zero, meaning there’s no RAW DATA, and thus the total message length is 6 bytes.
 * offset 6 - DATA LENGTH bytes:  Data to be interpreted by end applications, usually a text content.
 * --------------------------------------------------------------------------------------------------------------------------
 * 
 * @author Tiago Ribeiro
 */
public class MonitorizeMachineByUdp {
    
    private static final byte HELLO = 0;
    private static final byte RESET = 3;
    private static final byte VERSION = 1;
	
    public void run() throws Exception { 
        this.start();
    } 
    
    public void start() throws Exception{
        final long time = (1000 * 30);  //30 seconds
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {

        @Override
        public void run() {
            
            byte[] inData = new byte[3];
            byte[] outData = new byte[6];
            byte[] ipAddress = new byte[] {10, 8, 0, 82};  
        
            outData[0] = VERSION;
            outData[1] = HELLO;
            outData[2] = 0;
            outData[3] = 0;
            outData[4] = 0;
            outData[5] = 0;
        
            InetAddress address = null;  
            try {
                address = InetAddress.getByAddress(ipAddress);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MonitorizeMachineByUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket();
            } catch (SocketException ex) {
                Logger.getLogger(MonitorizeMachineByUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            DatagramPacket udpPacketOut = new DatagramPacket(outData, outData.length, address, 31206); 

            try {
                socket.send(udpPacketOut);
            } catch (IOException ex) {
                Logger.getLogger(MonitorizeMachineByUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            // RECEIVE
            DatagramPacket udpPacketIn = new DatagramPacket(inData, inData.length);
            
            udpPacketIn.setData(inData);
            udpPacketIn.setLength(inData.length);
            
            try {
                socket.setSoTimeout(20000);
                
                socket.receive(udpPacketIn);
                
                String read = new String(udpPacketIn.getData());
                System.out.println("\nCode Read:" + read);
                System.out.println("Server IP Read: "+ udpPacketIn.getAddress());
                System.out.println("Port Used: " + udpPacketIn.getPort()+"\n");
                
            } catch (IOException ex) {
                Logger.getLogger(MonitorizeMachineByUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(Arrays.toString(udpPacketIn.getData()));
            socket.close();
        }
        };
        timer.scheduleAtFixedRate(task, 0, time);
    }
}
