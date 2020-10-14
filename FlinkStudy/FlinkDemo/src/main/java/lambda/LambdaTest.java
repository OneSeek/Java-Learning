package lambda;

public class LambdaTest {
    public static void main(String[] args) {

        print(() -> System.out.println("x"));
        print2(a -> System.out.println(a*100));
    }
    static void print(Interface1 x){
        x.run();
    }
    static void print2(Interface2 x){
        x.run(1);
    }
}

