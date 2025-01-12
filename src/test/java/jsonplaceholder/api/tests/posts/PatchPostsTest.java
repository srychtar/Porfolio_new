package jsonplaceholder.api.tests.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.posts.GetPostService;
import jsonplaceholder.api.services.posts.PatchPostService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("Post")
public class PatchPostsTest extends TestBase {

    posts post = new posts();
    posts actualPost;
    PatchPostService postService = new PatchPostService();
    int postId = 2;

    @Test
    public void shouldUpdatePostTest() {
        post.setId(postId);
        actualPost = new GetPostService().setPath(post.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        actualPost.setTitle("New Title");
        test.info("<pre>" + postService.gson.toJson(actualPost) + "</pre>");
        post = postService.setBody(actualPost).setPath(actualPost.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(postService.getDetails());
        Assertions.assertThat(actualPost).describedAs("post as modify as expected").usingRecursiveComparison().isEqualTo(post);
        test.info("Expected Title: " + actualPost.getTitle());
        test.info("Actual Title: " + post.getTitle());
    }
}
