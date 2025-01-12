package jsonplaceholder.api.services.posts;

import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.annotations.ResourceType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;
import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPostService extends GenericModel<GetPostService, posts> {



    @Override
    protected Type getDataType() {
        return posts.class;
    }


    @Override
    public GetPostService executeRequestForSingle() {
        currentUri = getPostsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetPostService executeRequestForAll() {

        currentUri = getPostsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetPostService executeRequestForNested(ResourceType resourceType, Integer postId) {
        currentUri = getPostsPath() + postId + "/" + resourceType.getResourceName();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetPostService executeRequestForKeyValue(String key, Integer value) {
        currentUri = getPostsKeyValuePath() + key + "=" + value;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }
    public List<posts> getResponseUserModelList() {
        Type listType = new TypeToken<List<posts>>() {}.getType();
        return new Gson().fromJson(response.getBody().asString(), listType);
    }
    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }
    @Override
    protected int getExpectedNegativeStatusCode() {
        return HttpStatus.SC_NOT_FOUND;
    }

}
