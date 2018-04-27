import java.util.*;
import java.lang.*;

public class branch implements Runnable{
    String name;
    Integer time;
    Random r;
    public branch(String name){
        this.name = name;
        r = new Random();
        this.time = r.nextInt(999);
    }
    public void run(){
        try{
            System.out.println("thread: "+name+" time: "+time);
            Thread.sleep(time);
            System.out.println("thread: "+name+" is back");
        }
        catch(Exception e){

        }
    }
}