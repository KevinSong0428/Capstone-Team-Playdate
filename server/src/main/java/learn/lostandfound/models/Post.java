package learn.lostandfound.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;

    private Animal animal;
    private User user;
    private String url;
    private String description;

    private LocalDateTime dateTime;
    private String gender;
    private Location location;
    private int size;
    private boolean found;

    public Post(int id, Animal animal, User user, String url, String description, LocalDateTime dateTime, String gender, Location location, int size, boolean found) {
        this.id = id;
        this.animal = animal;
        this.user = user;
        this.url = url;
        this.description = description;
        this.dateTime = dateTime;
        this.gender = gender;
        this.location = location;
        this.size = size;
        this.found = found;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Post() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && size == post.size && found == post.found && Objects.equals(animal, post.animal) && Objects.equals(user, post.user) && Objects.equals(url, post.url) && Objects.equals(description, post.description) && Objects.equals(dateTime, post.dateTime) && Objects.equals(gender, post.gender) && Objects.equals(location, post.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animal, user, url, description, dateTime, gender, location, size, found);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", animal=" + animal +
                ", user=" + user +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", gender='" + gender + '\'' +
                ", location=" + location +
                ", size=" + size +
                ", found=" + found +
                '}';
    }
}
