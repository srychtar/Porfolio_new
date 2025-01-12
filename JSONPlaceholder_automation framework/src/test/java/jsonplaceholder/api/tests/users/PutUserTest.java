package jsonplaceholder.api.tests.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.users.GetUserService;
import jsonplaceholder.api.services.users.PutUserService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("User")
public class PutUserTest extends TestBase {

    Users receivedUser;
    Users user = new Users();
    Users updatedUser;
    PutUserService userService = new PutUserService();

    @Test
    @TestId("4.2")
    public void shouldChangeExistingUserTest() {
        user.setId(1);
        receivedUser = new GetUserService().setPath(user.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        receivedUser.setName("Sylwia Bajak");
        test.info("<pre>" + userService.gson.toJson(receivedUser) + "</pre>");
        updatedUser = userService.setBody(receivedUser).setPath(receivedUser.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(receivedUser).describedAs("user correct updated").usingRecursiveComparison().isEqualTo(updatedUser);
        test.info("Expected User Name: " + receivedUser.getName());
        test.info("Actual User Name: " + updatedUser.getName());
    }



}
