package pp.aul.pt;

interface I {
    void m();
}

class A implements I {
    @Override
    public void m() {
        System.out.println("m implemented in A");
    }
}

class B {
    public void goal(I impl) {
        System.out.print("B is executing goal: ");
        impl.m();
    }
}

public class App {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        b.goal(() -> System.out.println("lambda"));
    }
}


