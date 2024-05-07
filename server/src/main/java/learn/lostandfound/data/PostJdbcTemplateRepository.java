package learn.lostandfound.data;


import learn.lostandfound.data.mappers.PostMapper;
import learn.lostandfound.data.mappers.UserMapper;
import learn.lostandfound.models.Post;
import learn.lostandfound.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PostJdbcTemplateRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public PostJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Post> findAll() {
        final String sql = "SELECT * "
                + "FROM post;";
        return jdbcTemplate.query(sql, new PostMapper());
    }

    @Override
    public Post findByPostId(int postId) {
        final String sql = "SELECT *"
                + "FROM post "
                + "WHERE post_id = ?;";
        return jdbcTemplate.query(sql, new PostMapper(), postId).stream().findAny().orElse(null);
    }


// make sure to do camel casing for the models
    @Override
    public Post add(Post post) throws DataAccessException {
        final String sql = "INSERT INTO post (animal_id, user_id, img_url, `description`, time, location_id, gender, size, `found`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, post.getAnimalId());
            ps.setInt(2, post.getUserId());
            ps.setString(3, post.getUrl());
            ps.setString(4, post.getDescription());
            ps.setObject(5, post.getDateTime());
            ps.setInt(6, post.getLocationId());
            ps.setString(7, post.getGender());
            ps.setInt(8, post.getSize());
            ps.setBoolean(9, post.isFound());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0) {
            return null;
        }

        post.setId(keyHolder.getKey().intValue());
        return post;
    }

    @Override
    public boolean update(Post post) throws DataAccessException{
        final String sql = "UPDATE post set " +
                "`img_url` = ?, location_id = ?, `found` = ? " +
                "WHERE post_id = ?;";

        return jdbcTemplate.update(sql, post.getUrl(), post.getLocationId(), post.isFound(), post.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM post where post_id = ?;", id) > 0;
    }
}
