package learn.lostandfound.controllers;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.domain.AnimalService;
import learn.lostandfound.domain.Result;
import learn.lostandfound.models.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/animal")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Animal> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<Object> findById(@PathVariable int animalId) throws DataAccessException {
        Animal animal = service.findById(animalId);
        if (animal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Animal animal) throws DataAccessException {
        Result<Animal> result = service.add(animal);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Object> update(@PathVariable int animalId, @RequestBody Animal animal) throws DataAccessException {
        if (animalId != animal.getAnimalId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Animal> result = service.update(animal);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Object> deleteById(@PathVariable int animalId) throws DataAccessException {
        if (service.deleteById(animalId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
