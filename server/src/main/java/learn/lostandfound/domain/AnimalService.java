package learn.lostandfound.domain;

import learn.lostandfound.data.AnimalRepository;
import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.models.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }
    public List<Animal> findAll() throws DataAccessException {
        return repository.findAll();
    }
    public Animal findById(int id) throws DataAccessException {
        return repository.findById(id);
    }
    public Result<Animal> add(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);
        if (!result.isSuccess()) {
            return result;
        }

        if(animal.getAnimalId() != 0){
            result.addMessage("Animal id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        animal = repository.add(animal);
        result.setPayload(animal);

        return result;
    }

    public Result<Animal> update(Animal animal) throws DataAccessException {
        Result<Animal> result = validate(animal);
        if (!result.isSuccess()) {
            return result;
        }

        if(animal.getAnimalId() <= 0){
            result.addMessage("Animal id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(animal)) {
            result.addMessage(String.format("Animal ID: %d not found.", animal.getAnimalId()), ResultType.NOT_FOUND);
        }
        result.setPayload(animal);

        return result;
    }

    public boolean deleteById(int animalId) throws DataAccessException {
        return repository.deleteById(animalId);
    }

    public Result<Animal> validate(Animal animal) {
        Result<Animal> result = new Result<>();

        if (animal == null) {
            result.addMessage("Animal cannot be null.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(animal.getCharacteristic())) {
            result.addMessage("Animal characteristics are required.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(animal.getAnimal())) {
            result.addMessage("Animal type is required.", ResultType.INVALID);
        }

        return result;
    }


}
