import java.io.File;
import java.util.Formatter;

public class file{
    public static void main(String[] args) {
        File x = new File("C:\\test.txt");
        if(x.exists())
            System.out.println(x.getName()+" exists!");
        else{
            try{
                Formatter c = new Formatter("D:\\test.txt");
                c.format("%s%s", "args ", "friend");
                c.close();
            }
            catch(Exception e){
                System.out.println("hello");
            }
            System.out.println("na bai ni haigi!");
        }
    }
}