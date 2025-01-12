package jsonplaceholder.api.tests.comments;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.comments.GetCommentService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

import java.util.List;

@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("Comment")
public class GetCommentTest extends TestBase {

    comments comment = new comments();
    comments receivedComment;
    List<comments> receivedComments;
    Integer commentId = 1;
    GetCommentService commentService = new GetCommentService();

    @Test
    @TestId("1.1")
    public void shouldFetchCommentTest() {

        comment.setId(commentId);
        receivedComment = commentService.setPath(comment.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());
        Assertions.assertThat(receivedComment.getId()).describedAs("User ID is different").isEqualTo(comment.getId());
        test.info("Expected Comment ID: " + comment.getId());
        test.info("Actual Comment ID: " + receivedComment.getId());
    }

    @Test
    @TestId("1.2")
    public void shouldFetchAllCommentsTest() {

        receivedComments = commentService.executeRequestForAll().validateSuccessRequest().getResponseUserModelList();
        test.info(commentService.getDetails());
        Assertions.assertThat(receivedComments).describedAs("Users list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedComments.size() + " users.");
    }

    @Test
    @TestId("1.4")
    public void shouldFetchKeyValueUsersTest() {
        String key = "id";
        receivedComments = commentService.executeRequestForKeyValue(key, commentId).validateSuccessRequest().getResponseModelList(comments.class);
        test.info(commentService.getDetails());
        Assertions.assertThat(receivedComments).describedAs("posts list should not be empty").isNotEmpty();
        test.info("Fetched " + receivedComments.size() + " comments with ID: " + commentId);
        Assertions.assertThat(receivedComments).allMatch(comments->comments.getId() == 1);
    }




}
