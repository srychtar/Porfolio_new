package jsonplaceholder.api.tests.comments;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.comments.DeleteCommentService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("Comment")
public class DeleteCommentTest extends TestBase {

    DeleteCommentService commentService= new DeleteCommentService();
    comments comment = new comments();
    comments deletedComment = new comments();
    comments expectedComment = new comments();


    @Test
    @TestId("5.1")
    public void shouldFindAndDeleteCommentTest() {

        comment.setId(1);
        deletedComment = commentService.setPath(comment.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());

        Assertions.assertThat(deletedComment).describedAs("Post was not deleted by API").usingRecursiveComparison().isEqualTo(expectedComment);
    }

}




