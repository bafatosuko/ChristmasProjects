package Project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;


public class Project3 {

    public static void main(String[] args) {
        File fdIn = new File("C:/Users/thomas/Desktop/tmp/Project3-in.txt");
        String[][] charReader = new String[128][2];


        // Αρχικόποιουμε τον πίνακα
        for (int i = 0; i < charReader.length; i++){
            charReader[i][0] = ""; // εχουμε  κενα string
            charReader[i][1] = "0"; // ξεκιναει ενας χαρακτήρας απο 0 φορες

        }


        try(BufferedReader bf = new BufferedReader(new FileReader(fdIn))){
            int characterNumber;
            while ((characterNumber = bf.read()) != -1) {
                char ch = (char) characterNumber;


                // Κοίταμε για τα whitespaces
                if (Character.isWhitespace(ch)) {
                    continue;
                }


                if (characterNumber >= 32 && characterNumber <= 126) {
                    boolean found  = false;


                    for (int i = 0; i < charReader.length; i++) {
                        // Ελένχουμε αν υπαρχει ο χαρακτηρας ξανα μεσα στον πινακα
                        if (charReader[i][0].equals(String.valueOf(ch))) {
                            // βαζουμε στο count  τον αριθμο που εχει ηδη
                            int count = Integer.parseInt(charReader[i][1]);
                            charReader[i][1] = String.valueOf(count + 1);
                            // θετουμε το found true δηλαδη οτι βρηκαμε ιδιο χαρακτηρα
                            found = true;
                            break;

                        }
                    }

                    // Αν δεν εχουμε ιδιο χαρακτηρα , τοτε τον τοποθετουμε στον πινακα
                    if(!found){
                        for ( int i = 0; i < charReader.length; i++){
//                            if(charReader[i][0] != null)
                           if(charReader[i][0].equals("")) {
                                charReader[i][0] = String.valueOf(ch);
                                charReader[i][1] = "1";
                                break;
                            }
                        }
                    }

                }



            }
        }catch (IOException e){
            System.err.println(e);
        }

        // Εκτύπωση δεδομένων
        // Sorted για καθε χαρακτηρα
        Arrays.sort(charReader, Comparator.comparing(o -> o[0]));
        System.out.println("--------- ΛΙΣΤΑ ΚΑΤΑΝΑΝΟΜΗΜΕΝΗ ΩΣ ΠΡΟΣ ΤΟΝ ΧΑΡΑΚΤΗΡΑ --------------");
        for(int i =0; i < charReader.length;  i++){

            if(charReader[i][0].equals("")) continue;
            System.out.println("Ο χαρακτήρας " + charReader[i][0] + " εμφανίζετε " + charReader[i][1] + " φορές" );

        }

        System.out.println("------------------ ΛΙΣΤΑ ΚΑΤΑΝΟΜΗΜΕΝΗ ΩΣ ΠΡΟΣ ΤΟ ΠΟΣΕΣ ΦΟΡΕΣ ΕΜΦΑΝΙΖΟΝΤΑΙ ΟΙ ΧΑΡΑΚΤΗΡΕΣ ΚΑΤΑ ΑΥΞΟΥΣΑ ΣΕΙΡΑ ----------");
        Arrays.sort(charReader, (a,b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        for (int i = 0; i < charReader.length; i++){
            if(charReader[i][0].equals("")) continue;
            System.out.println(charReader[i][1] + " φορές εμφανίζεται ο χαρακτήρας " + charReader[i][0]);
        }

    }

}
