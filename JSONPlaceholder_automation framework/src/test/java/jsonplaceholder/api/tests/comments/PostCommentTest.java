package jsonplaceholder.api.tests.comments;

import jsonplaceholder.api.models.comments;

import jsonplaceholder.api.services.comments.PostCommentService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;

import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.CommentsBaseData;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;

import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("Comment")
public class PostCommentTest extends TestBase {


    PostCommentService commentService = new PostCommentService();
    List<comments> commentsList = new ArrayList<>();
    CommentsBaseData commentData = new CommentsBaseData();
    comments comment1 = commentData.createComments();
    comments comment2 = commentData.createComments();
    comments receivedCommentInfo;


    @Test
    @TestId("2.1a")
    public void shouldCreateCommentTest() {

        test.info("<pre>" + commentService.gson.toJson(comment1) + "</pre>");
        receivedCommentInfo = commentService.setBody(comment1).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());
        Assertions.assertThat(receivedCommentInfo).describedAs("comment uncorrected created").usingRecursiveComparison().isNotEqualTo(comment1);

    }
    @Test
    @TestId("2.1b")
    public void shouldCreateMultiplyCommentsTest(){

        commentsList.add(comment1);
        commentsList.add(comment2);
        test.info("<pre>" + commentService.gson.toJson(commentsList) + "</pre>");
        receivedCommentInfo = commentService.setBodyAsList(commentsList).executeRequestForMultiple().validateSuccessRequest().getResponseModel();
        test.info(commentService.getDetails());

    }


}
