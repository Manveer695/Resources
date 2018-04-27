import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JLister extends JFrame{
    private JList jl;
    private String[] colorNames = {"black","blue","red","white"};
    private Color[] colors = {Color.BLACK,Color.BLUE,Color.RED,Color.WHITE};

    public JLister(){
        super("kidaan");
        setLayout(new FlowLayout());
        jl = new JList<>(colorNames);
        
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jl.setVisibleRowCount(3);
        add(new JScrollPane(jl));

        jl.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                
                getContentPane().setBackground(colors[jl.getSelectedIndex()]);
            }
        });
    }
}