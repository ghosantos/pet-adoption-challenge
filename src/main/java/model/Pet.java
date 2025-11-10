package model;

import model.enums.PetGender;
import model.enums.PetType;

public class Pet {
    private String petName;
    private PetType petType;
    private PetGender petGender;
    private String address;
    private Double petAge;
    private Double petWeight;
    private String petRace;

    public Pet(String petName, PetType petType, PetGender petGender, String address, Double petAge, Double petWeight, String petRace) {
        this.petName = petName;
        this.petType = petType;
        this.petGender = petGender;
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

    public PetGender getPetGender() {
        return petGender;
    }

    public void setPetGender(PetGender petGender) {
        this.petGender = petGender;
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
        return  "Nome: " + petName + "\n"
                + "Tipo: " + petType + "\n"
                + "Gênero: " + petGender + "\n"
                + "Endereço: " + address + "\n"
                + "Idade: " + petAge + "\n"
                + "Peso: " + petWeight + "Kg" + "\n"
                + "Raça: " + petRace + "\n";
    }
}
