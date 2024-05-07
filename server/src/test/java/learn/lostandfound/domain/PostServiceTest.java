package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.PostJdbcTemplateRepositoryDouble;
import learn.lostandfound.models.Animal;
import learn.lostandfound.models.Post;
import learn.lostandfound.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostService service = new PostService(
            new PostJdbcTemplateRepositoryDouble()
    );
    @Test
    void shouldFindAll() throws DataAccessException {
        List<Post> all = service.findAll();
        assertTrue(all.size()>2);
    }
    @Test
    void shouldFindById() throws DataAccessException{
        Post expected = service.findById(1);
        assertNotNull(expected);
        assertEquals("Found this cat in a garage, seems lost.", expected.getDescription());
    }
    @Test
    void shouldNotFindIfIdDoesNotExist() throws DataAccessException{
        Post expected = service.findById(999);
        assertNull(expected);
    }
    @Test
    void shouldAdd() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "fakeurl.png", "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals("New Post", result.getPayload().getDescription());
        assertEquals("fakeurl.png", result.getPayload().getUrl());
        assertEquals(LocalDateTime.parse("2024-05-02T08:00:00"), result.getPayload().getDateTime());
        assertEquals(4, result.getPayload().getLocationId());
        assertEquals("female", result.getPayload().getGender());
        assertEquals(25, result.getPayload().getSize());
        assertFalse(result.getPayload().isFound());
    }
    @Test
    void shouldNotAddWithZeroAnimalId() throws DataAccessException {
        Animal animal = new Animal(0, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "fakeurl.png", "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithZeroUserId() throws DataAccessException {
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(0, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "fakeurl.png", "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithNullUrl() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, null, "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithEmptyUrl() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "", "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithNullDateTime() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "fakeurl.png", "New Post", null, 4, "female", 25, false);
        Result<Post> result = service.add(newPost);
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotAddWithZeroSize() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post newPost = new Post( 0,animal, user, "fakeurl.png", "New Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 0, false);
        Result<Post> result = service.add(newPost);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
 //updates

    @Test
    void shouldUpdate() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertTrue(result.isSuccess());
        System.out.println(result.getPayload());
    }
    @Test
    void shouldNotUpdateWithZeroAnimalId() throws DataAccessException{
        Animal animal = new Animal(0, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateWithZeroUserId() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(0, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateWithNullUrl() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, null, "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateWithEmptyUrl() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "", "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateWithNullDescription() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", null, LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateWithEmptyDescription() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", "", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 25, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateWithZeroSize() throws DataAccessException{
        Animal animal = new Animal(1, "", "DESC", "ANIMAL", "BREED");
        User user = new User(1, "Name", "Email", "Phone");
        Post postToUpdate = new Post( 1,animal, user, "fakeurl.png", "update Post", LocalDateTime.parse("2024-05-02T08:00:00"), 4, "female", 0, false);
        Result<Post> result = service.update(postToUpdate);
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldDelete() throws DataAccessException {
        boolean response = service.deleteById(3);
        assertTrue(response);
    }
    @Test
    void shouldNotDeleteIfIdDoesNotExist() throws DataAccessException {
        boolean response = service.deleteById(999999);
        assertFalse(response);
    }
}