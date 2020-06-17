/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import java.util.Arrays;

/**
 *
 * @author Bernardo Carvalho
 */
public class AnalyseMessage {
    
    public static int messageCode(byte[] b){
        
        return Byte.toUnsignedInt(b[1]);
    }
    
    public static int idMachine(byte[] b){
        byte[] machineCode = Arrays.copyOfRange(b, 2, 4);
        int idMachine = Byte.toUnsignedInt(machineCode[0]) + (256 * Byte.toUnsignedInt(machineCode[1]));
        
        return idMachine;
    }
    
    public static int messageDataLength(byte[] b){
        byte[] dataLength = Arrays.copyOfRange(b, 4, 6);
        int quantity = Byte.toUnsignedInt(dataLength[0]) + (256 * Byte.toUnsignedInt(dataLength[1]));
        
        return quantity;
    }
    
}
