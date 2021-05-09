import java.io.*;
import java.net.*;

public class JabberServer {
    public static int PORT = 8080;
        public static void main (String[] args)
            throws IOException{
        //PORT = Integer.parseInt(args[0]);
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: "+s);
        try {
            Socket socket = s.accept();
            try{
                System.out.println(
                    "Connection accepted: "+ socket);
                BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(
                            socket.getInputStream()));
                PrintWriter out =
                    new PrintWriter(
                        new BufferedWriter(
                            new OutputStreamWriter(
                                socket.getOutputStream())), true);

                while(true){
                    int num = in.read();
                    if(num==0) break;
                    System.out.println(num);
                    out.println(1);
                }
            } finally {
                System.out.println("closing...");
                socket.close();
            }
        } finally {
            s.close();
        }
    }
}