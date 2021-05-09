public class Game {
    Player myself;  //自分と相手を記憶
    Player opp;
    public int status = 1;  // -1ならoppの勝ち、2ならmyselfの勝ち、100なら審議

    public Game(){  //Gameの開始
        myself = new Player();   //自分と相手を作り、名前を登録
        myself.Register();
        opp = new Player();
    }

    public static void Stop(int x){   //Sleepを呼び出す関数
        try{   Thread.sleep(x);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public void myTurnJudge(){   //両プレイヤーの乱数を生成、Judgementを呼び出す
        System.out.println("順番を決めるよ!!");
        while(true){
            if(myself.turn == -1){
                    myself.RandomNum();
                    opp.RandomNum();
                    Judgement();
            }
            else break;
        }
    }

    public void TurnPrint(){    //先攻後攻の表示
        if(myself.turn == 1){
            System.out.println("先攻 : "+myself.name);
            System.out.println("後攻 : "+opp.name);
        } else {
            System.out.println("先攻 : "+opp.name);
            System.out.println("後攻 : "+myself.name);
        }   
    }

    public void Judgement(){  //turn = -1 (初期値)で呼び出し、乱数の大きい方が先行に、一緒ならやり直し
        if(myself.myRandom > opp.myRandom){
            myself.turn = 1;
            opp.turn = 2;
        }
        else if(myself.myRandom < opp.myRandom){
            opp.turn = 1;
            myself.turn = 2;
        }
        Game.Stop(500);
    }

    public void setFirst(Player p){   //後攻のプレイヤーのmy_wordに「しりとり」をセット
        p.my_word = "しりとり";
        System.out.println("最初の文字は...「しりとり」です!!(\"とり\"から始まる単語を入力してね)");
        Stop(500);
    }

    public void myTurnFirst(){   //相手の単語を解析してstatusをセット
        if(myself.my_word!=null){
            if(Judge.Delibarate(opp.my_word)) status = 100;
            else if(!(Judge.WordLength(opp.my_word))) status = 2;
            else if(!(Judge.WordCheck(opp.my_word, myself.my_word))) status = 2;
        }
    }

    public void oppTurnFirst(){
        if(opp.my_word!=null){
            if(Judge.Delibarate(myself.my_word)) status = 100;
            else if(!(Judge.WordLength(myself.my_word))) status = -1;
            else if(!(Judge.WordCheck(myself.my_word, opp.my_word))) status = -1;
        }
    }

}