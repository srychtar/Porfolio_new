package jsonplaceholder.api.services.users;

import jsonplaceholder.api.specification.RequestBuilder;
import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PutUserService extends GenericModel<PutUserService, Users> {

    @Override
    protected Type getDataType() {
        return Users.class;
    }

    @Override
    public PutUserService executeRequestForSingle() {
        currentUri = getUsersPath() + resourceId;
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
        return (status == 0) ? HttpStatus.SC_BAD_REQUEST : status;
    }

}
