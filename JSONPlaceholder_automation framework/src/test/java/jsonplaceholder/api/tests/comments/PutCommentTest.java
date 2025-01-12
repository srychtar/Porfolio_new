package jsonplaceholder.api.tests.comments;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.CommentsBaseData;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.comments.PutCommentsService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("Comment")
public class PutCommentTest extends TestBase {

    comments actualComment;
    CommentsBaseData commentsData = new CommentsBaseData();
    comments comment = commentsData.createComments();
    PutCommentsService commentService = new PutCommentsService();

    @Test
    @TestId("4.2")
    public void shouldChangeExistingCommentTest() {
        comment.setId(2);
        test.info("<pre>" + commentService.gson.toJson(comment) + "</pre>");
        actualComment = commentService.setBody(comment).setPath(comment.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());
        Assertions.assertThat(actualComment).describedAs("comment uncorrected updated").usingRecursiveComparison().isEqualTo(comment);
    }
}
