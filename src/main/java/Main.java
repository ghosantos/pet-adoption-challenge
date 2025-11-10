import util.InputUtils;
import view.MenuView;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = InputUtils.getScanner();

        MenuView menuView = new MenuView();
        menuView.showMenu();

        sc.close();
    }
}