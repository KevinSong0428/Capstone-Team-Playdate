package learn.lostandfound.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;

    private int animalId;
    private int userId;
    private String url;
    private String description;

    private LocalDateTime dateTime;
    private int locationId;
    private String gender;

    private int size;
    private boolean found;

    public Post(int id, int animalId, int userId, String url, String description, LocalDateTime dateTime, int locationId, String gender, int size, boolean found) {
        this.id = id;
        this.animalId = animalId;
        this.userId = userId;
        this.url = url;
        this.description = description;
        this.dateTime = dateTime;
        this.locationId = locationId;
        this.gender = gender;
        this.size = size;
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

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", animalId=" + animalId +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", locationId=" + locationId +
                ", gender='" + gender + '\'' +
                ", size=" + size +
                ", found=" + found +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && animalId == post.animalId && userId == post.userId && locationId == post.locationId && size == post.size && found == post.found && Objects.equals(url, post.url) && Objects.equals(description, post.description) && Objects.equals(dateTime, post.dateTime) && Objects.equals(gender, post.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalId, userId, url, description, dateTime, locationId, gender, size, found);
    }
}
