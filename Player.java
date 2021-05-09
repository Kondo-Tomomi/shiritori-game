import java.io.*;
import java.util.Random;

public class Player {
    int myRandom;
    int turn = -1;
    String name;
    String my_word;
    String x;

    public void Register(){   //情報の登録
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("プレイヤー名を入力してください：　");
            String name = reader.readLine();
            System.out.println(name + "が登録されました"); 
            this.name=name;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void Input(){
        //if(!opp.my_word.equals("しりとり")) System.out.println(opp.name+"のターン：　"+opp.my_word);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
        try{
            String word = reader.readLine();
            if(word.equals("審議")) x = new String(my_word); 
            this.my_word=word;
        } catch (IOException e) {
            System.out.println(e);
        }
   }

    public void RandomNum(){
        Game.Stop(1000);
        Random r = new Random();
        myRandom = r.nextInt(100);
        //System.out.println(name + "'s RandomNum  "+myRandom);
    }
}

//javac -encoding UTF-8