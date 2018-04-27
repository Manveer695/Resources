import java.util.Arrays;
import java.util.LinkedList;

public class listToArr{
    public static void main(String[] args) {
        String[] stuff = {"hi", "ji"};
        LinkedList<String> theList = new LinkedList<>(Arrays.asList(stuff));
        theList.add("gi");
        theList.addFirst("ei");
        stuff = theList.toArray(new String[theList.size()]);

        for(String x:stuff){
            System.out.println(x);
        }
    }
}