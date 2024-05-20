package learn.lostandfound.controllers;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.domain.LocationService;
import learn.lostandfound.domain.PostService;
import learn.lostandfound.domain.Result;
import learn.lostandfound.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> findAll() throws DataAccessException {
        return service.findAll();
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> findById(@PathVariable int postId) throws DataAccessException {
        Post post = service.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Post post) throws DataAccessException {
        Result<Post> result = service.add(post);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Object> update(@PathVariable int postId, @RequestBody Post post) throws DataAccessException {
        if (postId != post.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Post> result = service.update(post);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable int postId) throws DataAccessException {
        if (service.deleteById(postId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}