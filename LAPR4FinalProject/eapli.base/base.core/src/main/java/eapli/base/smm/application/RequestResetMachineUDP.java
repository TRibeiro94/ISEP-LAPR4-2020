/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.smm.application;

import java.io.IOException;
import static java.lang.System.exit;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Ribeiro
 */
public class RequestResetMachineUDP {

    private static final byte RESET = 3;
    private static final byte VERSION = 1;
    private static final int PORT = 31206;
    private static final int TIMEOUT = 15000;

    public void run() {

        byte[] inData = new byte[6];
        byte[] outData = new byte[6];
        byte[] ipAddress = new byte[]{10, 8, 0, 82};

        outData[0] = VERSION;
        outData[1] = RESET;
        outData[2] = 0;
        outData[3] = 0;
        outData[4] = 0;
        outData[5] = 0;

        InetAddress address = null;
        try {
            address = InetAddress.getByAddress(ipAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(RequestResetMachineUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(RequestResetMachineUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramPacket udpPacketOut = new DatagramPacket(outData, outData.length, address, PORT);

        try {
            socket.send(udpPacketOut);
            System.out.println("Machine is rebooting...");
        } catch (IOException ex) {
            Logger.getLogger(RequestResetMachineUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //This is the receiving end
        DatagramPacket udpPacketIn = new DatagramPacket(inData, inData.length);
        udpPacketIn.setData(inData);
        udpPacketIn.setLength(inData.length);

        try {
            try {
                socket.setSoTimeout(TIMEOUT);
            } catch (SocketException e) {
                System.out.println("Socket Timed Out. No response from the machine was obtained for 15 seconds.\n");
                exit(0);
            }
            socket.receive(udpPacketIn);
            
            int x = Byte.toUnsignedInt(inData[1]);
            System.out.println("\nMachine rebooted successfully.\n");
            System.out.println("\nCode Read:" + x);
            System.out.println("Server IP Read: " + udpPacketIn.getAddress());
            System.out.println("Port Used: \n" + udpPacketIn.getPort()+"\n");

        } catch (IOException ex) {
            Logger.getLogger(RequestResetMachineUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        socket.close();
    }
}
