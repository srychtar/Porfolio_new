package jsonplaceholder.api.services.photos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jsonplaceholder.api.models.photos;
import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPhotoService extends GenericModel<GetPhotoService, photos> {
    @Override
    protected Type getDataType() {
        return photos.class;
    }

    @Override
    public GetPhotoService executeRequestForSingle() {
        currentUri = getPhotosPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetPhotoService executeRequestForAll() {
        currentUri = getPhotosPath();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public List<photos> getResponseUserModelList() {
        Type listType = new TypeToken<List<photos>>() {}.getType();
        return new Gson().fromJson(response.getBody().asString(), listType);
    }
    public GetPhotoService executeRequestForKeyValue(String key, Integer value) {
        currentUri = getPhotosKeyValuePath();
        response = given().queryParam(key,value).spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri); //Query params: Parametry w URL po znaku zapytania ?, używane głównie w GET.
        currentUri = "?" + key + "=" + value;
        return this;
    }

    @Override
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    protected int getExpectedNegativeStatusCode() {
        return HttpStatus.SC_NOT_FOUND;
    }
}
