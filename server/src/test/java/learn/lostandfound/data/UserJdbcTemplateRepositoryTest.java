package learn.lostandfound.data;

import learn.lostandfound.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<User> users = repository.findAll();
        assertNotNull(users);

        // can't predict order - default is 8
        // if delete is first, we're down to 7
        // if add is first, we may go as high as 9
        assertTrue(users.size() >= 7 && users.size() <= 9);
    }

    @Test
    void shouldFindById() throws DataAccessException {
        User muffy = new User(1, "Muffy Meyer", "555-555-5555", "muffy@fakeemail.com");
        User john = new User(2, "John Doe", "555-123-4567", "john.doe@example.com");

        User actual = repository.findById(1);
        assertEquals(muffy, actual);

        actual = repository.findById(2);
        assertEquals(john, actual);

        actual = repository.findById(10);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        User user = new User("Test Name", "Test Phone", "Test Email");
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(9, actual.getUserId());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        User user = new User("Test Updating Name", "Test Phone", "Test Email");
        user.setUserId(1);
        user.setName("Test Updated Name");
        assertTrue(repository.update(user));
    }

    @Test
    void shouldDelete() throws DataAccessException {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(10));
    }
}