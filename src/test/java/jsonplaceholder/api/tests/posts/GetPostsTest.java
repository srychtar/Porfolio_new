package jsonplaceholder.api.tests.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.posts.GetPostService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.ResourceType;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

import java.util.List;
import java.util.Objects;

@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("Post")

public class GetPostsTest extends TestBase {

   posts post = new posts();
   posts receivedPost;
   List<posts> receivedPosts;
   List<comments> receivedComments;
   GetPostService postService = new GetPostService();
   Integer postId = 2;

    @Test
    @TestId("1.1")
    public void shouldFetchPostTest() {
        post.setId(1);
        receivedPost= postService.setPath(post.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(postService.getDetails());
        Assertions.assertThat(receivedPost.getId()).describedAs("User ID is different").isEqualTo(post.getId());
        test.info("Expected Post ID: " + post.getId());
        test.info("Actual User Post ID: " + receivedPost.getId());
    }

    @Test
    @TestId("1.2")
    public void shouldFetchAllPostsTest() {

        receivedPosts = postService.executeRequestForAll().validateSuccessRequest().getResponseUserModelList();
        test.info(postService.getDetails());
        Assertions.assertThat(receivedPosts).describedAs("Posts list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedPosts.size() + " posts.");
    }

    @Test
    @TestId("1.3")
    public void shouldFetchNestedPostsForUsersTest() {

        receivedComments = postService.executeRequestForNested(ResourceType.COMMENTS,postId).validateSuccessRequest().getResponseModelList(comments.class);
        test.info(postService.getDetails());
        Assertions.assertThat(receivedComments).describedAs("posts list should not be empty").isNotEmpty();
        Assertions.assertThat(receivedComments).allMatch(comments-> Objects.equals(comments.getPostId(), postId), "All posts should have the userId equal to 1");
        test.info("Fetched " + receivedComments.size() + " comments for post with ID: " + postId);
    }

    @Test
    @TestId("1.4")
    public void shouldFetchKeyValuePostTest() {

        receivedPosts = postService.executeRequestForKeyValue("userId", postId).validateSuccessRequest().getResponseModelList(posts.class);
        test.info(postService.getDetails());
        Assertions.assertThat(receivedPosts).describedAs("posts list should not be empty").isNotEmpty();
        test.info("Fetched " + receivedPosts.size() + "  post with userId: " + postId);
        Assertions.assertThat(receivedPosts).allMatch(posts->posts.getUserId() == 2);
    }

    @Test
    @TestId("1.1")
    public void shouldNoFetchPostTest() {

        post.setId(200);
        receivedPost = postService.setPath(post.getId()).executeRequestForSingle().validateFailureRequest().getResponseModel();
        test.info(postService.getDetails());
        Assertions.assertThat(receivedPost.getId()).describedAs("Founded not exist user").isNotEqualTo(post.getId());
        test.info("Actual Post ID: " + receivedPost.getId());
    }

}
