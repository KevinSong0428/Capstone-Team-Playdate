package learn.lostandfound.data;


import learn.lostandfound.models.Post;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Post> findAll() {
        return null;
    }

    public Post findByPostId(int id) {
        return null;
    }

    public Post add() {
        return null;
    }

    public boolean update(Post post) {
        return false;
    }

    public boolean deleteById(int id) {
        return false;
    }
}
