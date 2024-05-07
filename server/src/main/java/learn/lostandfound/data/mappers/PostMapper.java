package learn.lostandfound.data.mappers;

import learn.lostandfound.models.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post>  {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getInt("post_id"));
        post.setAnimalId(resultSet.getInt("animal_id"));
        post.setUserId(resultSet.getInt("animal_id"));
        post.setUrl(resultSet.getString("img_url"));
        post.setDescription(resultSet.getString("description"));
        post.setDateTime(resultSet.getTimestamp("time").toLocalDateTime());
        post.setLocationId(resultSet.getInt("location_id"));
        post.setGender(resultSet.getString("gender"));
        post.setSize(resultSet.getInt("size"));
        post.setFound(resultSet.getBoolean("found"));
        return post;
    }
}
