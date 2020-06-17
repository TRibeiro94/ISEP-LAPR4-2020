/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import eapli.base.machinemanagement.application.ListMachineService;
import eapli.framework.validations.Preconditions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Bernardo Carvalho
 */

public class MachineSession implements Runnable {

    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public MachineSession(Socket cli_s) {
        Preconditions.noneNull(cli_s);
        s = cli_s;
    }
    
    /**
     * Create ack message
     * @return ack message
     */
    public byte[] buildAckMessage(){
        byte[] ack = new byte[6];
        ack[0] = (byte) 0;
        ack[1] = (byte) 150;
        ack[2] = (byte) 0;
        ack[3] = (byte) 0;
        ack[4] = (byte) 0;
        ack[5] = (byte) 0;
        
        return ack;
    }
    
    /**
     * Create nack message
     * @return nack message
     */
    public byte[] buildNackMessage(){
        byte[] nack = new byte[6];
        nack[0] = (byte) 0;
        nack[1] = (byte) 151;
        nack[2] = (byte) 0;
        nack[3] = (byte) 0;
        nack[4] = (byte) 0;
        nack[5] = (byte) 0;
            
        return nack;
    }

    @Override
    public void run() {
        InetAddress clientIP;
        clientIP = s.getInetAddress();
        //System.out.println("New client connection from " + clientIP.getHostAddress()
                //+ ", port number " + s.getPort());
        String ipMachine = clientIP.getHostAddress();
        ListMachineService lms = new ListMachineService();
        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());
            
            byte[] ack = buildAckMessage();
            byte[] nack = buildNackMessage();
            
            byte[] b = new byte[6];
            sIn.read(b);
            
            int messageCode = AnalyseMessage.messageCode(b);
            
            while(messageCode != 0){
                sOut.write(nack);
                sIn.read(b);
                messageCode = AnalyseMessage.messageCode(b);
            }
            
            int idMachine = AnalyseMessage.idMachine(b);
            int firstMachineId = idMachine;

            boolean existsMachine = lms.checkIdMachine(String.valueOf(idMachine));
            
            while(true){
                try{
                
                if(!existsMachine || idMachine != firstMachineId){
                    sOut.write(nack);
                }
                
                switch (messageCode) {
                    case 0:
                        sOut.write(ack);
                        break;
                    case 1:
                        int quantity = AnalyseMessage.messageDataLength(b);
                        
                        byte[] b2 = new byte[255];
                        sIn.read(b2, 0, quantity);

                        String message = new String(b2, 0, quantity);
                        String idPL = lms.getIDPLByIdMachine(String.valueOf(idMachine));
                        
                        FileWriter fr = new FileWriter("LOGFiles/logPL_"+idPL+".txt", true);
                        fr.write(message.trim()+"\n");
                        fr.close();
                        sOut.write(ack);
                        
                        String[] messageParts = message.trim().split(";");
                        SaveMessage.saveMsg(messageParts, idPL);
                        break;
                    default:
                        sOut.write(nack);
                        break;
                }
                
                sIn.read(b, 0, b.length);
                messageCode = AnalyseMessage.messageCode(b);
                } catch(SocketException | ArrayIndexOutOfBoundsException e){
                    s.close();
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException");
        }
    }
}
