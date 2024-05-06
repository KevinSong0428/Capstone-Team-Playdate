package learn.lostandfound.data;

import learn.lostandfound.models.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll() throws DataAccessException;
    Post findByPostId(int id) throws DataAccessException;
    Post add(Post post) throws DataAccessException;
    boolean update(Post post) throws  DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
}
