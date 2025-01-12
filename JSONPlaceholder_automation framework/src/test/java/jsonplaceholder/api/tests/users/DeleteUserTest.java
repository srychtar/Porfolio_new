package jsonplaceholder.api.tests.users;
import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.users.DeleteUserService;
import jsonplaceholder.api.services.users.GetUserService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("User")
public class DeleteUserTest extends TestBase {

    Users receivedUser;
    Users user = new Users();
    Users deletedUser = new Users();
    Integer userId = 1;
    Users expectedUser = new Users();
    DeleteUserService userService = new DeleteUserService();

    @Test
    @TestId("5.1")
    public void shouldFindAndDeleteUserTest() {

        user.setId(userId);
        receivedUser = new GetUserService().setPath(user.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info("<pre>" + userService.gson.toJson(receivedUser) + "</pre>");
        deletedUser = userService.setPath(receivedUser.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(deletedUser).describedAs("User was not deleted by API").usingRecursiveComparison().isEqualTo(expectedUser);
        receivedUser = new GetUserService().setPath(user.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
    }

}

