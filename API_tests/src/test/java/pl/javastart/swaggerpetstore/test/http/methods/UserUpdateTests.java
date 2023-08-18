package pl.javastart.swaggerpetstore.test.http.methods;
import org.testng.annotations.Test;
import POJO.User;
import static io.restassured.RestAssured.given;

public class UserUpdateTests extends TestBase{

    @Test
    //Sprawdzenie czy znajduje użytkownika, aktualizacja danych, sprawdznie czy poprawnie zmieniono dane
    //metoda put i get
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {

        User user = new User();
        user.setId(500);
        user.setUsername("AK");
        user.setFirstName("Alicja");
        user.setLastName("Kowalska");
        user.setEmail("alicja@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(999);


        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().log().all().statusCode(200);


        user.setFirstName("Patrycja");
        user.setLastName("Malinowska");

        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .body(user)
                .when().put("user/{username}")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().log().all().statusCode(200);


    }

}