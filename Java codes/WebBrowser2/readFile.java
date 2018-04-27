import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class readFile extends JFrame{
    JTextField jtf;
    JEditorPane jep;

    public readFile(){
        super("great!");
        jtf = new JTextField("Enter the url!");
        jep = new JEditorPane();

        jep.setEditable(false);
        jtf.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try{
                        jep.setPage(arg0.getActionCommand());
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        );

        jep.addHyperlinkListener(
            new HyperlinkListener(){
            
                @Override
                public void hyperlinkUpdate(HyperlinkEvent arg0) {
                    if(arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
                        try{
                            jep.setPage(arg0.getURL());
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                        jtf.setText(arg0.getURL().toString());
                    }    
                }
            }
        );
        add(jtf, BorderLayout.NORTH);
        add(jep, BorderLayout.CENTER);

    }
}