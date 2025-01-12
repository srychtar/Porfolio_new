package jsonplaceholder.api.services.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PostPostService extends GenericModel<PostPostService, posts> {


    @Override
    protected Type getDataType() {
        return posts.class;
    }

    @Override
    public PostPostService executeRequestForSingle() {
        currentUri = getPostsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
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
