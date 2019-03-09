package pl.coderslab.model;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("Pomyśl liczbe od 0 do 1000, a ja zgadnę w max. 10 próbach");
        int min = 0;
        int max = 1000;
        int counter = 0;
        boolean result = false;
        Scanner scan = new Scanner(System.in);

        while (!result) {
            int guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję " + guess);
            String option = scan.nextLine();
            counter++;

            switch (option) {
                case "zgadles":
                    System.out.println("Wygrałem w " + counter + " krokach!");
                    result = true;
                    break;
                case "za duzo":
                    max = guess;
                    break;
                case "za malo" :
                    min = guess;
                    break;
                default:
                    System.out.println("Nie oszukuj");
                    break;
            }
        }
        scan.close();
        System.out.println("Koniec gry");

    }
}