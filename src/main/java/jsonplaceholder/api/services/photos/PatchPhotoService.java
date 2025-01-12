package jsonplaceholder.api.services.photos;

import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PatchPhotoService extends GenericModel<PatchPhotoService, photos> {
    @Override
    protected Type getDataType() {
        return photos.class;
    }

    @Override
    public PatchPhotoService executeRequestForSingle() {
        currentUri = getPhotosPath() + resourceId;
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
