package jsonplaceholder.api.tests.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.users.PostUserService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.UserBaseData;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("User")
public class PostUserTest extends TestBase {

    PostUserService userService = new PostUserService();
    Users createdUser;
    UserBaseData userData = new UserBaseData();
    Users user = userData.createUser();
    List<Users> usersList = new ArrayList<>();
    Users user1 = userData.createUser();
    Users user2 = userData.createUser();
    Users receivedUsersInfo;

    @Test
    @TestId("2.1")
    public void shouldCreateUserTest() {

        test.info("<pre>" + userService.gson.toJson(user) + "</pre>");
        createdUser = userService.setBody(user).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(createdUser).describedAs("user uncorrected created").usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(user);
    }

    @Test
    @TestId("2.2")
    public void shouldCreateMultiplyUserTest(){

        usersList.add(user1);
        usersList.add(user2);
        test.info("<pre>" + userService.gson.toJson(usersList) + "</pre>");
        receivedUsersInfo = userService.setBodyAsList(usersList).executeRequestForMultiple().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(receivedUsersInfo)
                .describedAs("The response should contain the correct number of users")
                .isNotNull();
    }

    @Test
    @TestId("3.1")
    public void shouldNotCreateEmptyUserTest() {

        Users emptyUser = new Users();

        test.info("<pre>" + userService.gson.toJson(emptyUser) + "</pre>");
        createdUser = userService.setBody(emptyUser).executeRequestForSingle().validateFailureRequest().getResponseModel();
        test.info(userService.getDetails());
    }

    @Test
    @TestId("3.2")
    public void shouldNotCreateLargeUserTest() {

        user.setId(11);
        user.setName(userData.LongTextGenerator(10000));
        test.info("<pre>" + userService.gson.toJson(user) + "</pre>");
        createdUser = userService.setBody(user).setExpectedNegativeStatusCode(HttpStatus.SC_REQUEST_TOO_LONG).executeRequestForSingle()
                .validateFailureRequest().getResponseModel();
        test.info(userService.getDetails());
    }
}
