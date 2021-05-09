public class Message {
    public static String Fin(String x, String name){
        if(x.equals("win")) return "おめでとう!! "+name+" あなたの勝ちです☆";
        else if(x.equals("rose")) return "残念... "+name+" の負けです(T^T)";
        else return "Error...";
    }

    public static void Rule(){   //ルール説明のプリント
        String str;
        System.out.println("");
        System.out.println("");
        oppShiritori.Stop(500);
        System.out.println("GAME START !!!");
        System.out.println("ルール説明：");
        str = "この「脳トレしりとりゲーム」はちょっと制限が厳しいしりとりゲームです。";
        printRule(str);
        str = "許される言葉は【4文字】の【ひらがな】のみです。";
        printRule(str);
        str = "あなたは相手が出した単語の後半2文字から始まる言葉を返さなければなりません。";
        printRule(str);
        str = "例：せつめい -> めいかく -> かくやす ...";
        printRule(str);
        str = "もし文字数が4文字でなかったり、3文字目に「ん」がつく言葉を返してしまうと...";
        printRule(str);
        str = "あなたの負けとなってしまいます(;ω;)";
        printRule(str);
        System.out.println("");
        str = "【重要】また、相手の単語が存在しない言葉だと思ったら「審議」と打ってみてください。";
        printRule(str);
        str = "相手に単語を変えさせることができます。";
        printRule(str);
        System.out.println("");
        str = "さて。ルールはわかったかな？";
        printRule(str);
        System.out.println("");
        str = "それでは...";
        printRule(str);
        str = "Let's Start ♪";
        printRule(str);
        System.out.println("");
        System.out.println("");
        oppShiritori.Stop(1000);
    }
    
    public static void printRule(String str){
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            oppShiritori.Stop(20);
        }
        System.out.println();
        oppShiritori.Stop(1000);
    }
}

//javac -encoding UTF-8