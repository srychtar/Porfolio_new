package jsonplaceholder.api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonplaceholder.api.config.EnvironmentSettings;
import org.assertj.core.api.Assertions;
import java.lang.reflect.Type;
import java.util.List;


public abstract class GenericModel<ServiceName, DataModel> implements EnvironmentSettings {

    //zmienne używane w klasach dziedziczących
    protected String currentUri;
    protected Response response;
    protected int status = 0;
    protected Integer resourceId;
    protected DataModel model;
    protected List<DataModel> modelList;

    //zmienna używana w klasach testowych
    public Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Metody abstrakcyjne używane w klasach typu service, które definiują operacje i zasady dla danej usługi
    protected abstract Type getDataType();
    protected abstract int getExpectedStatusCode();
    protected abstract int getExpectedNegativeStatusCode();
    public abstract ServiceName executeRequestForSingle();

    //getter używany w klasach testowych, przekształca odpowiedź z formatu JSON na pojedynczy obiekt
    public DataModel getResponseModel() {
       return response.as(getDataType());
    }

    //sprawdzenie czy kod statusowy odpowiedzi jest zgodny z oczekiwanym
    private ServiceName checkCodeStatus(int statusCode) {
        Assertions.assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        return (ServiceName) this;
    }
    // wywołanie powyższej metody z oczekiwanymi kodami
    public ServiceName validateSuccessRequest() {
        return checkCodeStatus(getExpectedStatusCode());
    }
    public ServiceName validateFailureRequest() {
        return checkCodeStatus(getExpectedNegativeStatusCode());
    }

    //setter używany w klasach testowych, pozwala na ustawienie identyfikatora zasobu, przekazywanego do klas typu service
    public ServiceName setPath(Integer resourceId){
        this.resourceId = resourceId;
        return (ServiceName) this;
    }

    //setter używany w klasach testowych pozwala na ustawienie ciała żadania, przekazywanego do klas typu service
    public ServiceName setBody(DataModel model){
        this.model = model;
        return (ServiceName) this;
    }

    public ServiceName setBodyAsList(List<DataModel> modelList) {
        this.modelList = modelList;
        return (ServiceName) this;
    }

    //setter używany w klasach testowych wtedy gdy potrzebny jest inny oczekiwany kod statusowy niż domyślnie ustawiony
    // w klasie typu service
    public ServiceName setExpectedNegativeStatusCode(int status){
        this.status = status;
        return (ServiceName) this;
    }

    //getter używany w klasach testowych do zwrócenia listy obiektów określonego typu
    public <anyObjectType> List<anyObjectType> getResponseModelList(Class <anyObjectType> classType) {
        Type listType = TypeToken.getParameterized(List.class, classType).getType();
        return new Gson().fromJson(response.getBody().asString(), listType);
    }

    //getter zwracający pełne URI
    private String getCurrentUri() {
        return RestAssured.baseURI + "/" + currentUri;
    }

    //getter używany w klasach testowych, zwracający szczegóły odpowiedzi
    public String getDetails() {
        return  "<b>URL:</b> " + getCurrentUri() + "<br>" +
                "<b>Response Status Code:</b> " + response.getStatusCode() + "<br>" +
                "<b>Content-Type:</b> " + response.getHeader("Content-Type") + "<br>" +
                "<b>Response Body:</b><br>" + "<pre>" + response.getBody().asString() + "</pre>";
    }

}


