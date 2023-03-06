package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App {

//    private static final Logger logger = Logger.getLogger(App.class);

    private static final int PORT_NUMBER = 4432;

    public static void main(String[] args) throws IOException{

        String myIp = getMyIp();
//        PropertyConfigurator.configure(System.getProperty("user.dir") + "/server/conf/log4j.properties");
        System.out.println("+ my ip = " + myIp);
        System.out.println(":::                                                :::");
        System.out.println(":::       Socket Application  Process Start        :::");
        System.out.println(":::                                                :::");

        try(ServerSocket server = new ServerSocket(PORT_NUMBER)){
            while(true){
                Socket connection = server.accept();
                Thread task = new SocketThreadServer(connection);
                task.start();
            }
        }catch(IOException e){
            System.out.println(e);
        }
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
