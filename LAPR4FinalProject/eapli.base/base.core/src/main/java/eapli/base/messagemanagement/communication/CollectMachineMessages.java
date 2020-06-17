/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author Bernardo Carvalho
 */
public class CollectMachineMessages implements Runnable {

    static final int SERVER_PORT = 31222;
    static final String TRUSTED_STORE = "SSL\\serverJ.jks";
    static final String KEYSTORE_PASS = "forgotten";
    //static ServerSocket sock;

    @Override
    public void run() {
        SSLServerSocket sock = null;
        Socket cliSock;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + SERVER_PORT);
            System.exit(1);
        }
        while (true) {
            try {
                cliSock = sock.accept();
                new Thread(new MachineSession(cliSock)).start();
            } catch (IOException ex) {
                Logger.getLogger(CollectMachineMessages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
