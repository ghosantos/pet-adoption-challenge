package repository;

import model.Pet;
import model.enums.PetSex;
import model.enums.PetType;

import java.util.ArrayList;
import java.util.List;

public class PetRepositoryInMemory implements PetRepository {

    private final List<Pet> petList = new ArrayList<>();

    @Override
    public List<Pet> getAllPets(){
        return new ArrayList<>(petList);
    }

    @Override
    public void save(Pet pet){
        petList.add(pet);
    }

    public void delete(Pet pet){
        petList.remove(pet);
    }

    @Override
    public boolean exists(Pet pet){
        String name = pet.getName();

        for (Pet p : petList){
            if (name.equalsIgnoreCase(p.getName()) && pet.getPetType() == p.getPetType()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pet> findByNameAndType(String name, PetType petType) {
        List<Pet> result = new ArrayList<>();
        String filter = name.trim().toLowerCase();

        for (Pet p : petList){
            boolean matchesName = p.getName().toLowerCase().contains(filter.toLowerCase());
            boolean matchesType = p.getPetType() == petType;

            if (matchesName && matchesType) {
                result.add(p);
            }
        }

        return result;
    }

    @Override
    public List<Pet> findBySexAndType(PetSex petSex, PetType petType){
        List<Pet> result = new ArrayList<>();

        for (Pet p : petList){
            boolean matchesType = p.getPetType() == petType;
            boolean matchesSex = p.getPetGender() == petSex;

            if (matchesType && matchesSex){
                result.add(p);
            }
        }

        return result;
    }

    @Override
    public List<Pet> findByAge(double age, PetType petType){
        List<Pet> foundPets = new ArrayList<>();

        for (Pet p : petList){
            boolean matchesAge = p.getAge() == age;
            boolean matchesType = p.getPetType() == petType;

            if (matchesAge && matchesType){
                foundPets.add(p);
            }
        }

        return foundPets;
    }

    @Override
    public List<Pet> findByWeight(double weight, PetType petType){
        List<Pet> foundPets = new ArrayList<>();

        for (Pet p : petList){
            boolean matchesWeight = p.getWeight() == weight;
            boolean matchesType = p.getPetType() == petType;

            if (matchesWeight && matchesType){
                foundPets.add(p);
            }
        }

        return foundPets;
    }

    @Override
    public List<Pet> findByRace(String race, PetType petType){
        List<Pet> foundPets = new ArrayList<>();

        for (Pet p : petList){
            boolean matchesRace = p.getRace().toLowerCase().contains(race.toLowerCase());
            boolean matchesType = p.getPetType() == petType;

            if (matchesRace && matchesType){
                foundPets.add(p);
            }
        }

        return foundPets;
    }

    @Override
    public List<Pet> findByAddress(String address, PetType petType){
        List<Pet> foundPets = new ArrayList<>();

        for (Pet p : petList){
            boolean matchesAddress = p.getAddress().toLowerCase().contains(address.toLowerCase());
            boolean matchesType = p.getPetType() == petType;

            if (matchesAddress && matchesType){
                foundPets.add(p);
            }
        }

        return foundPets;
    }
}
