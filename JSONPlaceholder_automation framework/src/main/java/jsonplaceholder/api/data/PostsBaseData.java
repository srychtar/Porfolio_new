package jsonplaceholder.api.data;
import jsonplaceholder.api.models.posts;

public class PostsBaseData extends DataGeneratorBase {

    public posts createPost(){

        posts post = new posts();
        post.setUserId(faker().number().numberBetween(1,99));
        post.setId(faker().number().numberBetween(1,99));
        post.setTitle(faker().name().title());
        post.setBody(faker().food().ingredient());
        return post;
    }
}
