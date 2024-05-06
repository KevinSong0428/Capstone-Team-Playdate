package learn.lostandfound.data;

import learn.lostandfound.models.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll() throws DataAccessException;
    Post findById(int id) throws DataAccessException;
    Post create(Post post) throws DataAccessException;
    Post update(Post post) throws  DataAccessException;
    Post deleteById(int id) throws DataAccessException;
}
