package jsonplaceholder.api.services.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PostTodoService extends GenericModel<PostTodoService, todos> {


    @Override
    protected Type getDataType() {
        return todos.class;
    }


    @Override
    public PostTodoService executeRequestForSingle() {
        currentUri = getTodosPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().post(currentUri);
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_CREATED;
    }


    protected int getExpectedNegativeStatusCode() {
        return (status == 0) ? HttpStatus.SC_BAD_REQUEST : status;
    }
}
