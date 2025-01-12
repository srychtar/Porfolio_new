package jsonplaceholder.api.services.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.GenericModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetTodoService extends GenericModel<GetTodoService, todos> {

    @Override
    protected Type getDataType() {
        return todos.class;
    }

    @Override
    public GetTodoService executeRequestForSingle() {
        currentUri = getTodosPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetTodoService executeRequestForAll() {
        currentUri = getTodosPath();
        response = given().log().all().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public List<todos> getResponseUserModelList() {
        Type listType = new TypeToken<List<todos>>() {}.getType();
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
