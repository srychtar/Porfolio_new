package jsonplaceholder.api.services.albums;

import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PostAlbumService extends GenericModel<PostAlbumService, albums> {


    @Override
    protected Type getDataType() {
        return albums.class;
    }


    @Override
    public PostAlbumService executeRequestForSingle() {
        currentUri = getAlbumsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification()).body(model)
                .when().post(currentUri);
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_CREATED;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return (status == 0) ? HttpStatus.SC_BAD_REQUEST : status;
    }


}
