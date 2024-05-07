package learn.lostandfound.controllers;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.domain.LocationService;
import learn.lostandfound.domain.Result;
import learn.lostandfound.models.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> findAll() throws DataAccessException {
        return service.findAll();
    }
    @GetMapping("/{locationId}")
    public ResponseEntity<Location> findById(@PathVariable int locationId) throws DataAccessException {
        Location location = service.findById(locationId);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Location location) throws DataAccessException {
        Result<Location> result = service.add(location);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}