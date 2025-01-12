package jsonplaceholder.api.tests.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.services.users.PatchUserService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;
@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("User")
public class PatchUserTest extends TestBase {

    Users updatedUser = new Users();
    Users user = new Users();
    PatchUserService userService = new PatchUserService();
    int userId = 1;

    @Test
    @TestId("4.1")
    public void shouldUpdateUserTest() {
        user.setId(userId);
        user.setName("Sylwia Bajak");
        test.info("<pre>" + userService.gson.toJson(user) + "</pre>");
        updatedUser = userService.setBody(user).setPath(user.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(user).describedAs("user as modify as expected").usingRecursiveComparison().isNotEqualTo(updatedUser);
        test.info("Expected User Name: " + user.getName());
        test.info("Actual User Name: " + updatedUser.getName());
    }
}
