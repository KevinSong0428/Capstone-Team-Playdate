package learn.lostandfound.data;

import learn.lostandfound.models.User;
import java.util.List;
public interface UserRepository {
    List<User> findAll() throws DataAccessException;
    User findById(int id) throws DataAccessException;
    User add(User user) throws DataAccessException;
    boolean update(User user) throws DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
}
