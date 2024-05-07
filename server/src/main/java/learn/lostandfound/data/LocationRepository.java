package learn.lostandfound.data;

import learn.lostandfound.models.Location;
import learn.lostandfound.models.Post;

import java.util.List;

public interface LocationRepository {
    List<Location> findAll() throws DataAccessException;
    Location findByLocationId(int id) throws DataAccessException;
    Location add(Location location) throws DataAccessException;
}
