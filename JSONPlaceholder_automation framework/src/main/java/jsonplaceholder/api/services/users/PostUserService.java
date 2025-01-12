package jsonplaceholder.api.services.users;
import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;


public class PostUserService extends GenericModel<PostUserService, Users> {

    @Override
    protected Type getDataType() {
        return Users.class;
    }

    @Override
    public PostUserService executeRequestForSingle() {
        currentUri = getUsersPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().post(currentUri);
        return this;
    }

    public PostUserService executeRequestForMultiple() {
        currentUri = getUsersPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(modelList)
                .when().post(currentUri);
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    protected int getExpectedNegativeStatusCode(){
        return (status == 0) ? HttpStatus.SC_BAD_REQUEST : status;
    }

}
