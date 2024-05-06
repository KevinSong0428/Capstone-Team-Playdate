package learn.lostandfound.models;

public class User {
    private int user_id;
    private String name;
    private String phone_number;
    private String email;
    private int animal_id;
    private String state;
    private String city;

    public User() {}

    public User(int user_id, String name, String phone_number, String email, int animal_id, String state, String city) {
        this.user_id = user_id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.animal_id = animal_id;
        this.state = state;
        this.city = city;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
