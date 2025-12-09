package model;

import exceptions.DomainException;
import model.enums.PetSex;
import model.enums.PetType;

public class Pet {
    private String petName;
    private PetType petType;
    private PetSex petSex;
    private String address;
    private Double petAge;
    private Double petWeight;
    private String petRace;

    public Pet(String petName, PetType petType, PetSex petSex, String address, Double petAge, Double petWeight, String petRace) {
        this.petName = petName;
        this.petType = petType;
        this.petSex = petSex;
        this.address = address;
        this.petAge = petAge;
        this.petWeight = petWeight;
        this.petRace = petRace;
    }

    public String getName() {
        return petName;
    }

    public void setName(String name) {
        this.petName = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetSex getPetGender() {
        return petSex;
    }

    public void setPetGender(PetSex petSex) {
        this.petSex = petSex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAge() {
        return petAge;
    }

    public void setAge(Double age) {
        this.petAge = age;
    }

    public Double getWeight() {
        return petWeight;
    }

    public void setWeight(Double weight) {
        this.petWeight = weight;
    }

    public String getRace() {
        return petRace;
    }

    public void setRace(String race) {
        this.petRace = race;
    }

    @Override
    public String toString() {
        return petName + " - " + petType + " - " + petSex + " - " + address + " - " + petAge + " anos" + " - " + petWeight + "Kg" + " - " + petRace;
    }
}
