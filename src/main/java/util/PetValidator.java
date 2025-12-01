package util;

import exceptions.DomainException;

import static util.InputUtils.NOT_INFORMED;

public class PetValidator {

    public static String validateName(String name){
        String[] parts = name.trim().split("\\s");

        if (parts.length < 2) {
            throw new DomainException("⚠️ Falta Sobrenome. Digite o nome completo (Ex: Alfredo Lactea).");
        }
        validateNoInvalidChars(name);

        return name;
    }

    public static void validateNoInvalidChars(String name) {
        if (!name.matches("[a-zA-Z ]+")) {
            throw new DomainException("⚠️ O nome não pode conter números ou caracteres especiais.");
        }
    }

    public static double validateAge(double age){
        if (age > 20.0 || age <= 0.0) {
            throw new DomainException("⚠️ Idade fora do limite. Digite um valor entre 0.1 e 20 anos. (0.1 representa 1 mês)");
        }

        if (age < 1.0) {
            age = convertMonthsFractionToYears(age);
        }

        return age;
    }

    public static void validateWeight(double weight){
        if (weight > 60 || weight < 0.5) {
            throw new DomainException("Digite um peso entre 0.5 e 60Kg");
        }
    }

    public static String validateAddress(String address){
        String[] parts = address.split(",");

        if (parts.length != 3) {
            throw new DomainException("⚠️ Deve conter exatamente 3 informações: Rua, Número e Cidade.");
        }

        String street = parts[0].trim();
        String number = parts[1].trim();
        String city = parts[2].trim();

        if (street.isEmpty() || city.isEmpty()) {
            throw new DomainException("⚠️ Informe os dados obrigatórios (Rua e Cidade).");
        }

        if (number.isBlank()) {
            number = NOT_INFORMED; // Caso o usuario não informe o número
        }

        return String.format("Rua: %s, Número: %s, Cidade: %s", street, number, city);
    }

    // Se o valor for menor que 1, assume que é a idade em meses e converte para anos
    public static double convertMonthsFractionToYears(double input) {
        double months = input * 100; //Ex: 0.1 * 100 = 10 Meses
        double years = months / 12; //Ex: 10 / 12 = 0.83 Anos

        // Faz o cast para double, pois Math.round retorna um long.
        // Sem o cast, a divisão seria feita como inteira (long / int) e perderia as casas decimais, resultando em 0.
        return (double) Math.round(years * 100) / 100;
    }

}
