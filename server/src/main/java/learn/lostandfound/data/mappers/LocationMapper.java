package learn.lostandfound.data.mappers;

import learn.lostandfound.models.Location;
import learn.lostandfound.models.Post;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setAddress(resultSet.getString("address"));
        location.setCity(resultSet.getString("city"));
        location.setState(resultSet.getString("state"));
        location.setZipCode(resultSet.getString("zip_code"));
        return location;
    }
}
