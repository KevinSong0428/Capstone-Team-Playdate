package learn.lostandfound.data;

import learn.lostandfound.models.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;



public class PostJdbcTemplateRepositoryDouble implements PostRepository{
//    (int id, int animalId, int userId, String url, String description,
//    LocalDateTime dateTime, int locationId, String gender, int size, boolean found)
    private ArrayList<Post> posts = new ArrayList<>(List.of(
        new Post(1, 1, 1, "fakeurl.png", "Found this cat in a garage, seems lost.", LocalDateTime.parse("2023-04-25T09:15:00"), 2, "female", 10, true),
        new Post(2, 2, 2, "fakeurl.png", "Coco went missing during our walk in the neighborhood.", LocalDateTime.parse("2023-04-30T16:45:00"), 3, "male", 20, false),
        new Post(3, 1, 1, "fakeurl.png", "Spotted this dog wandering alone by the riverside.", LocalDateTime.parse("2023-05-02T08:00:00"), 4, "female", 25, true)
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
