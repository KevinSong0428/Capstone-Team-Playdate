package learn.lostandfound.data;

import learn.lostandfound.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PostJdbcTemplateRepositoryTest {

    @Autowired
    PostJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindPosts() {
        List<Post> post = repository.findAll();
        assertNotNull(post);
        assertTrue(post.size() >= 7);
    }

    @Test
    void shouldFindPost2() {
        Post Coco = repository.findByPostId(2);
        assertEquals("Coco went missing during our walk in the neighborhood.", Coco.getDescription());
    }

    @Test
    void shouldNotFindPost99() {
        Post post = repository.findByPostId(99);
        assertNull(post);
    }

    @Test
    void shouldAddNewPost() throws DataAccessException {
        Post post = new Post();
        post.setAnimalId(2);
        post.setUserId(1);
        post.setUrl("wakowako.com");
        post.setDescription("I found this animal but do not know anything else");
        post.setDateTime(LocalDateTime.parse("2008-10-18T00:00:00"));
        post.setLocationId(1);
        post.setGender("male");
        post.setSize(48);
        post.setFound(false);
        Post actual =  repository.add(post);
        assertNotNull(actual);
        assertEquals(8, actual.getId());
    }

    @Test
    void shouldUpdatePost() throws DataAccessException {
        Post post = new Post();
        post.setUrl("why.com");
        post.setLocationId(2);
        post.setFound(false);
        post.setId(4);
        assertTrue(repository.update(post));
    }

    @Test
    void shouldDeletePost() { // Handles both happy and unhappy
        assertTrue(repository.deleteById(6));
        assertFalse(repository.deleteById(6));
    }

}