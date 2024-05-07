package learn.lostandfound.data;

import learn.lostandfound.data.mappers.UserMapper;
import learn.lostandfound.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;
    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        final String sql = "SELECT *"
                + "FROM users;";
        return jdbcTemplate.query(sql, new UserMapper());
    }
    @Override
    public User findById(int id) throws DataAccessException {
        final String sql = "SELECT * " +
                "FROM users" +
                "WHERE users.id = ?;";
        return jdbcTemplate.query(sql, new UserMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User add(User user) throws DataAccessException {
        final String sql = "INSERT INTO users (`name`, phone, email, state, city) " +
                "VALUES (?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getState());
            ps.setString(5, user.getCity());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }
    @Override
    public boolean update(User user) throws DataAccessException {
        final String sql = "UPDATE users set " +
                "`name` = ?, phone = ?, email = ? " +
                "WHERE user_id = ?;";

        return jdbcTemplate.update(sql, user.getName(), user.getPhoneNumber(), user.getEmail(), user.getUserId()) > 0;
    }
    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM users where user_id = ?;", id) > 0;
    }
}