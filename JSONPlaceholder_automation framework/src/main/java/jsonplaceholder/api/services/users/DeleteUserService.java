package jsonplaceholder.api.services.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.GenericModel;
import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;
import java.lang.reflect.Type;
import static io.restassured.RestAssured.given;

public class DeleteUserService extends GenericModel<DeleteUserService, Users> {

    @Override
    protected Type getDataType() {
        return Users.class;
    }

    @Override
    public DeleteUserService executeRequestForSingle() {
        currentUri = getUsersPath() + resourceId;
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
        return (status == 0) ? HttpStatus.SC_BAD_REQUEST : status;
    }

}


// sprawdza, czy status jest równy 0. Jeśli tak, zwraca HttpStatus.SC_BAD_REQUEST; w przeciwnym razie zwraca status.
/*W klasach generycznych w Javie nie można bezpośrednio używać typu generycznego do uzyskiwania informacji o typie w czasie wykonywania (runtime).
 Oznacza to, że coś takiego jak DataModel.class nie jest dostępne, ponieważ Java używa tzw. erasure (usuwanie typów generycznych) podczas kompilacji.




 */