package learn.lostandfound.data.mappers;

import learn.lostandfound.models.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post>  {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {

    }
}
