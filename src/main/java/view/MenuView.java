package view;

import exceptions.DomainException;
import service.ServiceAdoption;
import util.InputUtils;

import java.util.Scanner;

public class MenuView {
    ServiceAdoption serviceAdoption = new ServiceAdoption();

    public void showMenu(){
        Scanner sc = InputUtils.getScanner();

        int option = -1;

        while (true){
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

            option = InputUtils.validateEntryMenu("Escolha a opção: ");

            switch (option){
                case 1:{
                    try {
                        serviceAdoption.registerNewPetForAdoption();
                    }catch (DomainException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 6:{
                    System.out.println("Finalizado");
                    return;
                }
            }
        }




    }

}
