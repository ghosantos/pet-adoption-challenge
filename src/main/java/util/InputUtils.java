package util;

import java.util.Scanner;

public final class InputUtils {

    private static final Scanner sc = new Scanner(System.in);
    public static final String NOT_INFORMED = "NÃO INFORMADO";

    public static Scanner getScanner(){
        return sc;
    }

    public static int readInt(){
        return Integer.parseInt(sc.nextLine().trim().replaceAll("\\s", ""));
    }

    public static String readString(){
        return sc.nextLine().trim();
    }

    public static double readDouble(){
        String input = sc.nextLine().trim().replace(",", ".");
        return Double.parseDouble(input);
    }
}
