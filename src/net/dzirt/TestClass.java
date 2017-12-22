package net.dzirt;

public class TestClass {
    static void metod1 ( int i) {
        i = i + 6;
    }

    static void metod2 (A a) {
        a.setC(12);
    }

    static void metod3 (Integer a) {
        a = a + 7;
    }



    public static void main(String[] args) {
        int x = 1;
        metod1(x);
        System.out.println(x);

        A xx = new A();
        xx.setC(2);
        metod2(xx);
        System.out.println(xx.getC());

        Integer xxx = 10;
        metod3(xxx);
        System.out.println(xxx);

    }
}
class A{
    int c;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}