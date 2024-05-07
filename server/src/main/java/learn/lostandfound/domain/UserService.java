package learn.lostandfound.domain;

import learn.lostandfound.data.DataAccessException;
import learn.lostandfound.data.UserRepository;
import learn.lostandfound.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){this.repository = repository;}
    public List<User> findAll() throws DataAccessException {return repository.findAll();}
    public User findById(int postId) throws DataAccessException { return repository.findById(postId);}
    public Result<User> add(User user) throws DataAccessException {
        Result<User> result = validate(user);
        if(!result.isSuccess()){
            return result;
        }
        if(user.getUserId() != 0){
            result.addMessage("User id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }
        user = repository.add(user);
        result.setPayload(user);
        return result;
    }
    public Result<User> update(User user) throws DataAccessException {
        Result<User> result = validate(user);
        if(!result.isSuccess()){
            return result;
        }
        if(user.getUserId()<=0){
            result.addMessage("User id must be set for `update` ooepration", ResultType.INVALID);
            return result;
        }
        if(!repository.update(user)){
            String msg = String.format("User ID: %s, not found", user.getUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }
    public boolean deleteById(int userId) throws DataAccessException {return repository.deleteById(userId);}
    private Result<User> validate(User user){
        Result<User> result = new Result<>();

        if(user==null){
            result.addMessage("User cannot be null", ResultType.INVALID);
            return result;
        }
        if(Validations.isNullOrBlank(user.getName())){
            result.addMessage("User name is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(user.getPhoneNumber())){
            result.addMessage("User phone number is required", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(user.getEmail())){
            result.addMessage("User email is required", ResultType.INVALID);
        }
       return result;
    }
}
