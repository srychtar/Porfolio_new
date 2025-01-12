package jsonplaceholder.api.services.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.GenericModel;
import jsonplaceholder.api.annotations.ResourceType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpStatus;
import jsonplaceholder.api.specification.RequestBuilder;

import java.lang.reflect.Type;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUserService extends GenericModel<GetUserService, Users> {

    @Override //zwraca typ danych reprezentujący model użytkownika
    protected Type getDataType() {
        return Users.class;
    }

    @Override //żądanie HTTP GET dla pojedynczego użytkownika
    public GetUserService executeRequestForSingle() {
        currentUri = getUsersPath() + resourceId;
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    //żądanie HTTP GET, dla wszystkich użytkowników
    public GetUserService executeRequestForAll() {
        currentUri = getUsersPath();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    //żądanie HTTP GET, dla zagnieżdżonego zasobu użytkownika
    public GetUserService executeRequestForNested(ResourceType resourceType, Integer userId) {
        currentUri = getUsersPath() + userId + "/" + resourceType.getResourceName();
        response = given().spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        return this;
    }

    //żądanie HTTP GET z parametrami zapytania, aby pobrać użytkowników według klucza i wartości
    public GetUserService executeRequestForKeyValue(String key, Integer value) {
        currentUri = getUsersKeyValuePath();
        response = given().queryParam(key,value).spec(RequestBuilder.buildRequestSpecification())
                .when().get(currentUri);
        currentUri += "?" + key + "=" + value;
        return this;
    }

    // przekształca odpowiedź JSON na listę obiektów Users.
    public List<Users> getResponseUserModelList() {
        Type listType = new TypeToken<List<Users>>() {}.getType();
        return new Gson().fromJson(response.getBody().asString(), listType);
    }

    @Override //zwraca oczekiwany kod statusowy dla poprawnego żądania
    protected int getExpectedStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override //zwraca oczekiwany kod statusowy dla niepoprawnego żądania
    protected int getExpectedNegativeStatusCode() {
        return (status == 0) ? HttpStatus.SC_NOT_FOUND : status;
    }

}


//Query params: Parametry w URL po znaku zapytania ?, używane głównie w GET.


// Typy generyczne są usuwane na etapie kompilacji, co oznacza, że TypeToken<List<DataModel>> w klasie generycznej nie może odwoływać się bezpośrednio do konkretnego typu jak Users w czasie wykonywania.

    // Request params: Ogólny termin dla różnych typów parametrów w żądaniach.
    //Query params: Parametry w URL po znaku zapytania ?, używane głównie w GET.
    //    Path params: Parametry w ścieżce URL, używane do określenia konkretnego zasobu.
     //   Form params: Parametry przesyłane w ciele żądania, zazwyczaj w POST.