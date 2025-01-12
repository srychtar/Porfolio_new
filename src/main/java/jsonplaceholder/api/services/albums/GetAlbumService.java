package jsonplaceholder.api.services.albums;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jsonplaceholder.api.annotations.ResourceType;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.specification.RequestBuilder;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAlbumService extends GenericModel<GetAlbumService, albums> {


    @Override
    protected Type getDataType() {
        return albums.class;
    }

    @Override
    public GetAlbumService executeRequestForSingle() {
        currentUri = getAlbumsPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetAlbumService executeRequestForAll() {
        currentUri = getAlbumsPath();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetAlbumService executeRequestForKeyValue(String key, Integer value) {
        currentUri = getAlbumsKeyValuePath() + key + "=" + value;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public GetAlbumService executeRequestForNested(ResourceType resourceType, Integer albumId) {
        currentUri = getAlbumsPath() + albumId + "/" + resourceType.getResourceName();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    public List<albums> getResponseAlbumModelList() {
        Type listType = new TypeToken<List<albums>>() {}.getType();
        return new Gson().fromJson(response.getBody().asString(), listType);
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
