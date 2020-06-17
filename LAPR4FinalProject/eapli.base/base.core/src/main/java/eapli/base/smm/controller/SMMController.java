/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.smm.controller;

import eapli.base.smm.application.MonitorizeMachineByUdp;
import eapli.base.smm.application.RequestResetMachineUDP;

/**
 *
 * @author Tiago Ribeiro
 */
public class SMMController {
    
    public void monitorizeMachines() throws Exception{
        MonitorizeMachineByUdp udpCli = new MonitorizeMachineByUdp();
        udpCli.run();
    }
    
    public void resetRequest() throws Exception{
        RequestResetMachineUDP reset = new RequestResetMachineUDP();
        reset.run();
    }
}
