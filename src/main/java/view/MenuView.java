package view;

import exceptions.DomainException;
import service.ServiceAdoption;
import util.InputUtils;

public class MenuView {
    ServiceAdoption serviceAdoption = new ServiceAdoption();

    public void showMenu() {
        int option = -1;

        while (true) {

            System.out.print("""
                    ==============================
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
                option = InputUtils.validateEntryMenu("Escolha a opção: ");

                if (option < 1 || option > 6) {
                    throw new DomainException("⚠️ Opção inválida! Escolha um número entre 1 e 6.\n");
                }
            } catch (DomainException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Digite apenas números!\n");
            }

            switch (option) {
                case 1: {
                    try {
                        serviceAdoption.registerNewPetForAdoption();
                    }catch (DomainException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 6: {
                    System.out.println("Finalizado");
                    return;
                }
            }
        }
    }

}
