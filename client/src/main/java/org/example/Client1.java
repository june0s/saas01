package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Client1 {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public Client1(String ip, int port) {
        try {
            // 서버에 요청 보내기
            socket = new Socket(ip, port);
            System.out.println(socket.getInetAddress().getHostAddress() + "에 연결됨");

            // 메시지 받기
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            // 메세지 전달
            int cnt = 0;
            while (cnt < 100) {
                String sendMsg = getUuid();
                System.out.println(sendMsg);
                pw.println(sendMsg);
                pw.flush();

                Thread.sleep(2000);
                cnt++;
            }
            // 응답 출력
//            System.out.println(br.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 소켓 닫기 (연결 끊기)
            try {
                if(socket != null) { socket.close(); }
                if(br != null) { br.close(); }
                if(pw != null) { pw.close(); }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getUuid() {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String uuid = UUID.randomUUID().toString();
        return time + " " +uuid;
    }
}
