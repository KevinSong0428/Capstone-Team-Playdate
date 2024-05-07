package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.PostRepository;
import learn.lostandfound.data.UserRepository;
import learn.lostandfound.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) { this.repository = repository; }

    public List<Post> findAll() throws DataAccessException {return repository.findAll();}

    public Post findById(int post_id) throws DataAccessException { return repository.findByPostId(post_id);}

    public Result<Post> add(Post post) throws DataAccessException {
        Result<Post> result = validate(post);
        if(!result.isSuccess()){
            return result;
        }
        if(post.getId() != 0){
            result.addMessage("postId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        post = repository.add(post);
        result.setPayload(post);
        return result;
    }
    public Result<Post> update(Post post) throws DataAccessException {
        Result<Post> result = validate(post);
        if(!result.isSuccess()){
            return result;
        }
        if(post.getId()<=0){
            result.addMessage("post id must be set for `update` operation", ResultType.INVALID);
        }
        if(!repository.update(post)){
            String msg = String.format("post id: %s, not found", post.getId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }
    public boolean deleteById(int postId) throws DataAccessException {
        return repository.deleteById(postId);
    }
    private Result<Post> validate(Post post){
        Result<Post> result = new Result<>();
        if(post== null){
            result.addMessage("post cannot be null", ResultType.INVALID);
            return result;
        }
        if(post.getAnimalId()<=0){
            result.addMessage("Animal ID is required.", ResultType.INVALID);
            return result;
        }
        if(post.getUserId()<=0){
            result.addMessage("User ID is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(post.getUrl())){
            result.addMessage("Image URL is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(post.getDescription())){
            result.addMessage("Description is required.", ResultType.INVALID);
        }
        if(post.getDateTime()==null){
            result.addMessage("Date time is required.", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(post.getGender())){
            result.addMessage("Gender is required", ResultType.INVALID);
        }
        if(post.getSize()<=0){
            result.addMessage("size is required", ResultType.INVALID);
        }
        return result;
    }
}
