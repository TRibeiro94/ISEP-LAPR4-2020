/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Tiago Ribeiro
 */
public class TransmitConfigFileByTCP {

    private static final int VERSION = 1;
    private static final int CONFIG_CODE = 2;
    private static final int SIZE = 6;
    private static Socket socket;

    public void run(String configFile, int machine) throws Exception {
        this.start(configFile, machine);
    }

    public void start(String configFile, int machine) throws Exception {

        byte[] ipAddress = new byte[]{10, 8, 0, 82};
        byte[] data = configFile.getBytes();
        byte[] outData = new byte[data.length + SIZE];

        int dataSize = configFile.length();

        outData[0] = (byte) VERSION;
        outData[1] = (byte) CONFIG_CODE;
        outData[2] = (byte) (machine & 0xFF);                                   //masks all but the lowest eight bits
        outData[3] = (byte) (((machine >> 8) & 0xFF));                          //discards the lowest 8 bits by moving all bits 8 places to the right
        outData[4] = (byte) (dataSize & 0xFF);
        outData[5] = (byte) (((dataSize >> 8) & 0xFF));

        for (int i = 0; i < data.length; i++) {
            outData[i + SIZE] = data[i];
        }

        InetAddress address = InetAddress.getByAddress(ipAddress);

        try {
            socket = new Socket(address, 31207);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection.");
            System.exit(1);
        }

        DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());
        DataInputStream sIn = new DataInputStream(socket.getInputStream());

        sOut.write(outData);
        System.out.println("\nConfiguration File was sent successfully.\n");

        byte[] inData = new byte[6];

        sIn.read(inData);
        int inMessage = Byte.toUnsignedInt(inData[1]);

        switch (inMessage) {
            case -1:
                System.out.println("Error reading from the socket.");
                System.exit(1);
            case 150:
                System.out.println("Configuration file was successfully established at machine " + machine +". Ack received.");
                break;
            case 151:
                System.out.println("There was an error establishing the configuration file at the server side. Nack received.");
                break;
            default:
                System.out.println(inMessage);
                break;
        }

        socket.close();
    }
}
