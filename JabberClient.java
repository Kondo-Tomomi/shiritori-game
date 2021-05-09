import java.io.*;
import java.net.*;

public class JabberClient {
    public static int PORT = 8080;
    public static void main(String[] args)
                throws IOException {
        InetAddress addr =
            InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        //PORT = Integer.parseInt(args[0]);
        Socket socket =
            new Socket(addr, PORT);
        try{
            System.out.println("socket = " + socket);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream()));
            PrintWriter out =
                new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            socket.getOutputStream())), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
                        
            for(int i=0; i<5; i++){
                System.out.print("number : ");
                int reply = reader.read();
                out.println(reply);
                System.out.println(in.read());
            }
            out.println(0);
            } finally {
            System.out.println("closing...");
            socket.close();
        }
    } 
}
