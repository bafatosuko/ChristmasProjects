package Project4;

import java.util.Scanner;

public class Project4 {

    public static void main(String[] args) {
        String[][] table = new String[3][3];
        int counter = 0;
        // Αρχικοποιουμε το table για να μπορουμε να το συγκρινουμε
        for (int i = 0; i < table.length; i++){
            for(int k = 0; k < table[i].length; k++){
                table[i][k] = "";
            }
        }

        System.out.println("Παίξτε τρίλιζα με την ακόλουθη σειρά");
        System.out.println("---------");
        System.out.println("[1.1|1.2|1.3]");
        System.out.println("[2.1|2.2|2.3]");
        System.out.println("[3.1|3.2|3.3]");
        System.out.println("---------");
        System.out.println("Διαλέξτε θέση που θελέτε να βάλετε τον χαρακτηρα σας σύμφωνα με το πανω σχήμα");

        boolean isTrue = true;
        int player1X;
        int player1Y;
        int player2X;
        int player2Y;
        try(  Scanner in = new Scanner(System.in);) {
            while (isTrue) {
                boolean validMove = false;
                // Θα χρειάστουμε και άλλο while για οταν κανει λαθος τοποθετηση ο παιχτης 1 σε θεση που
                // ηδη υπαρχει συμβολο να παιζει ξανα αυτος και οχι ο παιχτης 2 !!
                while(!validMove) {
                    // ΠΑΙΧΤΗΣ 1 ----
                    System.out.println("ΠΑΙΧΤΗΣ 1 ->(Ο) Γραψτε αριθμο 1 εως 3 για την κατακόρυφη στήλη:");
                    player1X = in.nextInt() - 1;
                    while (player1X < 0 || player1X > 2) {
                        System.out.println("Γραψτε αριθμο 1 εως 3 για την κατακόρυφη στήλη:");
                        player1X = in.nextInt() - 1;
                    }

                    System.out.println("ΠΑΙΧΤΗΣ 1 ->(Ο) Γραψτε αριθμο 1 εως 3 για την οριζόντια στήλη:");
                    player1Y = in.nextInt() - 1;
                    while (player1Y < 0 || player1Y > 2) {
                        System.out.println("Γραψτε αριθμο 1 εως 3 για την οριζόντια στήλη:");
                        player1Y = in.nextInt() - 1;
                    }

                    // Τσεκαρουμε αν η θέση ειναι κατειλημμένη
                    if (!table[player1X][player1Y].equals("")) {
                        System.out.println("Η θέση είναι ήδη κατειλημμένη. Προσπαθήστε ξανά.");
                        continue;
                    }else {
                        table[player1X][player1Y] = "O";  // Εκχωρούμε την θεση
                        printer(table);
                        validMove = true; // Βγάινουμε απο τον παίκτη 1
                    }
                }


                // Τσεκαρουμε αν έχει κερδισει ο παιχτης 1
                if (youWin(table, "O")){
                    System.out.println("O Παιχτης 1 κέρδισε !!!");


                  isTrue = false;
                  continue;
                }

                // Τσέκαρουμε αν έχουμε ισοπαλια
                if (isBoardFull(table)) {
                    System.out.println("Ισοπαλία!");
                    isTrue = false;
                    continue;
                }


                // ΠΑΙΧΤΗΣ 2-
                validMove = false;
                while (!validMove) {
                    System.out.println("ΠΑΙΧΤΗΣ 2 ->(Χ) Γραψτε αριθμο 1 εως 3 για την κατακόρυφη στήλη:");
                    player2X = in.nextInt() - 1;
                    while (player2X < 0 || player2X > 2) {
                        System.out.println("Λαθος αριθμος.Γραψτε αριθμο 1 εως 3 για την κατακόρυφη στήλη:");
                        player2X = in.nextInt() - 1;
                    }

                    System.out.println("ΠΑΙΧΤΗΣ 2 ->(Χ) Γραψτε αριθμο 1 εως 3 για την οριζόντια στήλη:");
                    player2Y = in.nextInt() - 1;
                    while (player2Y < 0 || player2Y > 2) {
                        System.out.println("Γραψτε αριθμο 1 εως 3 για την οριζόντια στήλη:");
                        player2Y = in.nextInt() - 1;
                    }

                    // Τσεκαρουμε αν η θέση ειναι κατειλημμένη
                    if (!table[player2X][player2Y].equals("")) {
                        System.out.println("Η θέση είναι ήδη κατειλημμένη. Προσπαθήστε ξανά.");
                        continue;
                    }else {
                        table[player2X][player2Y] = "X"; // εκχώρουμε την θεση
                        printer(table);
                        validMove = true; // Εγκύρη θέση βγαίνουμε απο τον παιχτης 2
                    }

                }



                // Τσεκαρουμε αν έχει κερδισει ο παιχτης 2
                if(youWin(table, "X")){
                    System.out.println("Ο παιχτης 2 κερδισε!");
                    isTrue = false;
                    continue;

                }

                // Τσέκαρουμε αν εχουμε ισοπαλία
                if (isBoardFull(table)) {
                    System.out.println("Ισοπαλία!");
                    isTrue = false;
                    continue;
                }


            }
        }catch (Exception e){
            System.out.println("Σφαλμα. Παρακάλω εισάγετε καταληλο αριθμο !! ");;

        }



    }

    /**
     * Ελένχουμε  αν καποιος παιχτης εχει κερδισει
     * @param arr το table
     * @param player  "Χ" η "0" για να μπορουμε ελενξουμε τον πινακα
     * @return boolean
     */
    public static boolean youWin(String[][] arr, String player){
        boolean youWon = false;
        for (int i = 0; i < arr.length ; i++){
                // για τις οριζοντιεσ στυλες
                if(arr[i][0].equals(arr[i][1])  && arr[i][0].equals(arr[i][2]) && arr[i][0].equals(player) ){
                    youWon = true;
                    break;
                }
                // για τις καθετες
                if(arr[0][i].equals(arr[1][i]) && arr[0][i].equals(arr[2][i]) && arr[0][i].equals(player)){
                    youWon = true;
                    break;
                }
                // για τισ διαγωνιες
                if (arr[0][0].equals(arr[1][1]) && arr[0][0].equals(arr[2][2]) && arr[0][0].equals(player)){
                    youWon = true;
                    break;
                }
                if (arr[0][2].equals(arr[1][1]) && arr[0][2].equals(arr[2][0]) && arr[0][2].equals(player)){
                    youWon = true;
                    break;
                }
        }
        return youWon;
    }

    public static void printer(String[][] arr){
        System.out.printf("[%s|%s|%s]\n", arr[0][0],arr[0][1],arr[0][2] );
        System.out.printf("[%s|%s|%s]\n", arr[1][0],arr[1][1],arr[1][2] );
        System.out.printf("[%s|%s|%s]\n", arr[2][0],arr[2][1],arr[2][2] );
    }

    public static boolean isBoardFull(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j].equals("")) {
                    return false; // Αν υπάρχει κενή θέση, επιστρέφει false
                }
            }
        }
        return true; // Αν δεν υπάρχουν κενές θέσεις, επιστρέφει true
    }

}
