package service;

import exceptions.DomainException;
import model.Pet;
import model.enums.PetGender;
import model.enums.PetType;
import repository.PetRepository;
import util.FileUtils;
import util.InputUtils;

import static util.InputUtils.NOT_INFORMED;

public class ServiceAdoption {

    private final PetRepository petRepository = new PetRepository();

    public void registerNewPetForAdoption(){
        //Exibe o formulário de cadastro do pet antes de iniciar as leituras
        FileUtils.readForm();

        String name;
        while (true){
            try {
                System.out.print("\nDigite o nome completo do pet: ");
                name = InputUtils.readName();
                validateNoInvalidChars(name);
                String[] parts = name.trim().split("\\s");

                if (parts.length < 2){
                    throw new DomainException("⚠️ Falta Sobrenome. Digite o nome completo (Ex: Alfredo Lactea).");
                }

                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }
        }

        PetType petType;
        while (true){
            try {
                System.out.print("Digite o tipo do pet (CACHORRO/GATO): ");
                petType = PetType.valueOf(InputUtils.readPetType());
                break;
            }catch (IllegalArgumentException e){
                System.out.println("⚠️ Opção inválida. Digite CACHORRO ou GATO");
            }
        }

        PetGender petGender;
        while (true){
            try {
                System.out.print("Digite o gênero do pet (MACHO ou FEMEA):");
                petGender = PetGender.valueOf(InputUtils.readPetGender());
                break;
            }catch (IllegalArgumentException e){
                System.out.println("️⚠️ Tipo inválido! Digite MACHO ou FEMEA.");
            }
        }

        String address;
        while (true){
            try {
                System.out.print("Endereço (Ex: Rua A, 100, São Paulo): ");
                address = InputUtils.readAddress().trim();

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

                address = String.format("Rua: %s, Número: %s, Cidade: %s", street, number, city);
                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }
        }

        double petAge;
        while (true){
            try {
                System.out.print("Idade aproximada do pet (em anos, use 0.X para meses): ");
                petAge = InputUtils.readPetAge();

                if (petAge > 20.0 || petAge <= 0.0){
                    throw new DomainException("⚠️ Idade fora do limite. Digite um valor entre 0.1 e 20 anos. (0.1 representa 1 mês)");
                }

                if (petAge < 1.0){
                    petAge = convertMonthsFractionToYears(petAge);
                }
                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("⚠️ Entrada Inválida! Digite um número válido.");
            }
        }

        double petWeight;
        while (true){
            try {
                System.out.print("Digite o peso do pet (em Kg): ");
                petWeight = InputUtils.readPetWeight();

                if (petWeight > 60 || petWeight < 0.5){
                    throw new DomainException("Digite um peso entre 0.5 e 60Kg");
                }

                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("⚠️ Formato inválido. Digite apenas números para o peso (Ex: 15.5)");
            }
        }

        String petRace;
        while (true){
            try {
                System.out.print("Digite a raça do pet: ");
                petRace = InputUtils.readPetRace();
                validateNoInvalidChars(petRace);

                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }
        }

        Pet pet = new Pet(name, petType, petGender, address, petAge, petWeight, petRace);
        petRepository.save(pet);

        System.out.println("\n✔ Pet Cadastrado! ✔");

        FileUtils.writeForm(pet);
    }

    // Valida se o nome contém apenas letras e espaços
    private static void validateNoInvalidChars(String name) {
        if (!name.matches("[a-zA-Z ]+")) {
            throw new DomainException("⚠️ O nome não pode conter números ou caracteres especiais.");
        }
    }

    // Se o valor for menor que 1, assume que é a idade em meses e converte para anos
    private static double convertMonthsFractionToYears(double input) {
        double months = input * 100; //Ex: 0.1 * 100 = 10 Meses
        double years = months / 12; //Ex: 10 / 12 = 0.83 Anos

        // Faz o cast para double, pois Math.round retorna um long.
        // Sem o cast, a divisão seria feita como inteira (long / int) e perderia as casas decimais, resultando em 0.
        return (double) Math.round(years * 100) / 100;
    }
}
