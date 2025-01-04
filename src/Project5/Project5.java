package Project5;

import java.util.Scanner;

public class Project5 {

    static boolean[][] theaterBook = new boolean[12][30];
    public static void main(String[] args) {


        // Αρχικοποιούμε το theaterBook να ειναι ολα false
        for (int i = 0; i < theaterBook.length; i++){
            for (int k = 0; k < theaterBook[i].length; k++){
                theaterBook[i][k] = false;
            }
        }




        try(Scanner in = new Scanner(System.in)){
            String bookInput ;
            char column;
            int row;
            int bookOrCancel;

            System.out.println("If you want to book type 1 .If u want to cansel press 2");
            bookOrCancel = in.nextInt();
            while(bookOrCancel < 1  || bookOrCancel > 2){
                System.out.println("Wrong Number!!Type 1 to book type 2 to cansel a booking");
                bookOrCancel = in.nextInt();

            }
            in.nextLine();

            // τεσταρουμε την επιλογη που εχει δωσει ο χρηστης αν θα κανει book η cancel
            if(bookOrCancel == 1){
                System.out.println("Book a ticket: column from A to L and row 1 to 30 (example A2,C3...)");
                bookInput = in.nextLine();
            }else {
                System.out.println("Cancel a ticket: column from A to L and row 1 to 30 (example A2,C3...)");
                bookInput = in.nextLine();
            }


            while(bookInput.length() < 2 || bookInput.length() > 3){
                System.out.println("Wrong Seat . Try Again !! Column from A to L and row 1 to 30 (example A2,C3...)");
                bookInput = in.nextLine();
                continue;
            }
            // Βρίσκουμε το column και τα κανουμε ολα κεφαλαία για να τα συγκρινουμε
            column = Character.toUpperCase(bookInput.charAt(0));
            if(bookInput.length() == 2){
                row = Character.getNumericValue(bookInput.charAt(1));
            }else {
                String rowTest = Character.toString(bookInput.charAt(1) ) +  Character.toString(bookInput.charAt(2));
                row = Integer.parseInt(rowTest);
                System.out.println(row);

            }



            // Ελένχουμε αν εχει βαλέι ο χρηστης την σωστη θεση column (A - L) και το row ειναι σε θεση 1 εως 30
            while(Integer.valueOf(column % 65) > 11  ||  (row >30 || row < 1 )){
                System.out.println("Wrong Booking! seats are from A to L and rows from 1 to 30");
                bookInput = in.nextLine();
                column = Character.toUpperCase(bookInput.charAt(0));
                row = Character.getNumericValue(bookInput.charAt(1));
            }

            if(bookOrCancel == 1){
                // κρατηση θεσης !!
                book(column,row);
            }else  {
                // Ακύρωση θεσης !!
                cancel(column,row);

            }


        }catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }




    }

    public static void book(char column, int row){
        int columnNumber = Integer.valueOf(column % 65);
        if(theaterBook[columnNumber][row - 1]){
            System.out.println("The seat is already taken .Try another seat!");
        }else {
            theaterBook[columnNumber][row-1] = true;
            System.out.println("Your booking was success. Seat: " +column + row );

        }


    }

    public static void cancel(char column, int row){
        int columnNumber = Integer.valueOf(column % 65);
        // Αν ειναι πιασμενη η θεση
        if (theaterBook[columnNumber][row - 1]){
            theaterBook[columnNumber][row - 1] = false;
            System.out.println("Your booking was canceled correctly.At Seat: " +column + row );


        }else {
            System.out.println("You didnt book that seat!! Try again");
        }

    }


}
