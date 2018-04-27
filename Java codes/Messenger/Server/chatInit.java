import javax.swing.JFrame;

public class chatInit{
    public static void main(String[] args) {
        socketClass sc = new socketClass();
        sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sc.startRun();
    }
}