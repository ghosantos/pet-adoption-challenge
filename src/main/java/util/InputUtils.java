package util;

import model.enums.PetGender;
import model.enums.PetType;

import java.util.Scanner;

public final class InputUtils {

    private static final Scanner sc = new Scanner(System.in);
    private static final String NOT_INFORMED = "NÃO INFORMADO";

    public static Scanner getScanner(){
        return sc;
    }

    public static int validateEntryMenu(String message){

        while (true){
            System.out.print(message);
            try {
                int value = Integer.parseInt(sc.nextLine().trim().replaceAll("\\s", ""));
                if (value > 0 && value <= 6) {
                    return value;
                }
                System.out.println("⚠ Digite um número entre 1 e 6 (positivo)!");

            }catch (NumberFormatException e){
                System.out.println("⚠ Digite apenas números!");
            }
        }
    }

    public static String[] readName(){
        while (true) {
            try {
                System.out.print("\nDigite o nome completo do pet (ex: Toddy Lactea): ");
                String name = sc.nextLine().trim();
                validateNoInvalidChars(name); // Verifica se a entrada possuí números ou caracteres especiais
                String[] fields = name.split("\\s");

                if (fields.length < 2) {
                    throw new IllegalArgumentException("⚠️ Digite nome e sobrenome (ex: Alfredo Lactea).");
                }

                String firstName = fields[0];
                String lastName = fields[1];

                return new String[] {firstName, lastName};

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.print("Digite novamente: ");
            }
        }
    }

    public static PetType readPetType(){
        while (true){
            System.out.print("Digite o tipo do pet (CACHORRO ou GATO): ");
            String input = sc.nextLine().trim().toUpperCase();

            try {
                return PetType.valueOf(input);
            }catch (IllegalArgumentException e){
                System.out.println("️⚠️ Tipo inválido! Digite CACHORRO ou GATO:");
            }
        }
    }

    public static PetGender readPetGender(){
        while (true){
            System.out.print("Digite o gênero do pet (MACHO ou FEMEA): ");
            String input = sc.nextLine().trim().toUpperCase();

            try {
                return PetGender.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Gênero inválido! Digite MACHO ou FEMEA:");
            }
        }

    }

    public static String[] readAddress(){
        while (true){
            System.out.println("Informe o endereço no formato: Rua,Número,Cidade");
            String input = sc.nextLine().trim();

            String[] fields = input.split(",");

            if (fields.length != 3) {
                System.out.println("Deve conter exatamente 3 informações: Rua, Número e Cidade.");
                continue;
            }

            String street = fields[0].trim();
            String number = fields[1].trim();
            String city = fields[2].trim();

            if (street.isEmpty() || city.isEmpty()) {
                System.out.println("Informe os dados obrigatórios (Rua e Cidade).");
                continue;
            }

            if (number.isBlank()){
                number = NOT_INFORMED; // Caso o usuario não informe o número
            }

            return new String[] {street,number,city};
        }
    }

    public static double readPetAge(){
        while (true){
            try {
                System.out.print("Digite a idade do pet (em anos): ");
                String input = sc.nextLine().trim().replace(",",".");
                double age = Double.parseDouble(input);

                return convertToYears(age);
            }catch (NumberFormatException e){
                System.out.println("Entrada Inválida! Digite um número válido: ");
            }
        }
    }

    public static double readPetWeight(){
        while (true){
            try {
                System.out.print("Digite o peso do pet (em Kg): ");
                String input = sc.nextLine().trim().replace(",",".");
                double inputParse = Double.parseDouble(input);

                return Double.parseDouble(String.format("%.2f", inputParse));
            }catch (NumberFormatException e){
                System.out.println("Entrada Inválida! Digite um número válido");
            }
        }
    }

    public static String readPetRace(){
        while (true){
            try {
                System.out.print("Digite a raça do pet: ");
                String input = sc.nextLine().trim();
                validateNoInvalidChars(input);

                return input;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println("⚠️ Raça inválida! Digite novamente (sem números ou caracteres especiais):");
            }
        }
    }

    // Metodo auxiliar para validar se há apenas letras e espaços
    private static void validateNoInvalidChars(String name) {
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("⚠️ O nome não pode conter números ou caracteres especiais.");
        }
    }

    // Se o valor for menor que 1, assume que é a idade em meses e converte para anos
    private static double convertToYears(double input) {
        double converted = input;
        if (input < 1.0) {
            converted /= 12.0;
        }

        return Double.parseDouble(String.format("%.3f", converted));
    }
}
