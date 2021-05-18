package pt.ual.pp.lab5;

interface I {
    void m(int i, String s);
}

//class A implements I {
//    @Override
//    public void m(int i, String s) {
//        System.out.println("m implemented in A (" + i + ", " + s + ")");
//    }
//}

class B {
    public void goal(I impl) {
        System.out.print("B is executing goal: ");
        impl.m(7, "Java");
    }
}

public class App {
    public static void main(String[] args) {
        B b = new B();
//        A a = new A();
//        b.goal(a);
        b.goal((x, y) -> System.out.println("m implemented in lambda ("+x+", "+y+")"));
    }
}