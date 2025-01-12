package jsonplaceholder.api.services.albums;

import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PatchAlbumService extends GenericModel<PatchAlbumService, albums> {


    @Override
    protected Type getDataType() {
        return albums.class;
    }

    @Override
    public PatchAlbumService executeRequestForSingle() {
        currentUri = getAlbumsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().patch(currentUri);
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
