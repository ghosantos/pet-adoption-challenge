package view;

import exceptions.DomainException;
import model.Pet;
import model.enums.PetSex;
import model.enums.PetType;
import service.ServiceAdoption;
import util.FileUtils;
import util.InputUtils;
import util.PetValidator;

import java.util.List;

public class MenuView {
    ServiceAdoption serviceAdoption = new ServiceAdoption();

    public void showMainMenu() {
        int menuOption = -1;

        while (true) {
            System.out.print("""
                    \n==============================
                            MENU PRINCIPAL
                    ==============================
                    1.Cadastrar um novo pet
                    2.Alterar os dados do pet cadastrado
                    3.Deletar um pet cadastrado
                    4.Listar todos os pets cadastrados
                    5.Listar pets por algum critério (idade, nome, raça)
                    6.Sair
                    """);
            try {
                menuOption = getOption(1, 6, "Escolha a opção: ");

                switch (menuOption) {
                    case 1 -> {
                        Pet petToRegister = captureNewPetData();

                        try {
                            serviceAdoption.registerNewPetForAdoption(petToRegister);
                            System.out.println("✅ Pet cadastrado com sucesso!\n");
                        } catch (DomainException e) {
                            System.out.println("❌ Erro ao salvar: " + e.getMessage());
                        }
                    }

                    case 2 -> {
                        // Busca a lista conforme os critérios de busca
                        List<Pet> founds = searchMenu();

                        // Se achar algo, pede para selecionar uma das opções
                        if (founds != null || founds.isEmpty()) {
                            Pet selectedPet = selectPetFromList(founds);

                            // Chama o menu de edição para o pet
                            if (selectedPet != null) {
                                menuEdition(selectedPet);
                                System.out.println("✅ Informações alteradas com sucesso!");
                            }
                        }
                    }

                    case 3 -> {
                        // Busca a lista conforme os critérios de busca
                        List<Pet> founds = searchMenu();

                        // Se achar algo, pede para selecionar uma das opções
                        if (founds != null && founds.isEmpty()) {
                            Pet selectedPet = selectPetFromList(founds);

                            // Realiza e exclusão do pet
                            if (selectedPet != null) {
                                serviceAdoption.deletePet(selectedPet);
                                System.out.println("✅ Pet deletado com sucesso!");
                            }
                        }
                    }


                    case 6 -> {
                        System.out.println("Finalizado");
                        return;
                    }
                }
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada Inválida. Digite um número válido.\n");
            }

        }
    }

    // Metodo responsável pela criação do objeto Pet.
    private Pet captureNewPetData() {
        FileUtils.readForm();

        String name;
        while (true) {
            try {
                System.out.print("\nNome completo: ");
                name = PetValidator.validateName(InputUtils.readString());
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }

        PetType type;
        while (true) {
            try {
                System.out.print("Tipo (CACHORRO/GATO): ");
                type = PetType.valueOf(InputUtils.readString().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Opção inválida. Digite CACHORRO ou GATO");
            }
        }

        PetSex sex;
        while (true) {
            try {
                System.out.print("Gênero (MACHO/FEMEA): ");
                sex = PetSex.valueOf(InputUtils.readString().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("️⚠️ Tipo inválido! Digite MACHO ou FEMEA.");
            }
        }

        String address;
        while (true) {
            try {
                System.out.print("Endereço (Rua, Num, Cidade): ");
                address = PetValidator.validateAddress(InputUtils.readString());
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }

        double age;
        while (true) {
            try {
                System.out.print("Idade (anos ou 0.x meses):  ");
                age = PetValidator.validateAge(InputUtils.readDouble());
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada Inválida! Digite um número válido.");
            }
        }

        double weight;
        while (true) {
            try {
                System.out.print("Peso (kg): ");
                weight = PetValidator.validateWeight(InputUtils.readDouble());
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Formato inválido. Digite apenas números para o peso (Ex: 15.5)");
            }
        }

        String race;
        while (true) {
            try {
                System.out.print("Raça: ");
                race = PetValidator.validateRace(InputUtils.readString());
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }

        return new Pet(name, type, sex, address, age, weight, race);
    }

    // Metodo com a lógica de busca do Pet utilizando os critérios.
    private List<Pet> searchMenu() {
        System.out.println("\n--- BUSCA DE PETS ---");

        while (true) {
            try {
                PetType type = null;
                while (type == null) {
                    try {
                        int option = getOption(1, 2, "Tipo (1.Cão / 2.Gato): ");

                        if (option == 1) {
                            type = PetType.CACHORRO;
                        } else if (option == 2) {
                            type = PetType.GATO;
                        }
                    } catch (DomainException e) {
                        System.out.println(e.getMessage());
                    }
                }

                System.out.print("""
                        Critério de busca:
                        1. Nome    2. Sexo
                        3. Idade   4. Peso
                        5. Raça    6. Endereço
                        7. Voltar
                        """);

                int criteria = getOption(1, 7, "Opção: ");
                if (criteria == 7) {
                    return null;
                }

                switch (criteria) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        return serviceAdoption.searchPetsByName(InputUtils.readString(), type);
                    }

                    case 2 -> {
                        System.out.print("Sexo (1.Macho / 2.Femea): ");
                        String input = InputUtils.readString();

                        PetSex sex;
                        if (input.equals("1") || input.equalsIgnoreCase("MACHO")) {
                            sex = PetSex.MACHO;
                        } else {
                            sex = PetSex.FEMEA;
                        }
                        return serviceAdoption.searchPetsBySex(sex, type);
                    }

                    case 3 -> {
                        System.out.print("Idade: ");
                        return serviceAdoption.searchPetsByAge(InputUtils.readDouble(), type);
                    }

                    case 4 -> {
                        System.out.print("Peso: ");
                        return serviceAdoption.searchPetsByWeight(InputUtils.readDouble(), type);
                    }

                    case 5 -> {
                        System.out.print("Raça: ");
                        return serviceAdoption.searchPetsByRace(InputUtils.readString(), type);
                    }

                    case 6 -> {
                        System.out.print("Endereço: ");
                        return serviceAdoption.searchPetsByAddress(InputUtils.readString(), type);
                    }
                }

            } catch (DomainException e) {
                System.out.println("⚠️ Erro na busca: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Erro: Entrada Inválida. Digite um número válido.");
            }

        }
    }

    // Metodo responsável por retornar todos os resultados da lista com base nos critérios definidos no metodo searchMenu.
    private Pet selectPetFromList(List<Pet> pets) {
        System.out.println("\n✅ Pets encontrados:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + " - " + pets.get(i));
        }

        while (true) {
            try {
                int index = getOption(1, pets.size(), "Escolha o pet que deseja alterar: ");
                return pets.get(index - 1);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Digite uma opção válida.");
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Metodo responsável por solicitar qual alteração será realizada no cadastro do Pet e acionar os métodos do Service para efetivar as alterações.
    private void menuEdition(Pet pet) {
        while (true) {
            System.out.print("""
                    \n✏️ O QUE DESEJA ALTERAR?
                    1. Nome      2. Endereço
                    3. Idade     4. Peso
                    5. Raça      6. Voltar
                    """);
            try {
                int option = getOption(1, 6, "Opção: ");
                System.out.println();
                if (option == 6) return;

                switch (option) {
                    case 1 -> {
                        while (true) {
                            try {
                                System.out.print("Novo nome: ");
                                serviceAdoption.changePetName(PetValidator.validateName(InputUtils.readString()), pet);
                                break;
                            } catch (DomainException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    case 2 -> {
                        while (true) {
                            try {
                                System.out.print("Novo Endereço: ");
                                serviceAdoption.changePetAddress(PetValidator.validateAddress(InputUtils.readString()), pet);
                                break;
                            } catch (DomainException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    }

                    case 3 -> {
                        while (true) {
                            try {
                                System.out.print("Nova Idade: ");
                                serviceAdoption.changePetAge(PetValidator.validateAge(InputUtils.readDouble()), pet);
                                break;
                            } catch (DomainException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Entrada Inválida! Digite um número válido.");
                            }
                        }
                    }

                    case 4 -> {
                        while (true) {
                            try {
                                System.out.print("Novo Peso: ");
                                serviceAdoption.changePetWeight(PetValidator.validateWeight(InputUtils.readDouble()), pet);
                                break;
                            } catch (DomainException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Entrada Inválida! Digite um número válido.");
                            }
                        }
                    }

                    case 5 -> {
                        while (true) {
                            try {
                                System.out.print("Nova Raça: ");
                                serviceAdoption.changePetRace(PetValidator.validateRace(InputUtils.readString()), pet);
                                break;
                            } catch (DomainException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
                System.out.println("✅ Dados atualizados!");

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada Inválida! Digite um número válido.\n");
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getOption(int min, int max, String message) {
        System.out.print(message);
        int option = InputUtils.readInt();

        if (option < min || option > max) {
            throw new DomainException("Opção inválida! Escolha um número entre " + min + " e " + max + ".");
        }
        return option;
    }
}

