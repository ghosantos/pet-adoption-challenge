package util;

import java.util.Scanner;

public final class InputUtils {

    private static final Scanner sc = new Scanner(System.in);
    public static final String NOT_INFORMED = "NÃO INFORMADO";

    public static Scanner getScanner(){
        return sc;
    }

    public static int validateEntryMenu(String message){
        System.out.print(message);
        return Integer.parseInt(sc.nextLine().trim().replaceAll("\\s", ""));
    }

    public static String readName(){
        return sc.nextLine().trim();
    }

    public static String readPetType(){
        return sc.nextLine().trim().toUpperCase();
    }

    public static String readPetGender(){
        return sc.nextLine().trim().toUpperCase();
    }

    public static String readAddress(){
        return sc.nextLine().trim();
    }

    public static double readPetAge(){
        String input = sc.nextLine().trim().replace(",", ".");
        return Double.parseDouble(input);
    }

    public static double readPetWeight(){
        String input = sc.nextLine().trim().replace(",",".");
        return Double.parseDouble(input);
    }

    public static String readPetRace(){
         return sc.nextLine().trim();
    }
}
