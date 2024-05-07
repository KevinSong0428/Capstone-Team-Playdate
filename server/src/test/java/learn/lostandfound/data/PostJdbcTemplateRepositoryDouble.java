package learn.lostandfound.data;

import learn.lostandfound.models.Animal;
import learn.lostandfound.models.Post;
import learn.lostandfound.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;



public class PostJdbcTemplateRepositoryDouble implements PostRepository{
    Animal one = new Animal(1, "", "DESC", "ANIMAL", "BREED");
    Animal two = new Animal(2, "", "DESC", "ANIMAL", "BREED");
    User userOne = new User();
    User userTwo = new User();
    private ArrayList<Post> posts = new ArrayList<>(List.of(
        new Post(1, one, userOne, "fakeurl.png", "Found this cat in a garage, seems lost.", LocalDateTime.parse("2023-04-25T09:15:00"), 2, "female", 10, true),
        new Post(2, two, userTwo, "fakeurl.png", "Coco went missing during our walk in the neighborhood.", LocalDateTime.parse("2023-04-30T16:45:00"), 3, "male", 20, false),
        new Post(3, one, userOne, "fakeurl.png", "Spotted this dog wandering alone by the riverside.", LocalDateTime.parse("2023-05-02T08:00:00"), 4, "female", 25, true)
    ));


    @Override
    public List<Post> findAll() throws DataAccessException {
        return posts;
    }

    @Override
    public Post findByPostId(int id) throws DataAccessException {
        return posts.stream()
                .filter(i->i.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post add(Post post) throws DataAccessException {
        return post;
    }

    @Override
    public boolean update(Post post) throws DataAccessException {
        return post.getId()>0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return id!=999999;
    }
}
