/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class ValueComparator implements Comparator<String>{
	public int compare(String a, String b){
		return a.compareTo(b);
	}
}
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		List<String> l = new ArrayList<>();
		l.add("abcde");
		l.add("de");
        l.add("cde");
        Collections.sort(l, Collections.reverseOrder());
        System.out.println(l.toString());
	}
}
