package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

//import org.apache.log4j.Logger;

public class SocketThreadServer extends Thread {

//    private static final Logger logger = Logger.getLogger(SocketThreadServer.class);

    private Socket socket;

    public SocketThreadServer(Socket socket){
        this.socket=socket;
    }

    //단순 문자열 Thread server
    public void run(){
        BufferedReader br = null;
        PrintWriter pw = null;
        String connIp = null;
        try{
            connIp = socket.getInetAddress().getHostAddress();
            System.out.println(connIp + "에서 연결 시도.");

            /*
             * 접근한 소켓 계정의 ip를 체크한다. KTOA 연동 모듈인지 체크
             * 정상이면 먼저 정상 접근되었음을 알린다.
             **/
            br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            pw = new PrintWriter(socket.getOutputStream());
            String read;
            while ((read = br.readLine()) != null) {
                System.out.println("[From "+ connIp +"] " + read);
            }
        }catch(IOException e){
            System.out.println(e);
        } finally{
            try{
                if(pw != null) { pw.close();}
                if(br != null) { br.close();}
                if(socket != null){socket.close();}
            }catch(IOException e){
                System.out.println(e);
            }
        }
        System.out.println("bye ~ " + connIp);
    }

    private String getTime() {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time;
    }
}
