public class Judge {
    public static int ErrorNum;

    public static boolean Delibarate(String x){
        if(x.equals("審議")) ErrorNum = 0;
        return (x.equals("審議"));
    }

    public static boolean WordLength(String x){
        if(x.length()!=4) ErrorNum = 1;
        return (x.length()==4);
    }

    public static boolean WordCheck(String opp, String my){
        String nn = "ん";
        String o2 = String.valueOf(opp.charAt(2));
        if(!(opp.charAt(0)==my.charAt(2) && opp.charAt(1)==my.charAt(3)) || o2.equals(nn)){
            ErrorNum = 2;
            return false;
        }
        else return true;
    }
}

//javac -encoding UTF-8