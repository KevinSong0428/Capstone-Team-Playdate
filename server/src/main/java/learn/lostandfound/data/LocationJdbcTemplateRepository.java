package learn.lostandfound.data;

import learn.lostandfound.data.mappers.LocationMapper;
import learn.lostandfound.data.mappers.PostMapper;
import learn.lostandfound.models.Location;
import learn.lostandfound.models.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationJdbcTemplateRepository implements LocationRepository{

    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> findAll() {
        final String sql = "SELECT * "
                + "FROM location;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location findByLocationId(int locationId) throws DataAccessException {
        final String sql = "SELECT *"
                + "FROM location "
                + "WHERE location_id = ?;";
        return jdbcTemplate.query(sql, new LocationMapper(), locationId).stream().findAny().orElse(null);
    }

    @Override
    public Location add(Location location) throws DataAccessException {
        final String sql = "INSERT INTO location (address, city, state, zip_code) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getAddress());
            ps.setString(2, location.getCity());
            ps.setString(3, location.getState());
            ps.setString(4, location.getZipCode());
            return ps;
        }, keyHolder);
        if (rowsAffected <=0) {
            return null;
        }
        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

//    @Override
//    public boolean update(Location location) throws DataAccessException {
//        final String sql = "UPDATE location SET address = ?, city = ?, state = ?, zip_code = ? " +
//                "WHERE location_id = ?";
//
//        return jdbcTemplate.update(sql, location.getAddress(), location.getCity(), location.getState(),
//                location.getZipCode(), location.getLocationId()) > 0;
//    }
//
//    @Override
//    public boolean deleteByLocationId(int id) throws DataAccessException {
//        return jdbcTemplate.update("DELETE FROM location where location_id = ?;", id) > 0;
//    }

}
