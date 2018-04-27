
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class tuna extends JFrame{
    public tuna(){
        super("fre");
        setLayout(new FlowLayout());
        JTextField jtx = new JTextField("ht");
        add(jtx);
    }
    public int foo(){
        return 5;
    }
    public void foo(int r){

    }
    public void foo(int r, int re){

    }
}