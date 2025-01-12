package jsonplaceholder.api.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    public static RequestSpecification buildRequestSpecification() {
        return new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config()
                        .objectMapperConfig(ObjectMapperConfig
                                .objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON)))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("User-Agent", "MyTestFramework/1.0")
                .build();
    }

}
