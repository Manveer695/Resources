import java.util.*;

public class threads{
    public static void main(String[] args) {
        Thread t1 = new Thread(new branch("one"));
        Thread t2 = new Thread(new branch("one2"));
        Thread t3 = new Thread(new branch("one3"));
        Thread t4 = new Thread(new branch("one4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}