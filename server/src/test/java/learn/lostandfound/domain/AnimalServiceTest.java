package learn.lostandfound.domain;

import learn.lostandfound.data.AnimalRepository;
import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.models.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnimalServiceTest {
    @Autowired
    AnimalService service;

    @MockBean
    AnimalRepository repository;

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Animal> expected = List.of(
                new Animal(1, "Buddy", "Friendly, loves children", "Dog", "Golden Retriever"),
                new Animal(2, "Whiskers", "Shy, has a missing tail", "Cat", "Siamese"),
                new Animal(3, "Coco", "Very energetic, loves to play fetch", "Dog", "Border Collie"),
                new Animal(4, "Daisy", "Calm and loving", "Dog", "Beagle"),
                new Animal(5, "Spike", "Loud and protective", "Dog", "German Shepherd"),
                new Animal(6, "Ginger", "Affectionate, loves to cuddle", "Cat", "Persian"),
                new Animal(7, "Shadow", "Independent, night prowler", "Cat", "Maine Coon")
        );
        when(repository.findAll()).thenReturn(expected);
        List<Animal> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Animal expected = new Animal(1, "Buddy", "Friendly, loves children", "Dog", "Golden Retriever");
        when(repository.findById(1)).thenReturn(expected);
        Animal actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAdd() throws DataAccessException {
        // should not add null
        Result<Animal> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if animal id is greater than 0.
        Animal animal = new Animal(1, "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        result = service.add(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if characteristics is null.
        animal = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setCharacteristic(null);
        result = service.add(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // should not add if characteristics is blank
        animal = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setCharacteristic("      ");
        result = service.add(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if animal type is null.
        animal = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setAnimal(null);
        result = service.add(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // should not add if animal is blank
        animal = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setAnimal("      ");
        result = service.add(animal);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldAddWhenValid() throws DataAccessException {
        Animal expected = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        Animal arg = new Animal("Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        arg.setAnimalId(0);

        when(repository.add(arg)).thenReturn(expected);
        Result<Animal> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotUpdate() throws DataAccessException {
        // should not update null
        Result<Animal> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());

        Animal animal = new Animal(1, "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        // Should not update if animal id is 0.
        animal.setAnimalId(0);
        result = service.update(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update if characteristics is null.
        animal = new Animal(1, "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setCharacteristic(null);
        result = service.update(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update if characteristics is empty.
        animal = new Animal(1, "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setCharacteristic("        ");
        result = service.update(animal);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update if not found.
        animal = new Animal(1, "Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        animal.setAnimalId(100);
        animal.setName("Test Updated Name");
        // Mocking the repository update method to return false is not strictly necessary
        // as the default mock implementation for that method will return false.
        // Adding here for clarity on what our expectations are.
        when(repository.update(animal)).thenReturn(false);
        result = service.update(animal);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }

    @Test
    void shouldUpdateWhenValid() throws DataAccessException {
        Animal expected = new Animal(1,"Test Name", "Test Characteristics", "Test Animal", "Test Breed");
        expected.setName("Updated Test Name");

        when(repository.update(expected)).thenReturn(true);
        Result<Animal> result = service.update(expected);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteInvalidId() throws DataAccessException {
        assertFalse(service.deleteById(10));
    }

    @Test
    void shouldDelete() throws DataAccessException {
        when(repository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }
}