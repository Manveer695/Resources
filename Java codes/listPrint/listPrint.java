import java.util.*;

import javax.management.Query;

import jdk.incubator.http.internal.common.Queue;

public class listPrint{
    public static void main(String[] args) {
        Character[] j = {'a','f','r'};
        List<Character> chs = Arrays.asList(j);
        System.out.println(chs);

        Integer[] ij = {12,43,54};
        List<Integer> chi = Arrays.asList(ij);
        System.out.println(chi);
        
    }
}