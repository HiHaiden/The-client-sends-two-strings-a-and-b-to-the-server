// Клиентская часть:
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class laba61 implements Runnable {

    BufferedReader br1, br2, br3;
    PrintWriter pr1, pr2;
    Socket socket;
    Thread t1, t2;
    String in1 = "", in2 = "", out1 = "";

    public laba61() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            socket = new Socket("localhost", 5000);
            t1.start();
            t2.start();

        } catch (Exception e) {
        }
    }

    public void run() {
        try {
            if (Thread.currentThread() == t2) {
                do {
                    br1 = new BufferedReader(new InputStreamReader(System.in));
                    br2 = new BufferedReader(new InputStreamReader(System.in));
                    pr1 = new PrintWriter(socket.getOutputStream(), true);
                    pr2 = new PrintWriter(socket.getOutputStream(), true);
                    in1 = br1.readLine();
                    in2 = br2.readLine();
                    pr1.println(in1);
                    pr2.println(in2);

                    br3 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out1 = br3.readLine();
                    System.out.println("Server Result: " + out1);
                } while (out1.equals("ENDING"));
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new laba61();
    }
}