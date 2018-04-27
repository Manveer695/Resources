import java.util.*;

public class generic{
    public static void main(String[] args) {
        Integer[] i= new Integer[5];
        String[] s = new String[5];
        s[0] = "hi";
        good(s);
    }

    public static <T> void good(T[] arr){
        for(T x: arr)
            System.out.println(x);
    }
}