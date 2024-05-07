package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.LocationRepository;
import learn.lostandfound.models.Location;
import learn.lostandfound.models.Post;

import java.util.List;

public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }
    public List<Location> findAll() throws DataAccessException {
        return repository.findAll();
    }
    public Location findById(int locationId) throws DataAccessException {
        return repository.findByLocationId(locationId);
    }
    public Result<Location> add(Location location) throws DataAccessException {
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }
        if(location.getLocationId() != 0){
            result.addMessage("locationId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        location = repository.add(location);
        result.setPayload(location);
        return result;
    }

    private Result<Location> validate(Location location){
        Result<Location> result = new Result<>();
        if(location== null){
            result.addMessage("Location cannot be null", ResultType.INVALID);
            return result;
        }
        if(Validations.isNullOrBlank(location.getAddress())){
            result.addMessage("Address is required.", ResultType.INVALID);
            return result;
        }
        if(Validations.isNullOrBlank(location.getCity())){
            result.addMessage("City is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(location.getState())){
            result.addMessage("State is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(location.getZipCode())){
            result.addMessage("Zip Code is required.", ResultType.INVALID);
        }
        return result;
    }
}
