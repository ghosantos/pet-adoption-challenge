package service;

import exceptions.DomainException;
import model.Pet;
import model.enums.PetSex;
import model.enums.PetType;
import repository.PetRepository;
import repository.PetRepositoryInMemory;
import util.FileUtils;

import java.util.List;

public class ServiceAdoption {

    private final PetRepository petRepository;

    public ServiceAdoption(){
        this.petRepository = new PetRepositoryInMemory();
    }

    public void registerNewPetForAdoption(Pet newPet){
        if (petRepository.exists(newPet)){
            throw new DomainException("Já existe um pet cadastrado com esse nome!\n");
        }

        petRepository.save(newPet);
        FileUtils.writeForm(newPet);
    }

    public List<Pet> searchAllPets(){
        return petRepository.getAllPets();
    }

    public List<Pet> searchPetsByName(String name, PetType petType){
        return petRepository.findByName(name, petType);
    }

    public List<Pet> searchPetsBySex(PetSex petSex, PetType petType){
        return petRepository.findBySex(petSex, petType);
    }

    public List<Pet> searchPetsByAge(double age, PetType petType){
        return petRepository.findByAge(age, petType);
    }

    public List<Pet> searchPetsByWeight(double weight, PetType petType){
        return petRepository.findByWeight(weight, petType);
    }

    public List<Pet> searchPetsByRace(String name, PetType petType){
        return petRepository.findByRace(name, petType);
    }

    public List<Pet> searchPetsByAddress(String address, PetType petType){
        return petRepository.findByAddress(address, petType);
    }

    public void changePetName(String name, Pet pet){
        pet.setName(name);
    }

    public void changePetAddress(String name, Pet pet){
        pet.setAddress(name);
    }

    public void changePetAge(double age, Pet pet){
        pet.setAge(age);
    }

    public void changePetWeight(double weight, Pet pet){
        pet.setWeight(weight);
    }

    public void changePetRace(String race, Pet pet){
        pet.setRace(race);
    }

    public void deletePet(Pet pet){
        petRepository.delete(pet);
    }
}

