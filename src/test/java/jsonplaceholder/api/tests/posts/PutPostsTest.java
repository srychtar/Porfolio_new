package jsonplaceholder.api.tests.posts;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.posts.PutPostService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.data.PostsBaseData;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("Post")
public class PutPostsTest extends TestBase {

    posts actualPost;
    PostsBaseData postsData = new PostsBaseData();
    posts post = postsData.createPost();
    PutPostService postService = new PutPostService();

    @Test
    @TestId("4.2")
    public void shouldChangeExistingPostTest() {
        post.setId(2);
        test.info("<pre>" + postService.gson.toJson(post) + "</pre>");
        actualPost = postService.setBody(post).setPath(post.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(postService.getDetails());
        Assertions.assertThat(actualPost).describedAs("post uncorrected updated").usingRecursiveComparison().isEqualTo(post);
    }

}
