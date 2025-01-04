package Project1;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

    public static void main(String[] args) {
        File fdIn = new File("C:/Users/thomas/Desktop/tmp/Project1.txt");
        File fdOut = new File("C:/Users/thomas/Desktop/tmp/Project1-out.txt");
        try(Scanner in = new Scanner(fdIn);
            PrintStream ps = new PrintStream(fdOut)){

            final int INT_SIZE = 6;
            int[] inputNumbers = new int[49];
            int[] result = new int[INT_SIZE];
            int num ;
            int count = 0;
            int window ;


            while ( in.hasNextInt()) {
                num = in.nextInt();
               if(num >= 1 && num <= 49){
                   inputNumbers[count++]=num;

               }else{
                   break;
               }



            }


            if (count <= 5  || count >= 49){
                System.out.println("το αρχείο πρέπει να περιέχει περισσότερους από 6 αριθμούς και το πολύ 49 αριθμούς");
            }


            int[] sortedArr = Arrays.copyOfRange(inputNumbers, 0, count);
            Arrays.sort(sortedArr);

            window = count - INT_SIZE;

            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = sortedArr[i];
                                    result[1] = sortedArr[j];
                                    result[2] = sortedArr[k];
                                    result[3] = sortedArr[l];
                                    result[4] = sortedArr[m];
                                    result[5] = sortedArr[n];
                                    if(isMax4Even(result) && isMax4Odds(result) && isSameEnding(result) && isSameTen(result)){
                                        ps.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                        System.out.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);

                                    }
                                }
                            }
                        }
                    }
                }
            }









        }catch (Exception e){
            System.err.println("Δεν υπάρχει φάκελος με αυτό το όνομα");

        }

    }

    public static boolean isMax4Even(int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] % 2 == 0) count ++;


        }
        return  count <= 4;
    }

    public static boolean isMax4Odds(int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] % 2 != 0) count ++;


        }
        return  count <= 4;

    }

    public static boolean consecutive(int arr[]){
        int count =0;
        for (int i = 0; i < arr.length - 1; i++){
            if(arr[i] == arr[i+1] -1) count++;

        }

        return count <= 1;
    }

    public static boolean isSameEnding(int arr[]){
        int count = 0;
        for(int i = 0; i < arr.length - 2; i++ ){
            if ( arr[i] / 10 - arr[i+1] / 10 - arr[i+2] / 10 == 0) count++;
        }

        return count <= 1;
    }

    public static boolean isSameTen(int[] arr){
        int count = 0;
        for(int i = 0; i < arr.length - 2; i++ ){
            if ( arr[i] % 10 - arr[i+1] % 10 - arr[i+2] % 10 == 0) count++;
        }

        return count <= 1;
    }
}
