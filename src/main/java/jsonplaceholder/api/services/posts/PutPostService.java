package jsonplaceholder.api.services.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PutPostService extends GenericModel<PutPostService, posts> {



    @Override
    protected Type getDataType() {
        return posts.class;
    }


    @Override
    public PutPostService executeRequestForSingle() {
        currentUri = getPostsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().put(currentUri);
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
