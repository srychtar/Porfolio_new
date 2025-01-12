package jsonplaceholder.api.services.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;
import java.lang.reflect.Type;
import static io.restassured.RestAssured.given;

public class DeletePostService extends GenericModel<DeletePostService, posts> {



    @Override
    protected Type getDataType() {
        return posts.class;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return 0;
    }


    @Override
    public DeletePostService executeRequestForSingle() {
        currentUri = getPostsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().delete(currentUri);
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }


}
