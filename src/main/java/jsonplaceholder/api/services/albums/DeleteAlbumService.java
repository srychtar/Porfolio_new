package jsonplaceholder.api.services.albums;

import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteAlbumService extends GenericModel<DeleteAlbumService, albums> {


    @Override
    protected Type getDataType() {
        return albums.class;
    }

    @Override
    public DeleteAlbumService executeRequestForSingle() {
        currentUri = getAlbumsPath() + resourceId;
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
