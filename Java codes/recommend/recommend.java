import java.io.File;
import java.util.*;
import javafx.util.Pair;

public class recommend{
    public static void main(String[] args) {
        data d = new data();
        int[][] movieUserData = d.generateData();
        
        try{
            Formatter ratingData = new Formatter("D:\\ratings.txt");

            for(int i=0;i<d.numMovies;i++){
                for(int j=0;j<d.numUsers;j++){
                    ratingData.format("%s, ", movieUserData[i][j]);
                }
                ratingData.format("\n");
            }
            ratingData.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println();
        System.out.println();

        FeatureNParameter fp = new FeatureNParameter(d.numMovies, d.numUsers);
        // for(int i=0;i<d.numMovies;i++){
        //     for(int j=0;j<100;j++){
        //         System.out.print(fp.X[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();
        // System.out.println();
        
        // for(int i=0;i<d.numUsers;i++){
        //     for(int j=0;j<100;j++){
        //         System.out.print(fp.Theta[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        recommendationSystem rs = new recommendationSystem();
        // double cost = rs.costFn(fp.X, fp.Theta, movieUserData, 0.1, d.numMovies, d.numUsers);
        // Pair<double[][],double[][]> grads = rs.gradients(fp.X, fp.Theta, movieUserData, 0.1, 0.03, d.numMovies, d.numUsers);
        // double[][] x_grad = grads.getKey();
        // double[][] theta_grad = grads.getValue();

        // System.out.println(cost);
        
        // System.out.println();
        // System.out.println();

        // for(int i=0;i<d.numMovies;i++){
        //     for(int j=0;j<100;j++){
        //         System.out.print(x_grad[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();
        // System.out.println();
        
        // for(int i=0;i<d.numUsers;i++){
        //     for(int j=0;j<100;j++){
        //         System.out.print(theta_grad[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        rs.gradientDescent(fp.X, fp.Theta, movieUserData, 5, 5, 0.3, d.numMovies, d.numUsers);
        
        double[][] thetaTrans = rs.transpose(fp.Theta, d.numUsers, 100);
        double[][] predictions = new double[d.numMovies][d.numUsers];
        for(int i=0;i<d.numMovies;i++){
            for(int j=0;j<d.numUsers;j++){
                for(int k=0;k<100;k++){
                    predictions[i][j] += (fp.X[i][k]*thetaTrans[k][j]); 
                }
                System.out.print(predictions[i][j]+" ");
            }  
            System.out.println();  
        }    
    }
}