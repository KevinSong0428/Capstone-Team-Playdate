package learn.lostandfound.models;

public class User {
    private int userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String state;
    private String city;

    public User() {}

    public User(int userId, String name, String phoneNumber, String email, String state, String city) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
        this.city = city;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

}
