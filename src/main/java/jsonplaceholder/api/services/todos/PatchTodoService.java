package jsonplaceholder.api.services.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PatchTodoService extends GenericModel<PatchTodoService, todos> {

    @Override
    protected Type getDataType() {
        return todos.class;
    }


    public PatchTodoService executeRequestForSingle() {
        currentUri = getTodosPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().patch(currentUri);
        return this;
    }

    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return 0;
    }


}
