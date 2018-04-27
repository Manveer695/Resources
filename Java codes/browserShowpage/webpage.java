import java.applet.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class webpage extends JApplet{
    Map<String, URL> webpages;
    public webpage(){
        add(new JLabel("hi"), BorderLayout.NORTH);
        List<String> titles = new ArrayList<String>();
        
        webpages = new HashMap<>();
        InfoWebpage();
        JList jl = new JList(titles.toArray());
        add(jl,BorderLayout.CENTER);

        jl.addListSelectionListener(
            new ListSelectionListener(){
            
                @Override
                public void valueChanged(ListSelectionEvent arg0) {
                    Object selected = jl.getSelectedValue();
                    URL newDoc = webpages.get(selected.toString());
                    AppletContext browser = getAppletContext();
                    browser.showDocument(newDoc);
                    
                }
            }
        );
    } 
    public void InfoWebpage(){
        String title, addr;
        int i = 0;
        title = getParameter("title"+i);
        while(title != null){
            addr = getParameter("address"+i);
            try{
                webpages.put(title,new URL(addr));
            }
            catch(MalformedURLException ex){

            }
            i++;
            title = getParameter("title"+i);
        }
     }
}