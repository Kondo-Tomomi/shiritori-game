import java.io.*;
import java.net.*;

public class myShiritori {   //先に実行
    private static final int PORT = 8080;
    public static String[] ErrorSentense = {    "ちょっと待って!!その日本語おかしくない？",
                                                "文字数が異なります",
                                                "ルール違反です"   };
        public static void main (String[] args)
            throws IOException{
        //PORT = Integer.parseInt(args[0]);
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("接続待機中...");
        try {
            Socket socket = s.accept();
            try{
                System.out.println("対戦相手が見つかりました");
                BufferedReader in = new BufferedReader( 
                    new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

                //コネクションの確立

                //ゲームの開始
                Game g = new Game(); 
                System.out.println("相手の入力を待っています...");
                g.opp.name = in.readLine();
                System.out.println("対戦相手は... "+g.opp.name+" です☆");
                out.println("対戦相手は... "+g.myself.name+" です☆");
                Message.Rule();
                
                //順番を決める
                out.println("順番を決めるよ!!");
                g.myTurnJudge();
                g.TurnPrint();
                if(g.myself.turn == 1){
                    out.println("先攻 : "+g.myself.name);
                    out.println("後攻 : "+g.opp.name);
                } else {
                    out.println("先攻 : "+g.opp.name);
                    out.println("後攻 : "+g.myself.name);
                } 
                Game.Stop(1000);

                //「しりとり」をセット
                if(g.myself.turn == 1) g.setFirst(g.opp);
                else g.setFirst(g.myself);
                out.println("最初の文字は...「しりとり」です!!(\"とり\"から始まる単語を入力してね)");
                Game.Stop(1000);

                if(g.myself.turn == 1){
                    g.myTurnFirst();  //相手が正しい単語を返しているかチェック
                    out.println("OppTurn");
                    out.println(g.myself.name + "：　");
                    System.out.print("あなたの番です：　");
                    g.myself.Input();
                    out.println(g.myself.my_word);
                    Game.Stop(1000);
                }

                while(g.status == 1){
                    //相手のターン
                    g.oppTurnFirst(); //相手が正しい単語を返しているかチェック
                    if(g.status == -1 || g.status == 2) break;
                    if(g.status == 100){
                        out.println("myDeliberate");
                        out.println(ErrorSentense[Judge.ErrorNum]);
                        Game.Stop(500);
                        out.println("対戦相手がこの単語は存在しないと考えたようです。");
                        System.out.println("審議が通りました。考え中...");
                        g.myself.my_word = g.myself.x;
                        Game.Stop(500);
                        out.println("他の単語を入力してください!!");
                        Game.Stop(500);
                        out.println("もう一度：　");
                        g.status = 1;
                        if(g.status == -1 || g.status == 2) break;
                    } else {
                        out.println("YourTurn");
                        out.println("あなたの番です：　");
                    }
                    System.out.print(g.opp.name + "：　");
                    String str = in.readLine();
                    if(str.equals("審議")) g.opp.x = new String(g.opp.my_word);
                    g.opp.my_word = str;
                    System.out.println(str);

                    Game.Stop(1000);

                    //自分のターン
                    g.myTurnFirst(); //相手が正しい単語を返しているかチェック
                    if(g.status == -1 || g.status == 2) break;
                    if(g.status == 100){
                        out.println("oppDeliberate");
                        System.out.println(ErrorSentense[Judge.ErrorNum]);
                        Game.Stop(500);
                        System.out.println("対戦相手がこの単語は存在しないと考えたようです。");
                        out.println("審議が通りました。　考え中...");
                        g.opp.my_word = g.opp.x;
                        Game.Stop(500);
                        System.out.println("他の単語を入力してください!!");
                        Game.Stop(500);
                        System.out.print("もう一度：　");
                        g.status = 1;
                    } else {
                        out.println("OppTurn");
                        System.out.print("あなたの番です：　");
                    }
                    out.println(g.myself.name + "：　");
                    g.myself.Input();
                    out.println(g.myself.my_word);
                }

                if(g.status == 2 || g.status == -1){
                    out.println("Error");
                    System.out.println(ErrorSentense[Judge.ErrorNum]);
                    out.println(ErrorSentense[Judge.ErrorNum]);
                }

                out.println("finish");

                //終了画面
                if(g.status == 2){
                    String str = Message.Fin("win", g.myself.name);
                    System.out.println(str);
                    str = Message.Fin("rose", g.opp.name);
                    out.println(str);
                } else if(g.status == -1){
                    String str = Message.Fin("rose", g.myself.name);
                    System.out.println(str);
                    str = Message.Fin("win", g.opp.name);
                    out.println(str);
                } else {
                    String str = "あれれ…おかしいな";
                    System.out.println(str);
                    out.println(str);
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