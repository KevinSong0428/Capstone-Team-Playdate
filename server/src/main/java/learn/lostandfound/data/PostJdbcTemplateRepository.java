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
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostJdbcTemplateRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public PostJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Post> findAll() {
        final String sql = "SELECT " +
                "post.post_id, " +
                "animal.animal_id, " +
                "animal.name as animal_name, " +
                "animal.characteristics, " +
                "animal.animal as animal, " +
                "animal.breed as breed, " +
                "u.user_id as user_id, " +
                "u.name as user_name, " +
                "u.phone as phone, " +
                "u.email as email, " +
                "post.img_url, " +
                "post.description, " +
                "post.time, " +
                "location.location_id, " +
                "location.address, " +
                "location.city, " +
                "location.state, " +
                "location.zip_code, " +
                "post.gender, " +
                "post.size, " +
                "post.found " +
                "FROM post " +
                "JOIN animal ON animal.animal_id = post.animal_id " +
                "JOIN user u ON u.user_id = post.user_id " +
                "JOIN location ON location.location_id = post.location_id;";
        return jdbcTemplate.query(sql, new PostMapper());
    }

    @Override
    public Post findByPostId(int postId) {
        final String sql = "SELECT " +
                "post.post_id, " +
                "animal.animal_id, " +
                "animal.name as animal_name, " +
                "animal.characteristics, " +
                "animal.animal, " +
                "animal.breed, " +
                "`user`.user_id, " +
                "`user`.name as user_name, " +
                "`user`.phone, " +
                "`user`.email, " +
                "img_url, " +
                "description, " +
                "time, " +
                "location.location_id, " +
                "location.address, " + // Added missing fields from the location table
                "location.city, " +
                "location.state, " +
                "location.zip_code, " +
                "gender, " +
                "size, " +
                "found " +
                "FROM post " +
                "JOIN animal ON animal.animal_id = post.animal_id " +
                "JOIN `user` ON `user`.user_id = post.user_id " +
                "JOIN location ON location.location_id = post.location_id " +
                "WHERE post.post_id = ?;";
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
            ps.setInt(1, post.getAnimal().getAnimalId());
            ps.setInt(2, post.getUser().getUserId());
            ps.setString(3, post.getUrl());
            ps.setString(4, post.getDescription());
            ps.setObject(5, post.getDateTime());
            ps.setInt(6, post.getLocation().getLocationId());
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
        final String sql = "UPDATE post SET " +
                "animal_id = ?, user_id = ?, img_url = ?, `description` = ?, time = ?, location_id = ?, gender = ?," +
                "`size` = ?, `found` = ? " +
                "WHERE post_id = ?;";

        return jdbcTemplate.update(sql, post.getAnimal().getAnimalId(), post.getUser().getUserId(),
                post.getUrl(), post.getDescription(), LocalDateTime.now(),
                post.getLocation().getLocationId(), post.getGender(), post.getSize(), post.isFound(), post.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM post where post_id = ?;", id) > 0;
    }
}
