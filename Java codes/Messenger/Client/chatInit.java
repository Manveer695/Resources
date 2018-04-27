import javax.swing.JFrame;

public class chatInit{
    public static void main(String[] args) {
        socketClass sc = new socketClass("127.0.0.1");
        sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sc.startRun();
    }
}