package learn.lostandfound.data;

import learn.lostandfound.models.User;

import java.util.List;

public class UserJdbcTemplateRepository implements UserRepository{
    public UserJdbcTemplateRepository() {
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        return null;
    }
    @Override
    public User findById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public User findByAnimalId(int id) throws DataAccessException {
        return null;
    }
    @Override
    public User add(User user) throws DataAccessException {
        return null;
    }
    @Override
    public boolean update(User user) throws DataAccessException {
        return false;
    }
    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }
}
 // Lol we have a 30 min time limit