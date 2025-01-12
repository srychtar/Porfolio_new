package jsonplaceholder.api.tests.posts;


import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.posts.DeletePostService;
import jsonplaceholder.api.services.posts.GetPostService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("Post")
public class DeletePostsTest extends TestBase {

    posts post = new posts();
    posts receivedPost;
    posts deletedPost;
    DeletePostService postService = new DeletePostService();
    posts expectedPost = new posts();

    @Test
    @TestId("5.1")
    public void shouldFindAndDeletePostTest() {

        post.setId(1);
        receivedPost = new GetPostService().setPath(post.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info("<pre>" + postService.gson.toJson(receivedPost) + "</pre>");
        deletedPost = postService.setPath(receivedPost.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(postService.getDetails());

        Assertions.assertThat(deletedPost).describedAs("Post was not deleted by API").usingRecursiveComparison().isEqualTo(expectedPost);
    }

}
