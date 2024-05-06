package learn.lostandfound.models;

public class Animal {
    private int animal_id;
    private String name;
    private String characteristic;
    private String animal;
    private String breed;

    public Animal(int animal_id, String name, String characteristic, String animal, String breed) {
        this.animal_id = animal_id;
        this.name = name;
        this.characteristic = characteristic;
        this.animal = animal;
        this.breed = breed;
    }
    public Animal() {}

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
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

}
