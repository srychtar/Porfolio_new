package jsonplaceholder.api.services.comments;

import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteCommentService extends GenericModel <DeleteCommentService, comments> {


    @Override
    protected Type getDataType() {
        return comments.class;
    }

    @Override
    public DeleteCommentService executeRequestForSingle() {
        currentUri = getCommentsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().delete(currentUri);
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return 0;
    }


}
