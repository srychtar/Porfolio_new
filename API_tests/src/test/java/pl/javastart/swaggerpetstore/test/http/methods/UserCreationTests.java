package pl.javastart.swaggerpetstore.test.http.methods;
import org.testng.annotations.Test;
import POJO.User;
import static io.restassured.RestAssured.given;

public class UserCreationTests extends TestBase{

    @Test
    //dodanie nowego użytkownika i sprawdzenie czy występuje, metoda post i get
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

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
                .body(user)
                .when().post("user")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().log().all().statusCode(200);



    }


}
