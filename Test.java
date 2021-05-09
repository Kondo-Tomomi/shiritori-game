public class Test {
    public static void main(String[] args){
        String old = "Tomomi";
        String n = new String(old);
        System.out.println(old);
        System.out.println(n);
        System.out.println();
        old = "TOMOMI";
        n = "KONDOU";
        old = n;
        System.out.println(old);
        System.out.println(n);
        System.out.println();
        n = "ABCDEF";
        System.out.println(old);
        System.out.println(n);
    }
}