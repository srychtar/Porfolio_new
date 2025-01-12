package jsonplaceholder.api.services.photos;

import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;


import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class PostPhotoService extends GenericModel<PostPhotoService, photos> {
    @Override
    protected Type getDataType() {
        return photos.class;
    }

    @Override
    public PostPhotoService executeRequestForSingle() {
        currentUri = getPhotosPath();
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
