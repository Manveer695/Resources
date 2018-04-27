import java.util.*;
import java.lang.*;
import java.sql.Time;

public class data{
    /*
    String[] movies = {
                        "ddlj","dilwaale","dabang","singham","wanted","barfi","hase to fase","lootera","baby","satya",
                        "ek tha tigr","krish","jab we met","love story","don","body guard","kl ho na ho","veer zraa","maine pyar","don2",
                        "ashqui2","hum tum","dhoom","love ajj kl","rowdy rathore","bang bang","dhoom2","silsila","dhoom 3","ashqui",
                        "gangs of wasepur","2 states","jthj","omkara","kkkg","avenger","chlte chlte","shivaay","bajirao","dark knight",
                        "border","matrix","tere naam","rasleela","khiladi","baby","dil se","gadar","hostage","ranjhana"
                      };
    char[] movieGenre = {
                            'R','R','R','A','A','R','R','R','A','A',
                            'A','A','R','R','A','A','R','R','R','A',
                            'R','R','A','R','A','A','A','R','A','R',
                            'A','R','R','A','R','A','R','A','R','A',
                            'A','A','R','R','A','R','R','A','A','R'
                        };                  

    char[] userGenre =  {
                            'R','A','A','R','A','A','A','A','R','R',
                            'R','R','A','A','R','A','R','A','R','A',
                            'R','R','R','R','A','A','A','A','R','R',
                            'A','R','A','R','A','R','R','A','A','R'
                        };
    */

    String[] movies2;
    /*
    int numMovies = 50;
    int numUsers = 40;
    */
    
    /**
     * Movie lens data set
     */
    int numMovies = 1682;
    int numUsers = 943;
    int flag = 1;

    int[][] movieUserMap = new int[numMovies][numUsers];
    
    Random r = new Random();
    
    /*
    int[][] generateData(){
        long startTime = System.currentTimeMillis();
        for(int i=0;i<numMovies;i++){
            for(int j=0;j<numUsers;j++){
                // if(System.currentTimeMillis() > startTime + 1)
                // {
                //     startTime = System.currentTimeMillis();
                //     flag = ((flag == 0)?1:0);
                // }
                // if(flag == 1)
                movieUserMap[i][j] = (movieGenre[i] == userGenre[j]?5-r.nextInt(3):r.nextInt(3));
                if(movieUserMap[i][j] == 0)
                {
                    movieUserMap[i][j] = (movieGenre[i] == userGenre[j]?5-r.nextInt(3):r.nextInt(3));
                    if(movieUserMap[i][j] == 0)
                    {
                        movieUserMap[i][j] = (movieGenre[i] == userGenre[j]?5-r.nextInt(3):r.nextInt(3));
                    }
                }    
            }
        }
        return movieUserMap;
    }
    */

    /**
     * Movie lens data set
     */
    int[][] generateData(){
        Map<Integer, String> movies = Movies.getData(".\\data\\u.item");
        Map<Integer, Map<Integer, Integer>> userRatings = Users.getData(".\\data\\u.data");
        
        int i=0;
        Iterator itr = movies.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry movie = (Map.Entry) itr.next();
            movies2[i] = movie.getValue();
            i++;
        }
        
        itr = userRatings.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry user = (Map.Entry) itr.next();
            int col = user.getKey();
            Map<Integer, Integer> userMovies = (HashMap) user.getValue();
            Iterator itr2 = userMovies.entrySet().iterator();
            while(itr2.hasNext()){
                Map.Entry movie = (Map.Entry) itr2.next();
                int row = movie.getKey();
                movieUserMap[row][col] = movie.getValue();
            }
        }

        return movieUserMap;
    }

    
}