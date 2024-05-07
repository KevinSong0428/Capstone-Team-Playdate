package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.UserRepository;
import learn.lostandfound.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {
    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldFindAll() throws DataAccessException{
        List<User> expected = List.of(
                new User(1, "Muffy Meyer", "555-555-5555", "muffy@fakeemail.com"),
                new User(2,"John Doe", "555-123-4567", "john.doe@example.com"),
                new User(3,"Samantha Right", "555-987-6543", "s.right@example.com"),
                new User(4,"Lucas Gray", "555-876-5432", "lucas.gray@example.com"),
                new User(5,"Emily Johnson", "555-234-5678", "emily.j@example.com"),
                new User(6, "Carlos Smith", "555-345-6789", "carlos.s@example.com"),
                new User(7,"Lisa Ray", "555-456-7890", "lisa.ray@example.com"),
                new User(8,"Nina Patel", "555-567-8901", "nina.patel@example.com")
            );
        when(repository.findAll()).thenReturn(expected);
        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }
    @Test
    void shouldFindById() throws DataAccessException{
        User expected = new User(8,"Nina Patel", "555-567-8901", "nina.patel@example.com");
        when(repository.findById(8)).thenReturn(expected);
        User actual = service.findById(8);
        assertEquals(expected, actual);
    }
    @Test
    void shouldAdd() throws DataAccessException{
        User expected = new User(0,"New User", "555-567-8901", "new.userl@example.com");
        User actual =new User(0,"New User", "555-567-8901", "new.userl@example.com");

        when(repository.add(actual)).thenReturn(expected);
        Result<User> result = service.add(actual);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }
    @Test
    void shouldNotAddNullUser() throws DataAccessException{
        Result<User> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddNullOrEmptyName() throws DataAccessException{
        User user = new User(0,"", "555-567-8901", "new.userl@example.com");
        Result<User> result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,null, "555-567-8901", "new.userl@example.com");
        result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddNullOrEmptyPhone() throws DataAccessException{
        User user = new User(0,"New User", null, "new.userl@example.com");
        Result<User> result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,"New User", "", "new.userl@example.com");
        result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddNullOrEmptyEmail() throws DataAccessException{
        User user = new User(0,"New User", "555-567-8901", "");
        Result<User> result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,"New User", "555-567-8901", null);
        result= service.add(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldUpdate() throws DataAccessException{
        User expected = new User(1,"New User", "555-567-8901", "new.userl@example.com");
        expected.setName("Updated user name");

        when(repository.update(expected)).thenReturn(true);
        Result<User> result = service.update(expected);
        assertEquals(ResultType.SUCCESS, result.getType());
    }
    @Test
    void shouldNotUpdateIfUserIsNull() throws DataAccessException {
        Result<User> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotUpdateNullOrEmptyName() throws DataAccessException{
        User user = new User(0,"", "555-567-8901", "new.userl@example.com");
        Result<User> result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,null, "555-567-8901", "new.userl@example.com");
        result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotUpdateNullOrEmptyPhone() throws DataAccessException{
        User user = new User(0,"New User", null, "new.userl@example.com");
        Result<User> result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,"New User", "", "new.userl@example.com");
        result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotUpdateNullOrEmptyEmail() throws DataAccessException{
        User user = new User(0,"New User", "555-567-8901", "");
        Result<User> result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        user = new User(0,"New User", "555-567-8901", null);
        result= service.update(user);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotDeleteIfIdDoesNotExist() throws DataAccessException{
        assertFalse(service.deleteById(999999));
    }
    @Test
    void shouldDelete() throws DataAccessException{
        when(repository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }
}
