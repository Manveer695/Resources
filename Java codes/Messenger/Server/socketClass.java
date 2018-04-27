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
    ObjectInputStream inputObj, ojn;
    ObjectOutputStream outputObj;
    ServerSocket server;
    Socket conn;

    public socketClass(){
        super("Messenger");
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
            server = new ServerSocket(6879,100);
            while(true){
                try{
                    waitForConn();
                    setupStreams();
                    whileChatting();
                }
                catch(Exception eofex){
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
        conn = server.accept();
        showMsg("Connected to"+conn.getInetAddress().getHostName());
        int a = 9;
        System.out.println(a);
    }

    private void setupStreams(){
        try{
           // showMsg(conn.getInputStream());
        
        outputObj = new ObjectOutputStream(conn.getOutputStream());
        outputObj.flush();
        inputObj = new ObjectInputStream(conn.getInputStream());
        showMsg("Streams set up dude!");
        }
        catch(Exception ex){
            showMsg("Some problem in streams bro");    
        }
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
                showMsg("Client mad probably!");
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
            showMsg("i/p o/p can't live with you!");
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
        
            msgs.append(msg+"\n");
        
    }
}
