package pt.ual.pp.lab5;

interface I {
    void m(int i);
}

class A implements I {
    @Override
    public void m(int i) {
        System.out.println("m implemented in A (" + i + ")" );
    }
}

class B {
    public void goal(I i) {
        System.out.print("B is executing goal: ");
        i.m(42);
    }
}

public class App {
    public static void main(String[] args) {
        new B().goal(new A());
        new B().goal((x) -> System.out.println("m implemented in lambda ("+x+")") );
    }
}
