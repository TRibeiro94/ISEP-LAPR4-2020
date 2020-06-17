/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.application;

import eapli.base.messagemanagement.communication.TransmitConfigFileByTCP;

/**
 *
 * @author Tiago Ribeiro
 */
public class TransmitConfigFileByTCPController {
    
    
    public void sendConfigurationFileByTCP(String configFile, int machine) throws Exception{
        TransmitConfigFileByTCP tcp = new TransmitConfigFileByTCP();
        tcp.run(configFile, machine);
    }
}
