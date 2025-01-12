package jsonplaceholder.api.services.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;
import java.lang.reflect.Type;
import static io.restassured.RestAssured.given;


public class DeleteTodoService extends GenericModel<DeleteTodoService, todos> {

    @Override
    protected Type getDataType() {
        return todos.class;
    }

    @Override
    public DeleteTodoService executeRequestForSingle(){
        currentUri = getTodosPath() + resourceId;
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
