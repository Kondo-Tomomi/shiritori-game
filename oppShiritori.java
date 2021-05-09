import java.io.*;
import java.net.*;

public class oppShiritori {   //myShiritoriを実行してから実行すること
    private static final int PORT = 8080;
    //private static final String addr = "192.168.xx.xxx";  
                        //自分のLAN内でのIPアドレスを入れると同じネットワーク内のPC同士で対戦できる

    public static void Stop(int x){
        try{   Thread.sleep(x);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static String Register(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("プレイヤー名を入力してください：　");
            String name = reader.readLine();
            System.out.println(name + "が登録されました"); 
            return name;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    public static void main(String[] args)
                throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("接続中...");
        //PORT = Integer.parseInt(args[0]);
        Socket socket = new Socket(addr, PORT);
        try{
            System.out.println("対戦相手が見つかりました");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())), true);
            
            //コネクションの確立

            //自分の名前を送る
            String my_name = Register();
            out.println(my_name);
            String str = in.readLine();
            System.out.println(str);
            Message.Rule();

            //順番が送られてくる
            for(int i=0; i<3; i++){
                str = in.readLine();
                System.out.println(str);
            }
            

            //最初は「しりとり」だというメッセージが送られてくる
            str = in.readLine();
            System.out.println(str);
            Stop(1000);
            
            while(true){
                str = in.readLine();  //サーバーから次何をやるかが送られてくる
                if(str.equals("OppTurn")){  //相手の入力を受け取る
                    str = in.readLine();
                    System.out.print(str);
                    str = in.readLine();
                    System.out.println(str);
                } else if(str.equals("YourTurn")){  //自分の入力を送信
                    str = in.readLine();
                    System.out.print(str);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    str = reader.readLine();  //入力値をstrへ
                    out.println(str);   //送信
                } else if(str.equals("Error")){
                    str = in.readLine();
                    System.out.println(str);
                } else if(str.equals("myDeliberate")){
                    for(int i=0; i<3; i++){
                        str = in.readLine();
                        System.out.println(str);
                    }
                    str = in.readLine();
                    System.out.print(str);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    str = reader.readLine();  //入力値をstrへ
                    out.println(str);   //送信
                } else if(str.equals("oppDeliberate")){
                    str = in.readLine();
                    System.out.println(str);
                    str = in.readLine();
                    System.out.print(str);
                    str = in.readLine();
                    System.out.println(str);
                } else if(str.equals("finish")) break;   //ゲーム終了
            }

            str = in.readLine();
            System.out.println(str);

            } finally {
            System.out.println("closing...");
            socket.close();
        }
    } 
}