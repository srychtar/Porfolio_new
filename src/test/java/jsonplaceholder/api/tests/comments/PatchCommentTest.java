package jsonplaceholder.api.tests.comments;

import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.comments.GetCommentService;
import jsonplaceholder.api.services.comments.PatchCommentService;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("Comment")
public class PatchCommentTest extends TestBase {

    comments comment = new comments();
    comments actualComment;
    PatchCommentService commentService = new PatchCommentService();


    @Test
    public void shouldUpdateCommentTest() {
        comment.setId(1);
        actualComment = new GetCommentService().setPath(comment.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        actualComment.setName("New Name");
        test.info("<pre>" + commentService.gson.toJson(actualComment) + "</pre>");
        comment= commentService.setBody(actualComment).setPath(actualComment.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());
        Assertions.assertThat(actualComment).describedAs("post as modify as expected").usingRecursiveComparison().isEqualTo(comment);
        test.info("Expected Title: " + actualComment.getName());
        test.info("Actual Title: " + comment.getName());
    }

}