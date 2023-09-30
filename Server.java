// Серверная часть: 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class laba6 implements Runnable {

    ServerSocket serversocket;
    BufferedReader br1, br2;
    PrintWriter pr3;
    Socket socket;
    Thread t1, t2;
    String out1="", out2="";
    int count1 = 0, count2 = 0;

    public laba6() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            serversocket = new ServerSocket(5000);
            System.out.println("Server is waiting. . .");
            socket = serversocket.accept();
            System.out.println("Client connected with Ip " + socket.getInetAddress().getHostAddress());
            t1.start();
            t2.start();

        } catch (Exception e) {
        }
    }

    public void run() {
        try {
            if (Thread.currentThread() == t1) {
                do {
                    br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out1 = br1.readLine();
                    out2 = br2.readLine();
                    for(int i = 0; i<out1.length(); i++) {
                        count1++;
                    }
                    for(int i = 0; i<out2.length(); i++) {
                        count2++;
                    }
                    System.out.println("First Line: " + out1);
                    System.out.println("Second Line: " + out2);
                    String all = out1 + count1 + out2 + count2;
                    System.out.println("Result: " + all);

                    pr3 = new PrintWriter(socket.getOutputStream(), true);
                    pr3.println(all);
                } while (pr3.equals("ENDING"));
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new laba6();
    }
}