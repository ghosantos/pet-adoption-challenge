package repository;

import model.Pet;
import model.enums.PetSex;
import model.enums.PetType;

import java.util.List;

public interface PetRepository {
    List<Pet> getAllPets();
    void save(Pet pet);
    void delete(Pet pet);
    boolean exists(Pet pet);

    List<Pet> findByName(String name, PetType petType);
    List<Pet> findBySex(PetSex petSex, PetType petType);
    List<Pet> findByAge(double age, PetType petType);
    List<Pet> findByWeight(double weight, PetType petType);
    List<Pet> findByRace(String race, PetType petType);
    List<Pet> findByAddress(String address, PetType petType);
}
