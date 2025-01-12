package jsonplaceholder.api.services.albums;

import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;


public class PutAlbumService extends GenericModel<PutAlbumService, albums> {

    @Override
    protected Type getDataType() {
        return albums.class;
    }

    @Override
    public PutAlbumService executeRequestForSingle() {
        currentUri = getAlbumsPath() + resourceId;
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
        return 0;
    }
}
