package pl.javastart.swaggerpetstore.test.http.methods;

import org.testng.annotations.Test;
import POJO.Category;
import POJO.Pet;
import POJO.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class PetDeleteTests extends TestBase {

    @Test
    // Dodanie zwierzaka, sprawdzenie czy istnieje, usunięcie i sprawdzenie czy na pewno
    // nie zostaje znaleziony, metoda post, get, delete
    public void givenExistingPetWhenDeletePetIsNotFoundTest(){

        Category category =new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag= new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet= new Pet();
        pet.setId(445);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("https://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet)
                .contentType("application/json")
                .when().post("pet")
                .then().log().all().statusCode(200);

        given().log().all().body(pet)
                .contentType("application/json")
                .pathParam("petId", pet.getId())
                .when().get("pet/{petId}")
                .then().log().all().statusCode(200);


        given().log().all()
                .contentType("application/json")
                .pathParam("petId", pet.getId())
                .when().delete("pet/{petId}")
                .then().log().all().statusCode(200);


        given().log().all().body(pet)
                .contentType("application/json")
                .pathParam("petId", pet.getId())
                .when().get("pet/{petId}")
                .then().log().all().statusCode(404);


    }


}
