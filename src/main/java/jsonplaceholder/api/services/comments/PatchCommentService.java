package jsonplaceholder.api.services.comments;

import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PatchCommentService extends GenericModel<PatchCommentService, comments> {


    @Override
    protected Type getDataType() {
        return comments.class;
    }

    @Override
    public PatchCommentService executeRequestForSingle() {
        currentUri = getCommentsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().patch(currentUri);
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
