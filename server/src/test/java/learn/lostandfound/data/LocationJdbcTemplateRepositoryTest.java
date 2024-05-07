package learn.lostandfound.data;

import learn.lostandfound.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationJdbcTemplateRepositoryTest {

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllLocations() {
        List<Location> locations = repository.findAll();
        assertNotNull(locations);
        assertTrue(locations.size() >= 1);
    }

    @Test
    void shouldFindLocationById() throws DataAccessException {
        Location location = repository.findByLocationId(1);
        assertNotNull(location);
        assertEquals("123 Maple Street", location.getAddress());
    }

    @Test
    void shouldNotFindLocationByInvalidId() throws DataAccessException {
        Location location = repository.findByLocationId(999);
        assertNull(location);
    }

    @Test
    void shouldAddNewLocation() throws DataAccessException {
        Location location = new Location();
        location.setAddress("456 Elm St");
        location.setCity("Springfield");
        location.setState("IL");
        location.setZipCode("12345");
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertNotNull(actual.getLocationId());
    }

//    @Test
//    void shouldUpdateLocation() throws DataAccessException {
//        Location location = new Location();
//        location.setLocationId(2);
//        location.setAddress("789 Oak St");
//        location.setCity("Springfield");
//        location.setState("IL");
//        location.setZipCode("54321");
//        assertTrue(repository.update(location));
//    }
//
//    @Test
//    void shouldDeleteLocation() throws DataAccessException {
//        assertTrue(repository.deleteByLocationId(3));
//        assertFalse(repository.deleteByLocationId(3));
//    }
}
