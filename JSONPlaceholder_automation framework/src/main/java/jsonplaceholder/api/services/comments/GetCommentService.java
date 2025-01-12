package jsonplaceholder.api.services.comments;

import jsonplaceholder.api.models.comments;
import jsonplaceholder.api.services.GenericModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCommentService extends GenericModel<GetCommentService, comments> {



    @Override
    protected Type getDataType() {
        return comments.class;
    }

    @Override
    public GetCommentService executeRequestForSingle() {
        currentUri = getCommentsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetCommentService executeRequestForAll() {

        currentUri = getCommentsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetCommentService executeRequestForKeyValue(String key, Integer value) {
        currentUri = getCommentsKeyValuePath() + key + "=" + value;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }
    public List<comments> getResponseUserModelList() {
        Type listType = new TypeToken<List<comments>>() {}.getType();
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
