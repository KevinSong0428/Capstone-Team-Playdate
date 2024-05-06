package learn.lostandfound.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Post {
    private int id;

    private int animal_id;
    private int user_id;
    private String url;
    private String description;

    private LocalDateTime dateTime;
    private boolean found;

    public Post(int id, int animal_id, int user_id, String url, String description, LocalDateTime dateTime, boolean found) {
        this.id = id;
        this.animal_id = animal_id;
        this.user_id = user_id;
        this.url = url;
        this.description = description;
        this.dateTime = dateTime;
        this.found = found;
    }
    public Post() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
