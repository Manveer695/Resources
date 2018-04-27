import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.net.*;

public class socketClass extends JFrame{
    JTextArea msgs;
    JTextField msgWrite;
    ObjectInputStream inputObj;
    ObjectOutputStream outputObj;
    String serverIP;
    Socket conn;

    public socketClass(String serveIP){
        
        super("Client");
        serverIP = serveIP;
        msgWrite = new JTextField("");
        msgs = new JTextArea();
        msgs.setEditable(false);
        msgWrite.setEditable(false);

        add(msgWrite, BorderLayout.SOUTH);
        add(new JScrollPane(msgs), BorderLayout.CENTER);

        msgWrite.addActionListener(
            new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    sendMsg(arg0.getActionCommand());     
                    msgWrite.setText(""); 
                }
            }
        );

        setSize(300,150);
        setVisible(true);
    }

    public void startRun(){
        try{
            
            while(true){
                try{
                    waitForConn();
                    setupStreams();
                    whileChatting();
                }
                catch(EOFException eofex){
                    showMsg("Conn Ended!");
                }
                finally{
                    closeConn();
                }
            }
        }
        catch(IOException ioex){
            ioex.printStackTrace();
        }
    }

    private void waitForConn() throws IOException{
        showMsg("Waiting to connect!");
        conn = new Socket(InetAddress.getByName(serverIP),6879);
        showMsg("Connected to"+conn.getInetAddress().getHostName());
    }

    private void setupStreams() throws IOException{
        outputObj = new ObjectOutputStream(conn.getOutputStream());
        outputObj.flush();
        inputObj = new ObjectInputStream(conn.getInputStream());        
        showMsg("Streams set up dude!");
    }

    private void whileChatting() throws IOException{
        String mess = "You are connected!"; 
        sendMsg(mess);
        
        msgWrite.setEditable(true);
        do{
            try{
                mess = (String) inputObj.readObject();
                showMsg(mess);
            }
            catch(ClassNotFoundException cnfex){
                showMsg("Client sending some shit!");
            }  
        }while(!mess.equals("END"));
    }

    private void closeConn(){
        showMsg("Closing connections!");
        msgWrite.setEditable(false);
        try{
            inputObj.close();
            outputObj.close();
            conn.close();
        }
        catch(IOException ioex){
            showMsg("i/p o/p problems dude!");
        }
    }

    private void sendMsg(String msg){
        try{
            outputObj.writeObject(new String(msg));
            outputObj.flush();
            showMsg(msg);
        }
        catch(IOException ioex){
            showMsg("send fail!");
        }
    }

    private void showMsg(String msg){
            msgs.append(msg);
        
    }
}
