package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.LocationRepository;
import learn.lostandfound.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepository locationRepository;


    @Test
    void shouldFindAllLocations() throws DataAccessException {
        List<Location> expectedLocations = new ArrayList<>();
        expectedLocations.add(new Location(1, "123 Maple Street", "Springfield", "IL", "62704"));
        expectedLocations.add(new Location(2, "456 Oak Avenue", "Madison", "WI", "53703"));
        expectedLocations.add(new Location(3, "789 Pine Lane", "Phoenix", "AZ", "85001"));
        expectedLocations.add(new Location(4, "1012 Birch Road", "Austin", "TX", "78701"));
        expectedLocations.add(new Location(5, "1314 Elm Street", "Orlando", "FL", "32801"));
        expectedLocations.add(new Location(6, "1516 Cedar Blvd", "Seattle", "WA", "98101"));
        expectedLocations.add(new Location(7, "1718 Willow Way", "Denver", "CO", "80202"));
        when(locationRepository.findAll()).thenReturn(expectedLocations);

        List<Location> actualLocations = service.findAll();

        assertEquals(expectedLocations.size(), actualLocations.size());
        for (int i = 0; i < expectedLocations.size(); i++) {
            assertEquals(expectedLocations.get(i), actualLocations.get(i));
        }
    }

    @Test
    void shouldFindLocationById() throws DataAccessException {
        Location expectedLocation = new Location(1, "123 Maple Street", "Springfield", "IL", "62704");
        when(locationRepository.findByLocationId(1)).thenReturn(expectedLocation);

        Location actualLocation = service.findById(1);

        assertEquals(expectedLocation, actualLocation);
    }
    @Test
    void shouldAddValidLocation() throws DataAccessException {
        Location location = new Location(0, "555 King Street", "New York", "NY", "61222");
        Location mockOut = new Location(8, "555 King Street", "New York", "NY", "61222");

        when(locationRepository.add(location)).thenReturn(mockOut);

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddLocationWithInvalidAddress() throws DataAccessException {
        Location locationToAdd = new Location(0, null, "New York", "NY", "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Address is required.", result.getMessages().get(0));
    }
    @Test
    void shouldNotAddLocationWithBlankAddress() throws DataAccessException {
        Location locationToAdd = new Location(0, "", "New York", "NY", "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Address is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddLocationWithInvalidCity() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", null, "NY", "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("City is required.", result.getMessages().get(0));
    }
    @Test
    void shouldNotAddLocationWithBlankCity() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", "", "NY", "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("City is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddLocationWithNullState() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", "New York", null, "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("State is required.", result.getMessages().get(0));
    }
    @Test
    void shouldNotAddLocationWithBlankState() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", "New York", "", "61222");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("State is required.", result.getMessages().get(0));
    }
    @Test
    void shouldNotAddLocationWithNullZipCode() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", "New York", "NY", null);

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Zip Code is required.", result.getMessages().get(0));
    }
    @Test
    void shouldNotAddLocationWithBlankZipCode() throws DataAccessException {
        Location locationToAdd = new Location(0, "555 King Street", "New York", "NY", "");

        Result<Location> result = service.add(locationToAdd);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Zip Code is required.", result.getMessages().get(0));
    }
}
