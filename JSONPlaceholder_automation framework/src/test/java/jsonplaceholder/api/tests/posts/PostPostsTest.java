package jsonplaceholder.api.tests.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.posts.PostPostService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.data.PostsBaseData;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("Post")
public class PostPostsTest extends TestBase {

    PostsBaseData postsData = new PostsBaseData();
    posts post= postsData.createPost();
    posts actualPost;
    PostPostService postService = new PostPostService();

    @Test
    public void shouldCreatePostTest() {

        test.info("<pre>" + postService.gson.toJson(post) + "</pre>");
        actualPost = postService.setBody(post).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(postService.getDetails());
        Assertions.assertThat(actualPost).describedAs("post correct created").usingRecursiveComparison().isNotEqualTo(post);

    }

}
