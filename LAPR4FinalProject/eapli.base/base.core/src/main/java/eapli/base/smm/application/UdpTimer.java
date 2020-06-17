/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.smm.application;

import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author Tiago Ribeiro
 */
public class UdpTimer {

    public void run() {
        final long time = (1000 * 30);  //30 seconds
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println(new Date());
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, time);
    }
}
