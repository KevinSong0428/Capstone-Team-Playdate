package learn.lostandfound.models;

import java.util.Objects;

public class Animal {
    private int animalId;
    private String name;
    private String characteristic;
    private String animal;
    private String breed;


    public Animal(String name, String characteristic, String animal, String breed) {
        this.name = name;
        this.characteristic = characteristic;
        this.animal = animal;
        this.breed = breed;
    }
  
    public Animal(int animal_id, String name, String characteristic, String animal, String breed) {
        this.animalId = animal_id;
        this.name = name;
        this.characteristic = characteristic;
        this.animal = animal;
        this.breed = breed;
    }
    public Animal() {}

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal1 = (Animal) o;
        return animalId == animal1.animalId && Objects.equals(name, animal1.name) && Objects.equals(characteristic, animal1.characteristic) && Objects.equals(animal, animal1.animal) && Objects.equals(breed, animal1.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, name, characteristic, animal, breed);
    }
}
