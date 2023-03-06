package org.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class App{
    public static void main( String[] args ) {
        String ip = System.getenv("DSERVER_IP");
        String myIp = getMyIp();
        System.out.println("+ my ip = " + myIp);
        System.out.println("+ server ip = " + ip);
        int port = 4432;
        new Client1(ip, port);
    }
    private static String getMyIp() {
        String myIp = null;
        try {
            myIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return myIp;
    }
}

