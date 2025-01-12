package jsonplaceholder.api.services.comments;
import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostCommentService extends GenericModel<PostCommentService, comments> {


    @Override
    protected Type getDataType() {
        return comments.class;
    }



    @Override
    public PostCommentService executeRequestForSingle() {
        currentUri = getCommentsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().post(currentUri);
        return this;
    }

    public PostCommentService executeRequestForMultiple() {
        currentUri = getCommentsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(modelList)
                .when().post(currentUri);
        return this;
    }


    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return 0;
    }
}
