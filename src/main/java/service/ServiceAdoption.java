package service;

import exceptions.DomainException;
import model.Pet;
import model.enums.PetGender;
import model.enums.PetType;
import util.FileUtils;
import util.InputUtils;

import java.util.ArrayList;
import java.util.List;


public class ServiceAdoption {

    List<Pet> petList = new ArrayList<>();

    public void registerNewPetForAdoption(){
        //Exibe o formulário de cadastro do pet antes de iniciar as leituras
        FileUtils.readForm();

        String[] partsName = InputUtils.readName();
        if (partsName[0].isBlank() || partsName[1].isBlank()) {
            throw new DomainException("⚠️ Digite nome e sobrenome válidos (ex: Alfredo Lactea).");
        }
        String name = partsName[0].trim() + " " + partsName[1].trim();

        PetType petType = InputUtils.readPetType();
        PetGender petGender = InputUtils.readPetGender();

        String[] partsAddress = InputUtils.readAddress();
        String address = String.format("Rua: %s, Número: %s, Cidade: %s", partsAddress[0].trim(), partsAddress[1].trim(), partsAddress[2].trim());

        double petAge;
        while (true){
            try {
                petAge = InputUtils.readPetAge();
                if (petAge > 20.0 || petAge <= 0.0){
                    throw new DomainException("Idade inválida: deve estar entre 0.1 e 20 anos");
                }
                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }
        }

        double petWeight;
        while (true){
            try {
                petWeight = InputUtils.readPetWeight();
                if (petWeight > 60 || petWeight < 0.5){
                    throw new DomainException("Digite um peso entre 0.5 e 60Kg");
                }
                break;
            }catch (DomainException e){
                System.out.println(e.getMessage());
            }
        }

        String petRace = InputUtils.readPetRace();

        Pet pet = new Pet(name, petType, petGender, address, petAge, petWeight, petRace);
        System.out.println(pet);
        petList.add(pet);
        FileUtils.writeForm(pet);
    }

}
