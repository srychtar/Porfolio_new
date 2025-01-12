package jsonplaceholder.api.tests.users;

import jsonplaceholder.api.models.users.Users;
import jsonplaceholder.api.models.posts;
import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.models.albums;
import jsonplaceholder.api.services.users.GetUserService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.ResourceType;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;
import java.util.List;
import java.util.Objects;


@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("User")
public class GetUserTest extends TestBase {

    Users user= new Users();
    Users receivedUser;
    List<Users> receivedUsers;
    List<posts> receivedPosts;
    List<todos> receivedTodos;
    List<albums> receivedAlbums;
    Integer userId = 1;
    GetUserService userService = new GetUserService();

    @Test
    @TestId("1.1")
    public void shouldFetchUserTest() {

        user.setId(userId);
        receivedUser = userService.setPath(user.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(userService.getDetails());
        Assertions.assertThat(receivedUser.getId()).describedAs("User ID is different").isEqualTo(user.getId());
        test.info("Expected User ID: " + user.getId());
        test.info("Actual User ID: " + receivedUser.getId());
    }

    @Test
    @TestId("1.2")
    public void shouldFetchAllUsersTest() {

        receivedUsers = userService.executeRequestForAll().validateSuccessRequest().getResponseUserModelList();
        test.info(userService.getDetails());
        Assertions.assertThat(receivedUsers).describedAs("Users list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedUsers.size() + " users.");
    }

    @Test
    @TestId("1.3a")
    public void shouldFetchNestedPostsForUsersTest() {

        receivedPosts = userService.executeRequestForNested(ResourceType.POSTS,userId).validateSuccessRequest().getResponseModelList(posts.class);
        test.info(userService.getDetails());
        Assertions.assertThat(receivedPosts).describedAs("posts list should not be empty").isNotEmpty();
        Assertions.assertThat(receivedPosts).allMatch(posts-> Objects.equals(posts.getUserId(), userId), "All posts should have the userId equal to 1");
        test.info("Fetched " + receivedPosts.size() + " posts for user with ID: " + userId);
    }

    @Test
    @TestId("1.3b")
    public void shouldFetchNestedTodosForUsersTest() {

        receivedTodos = userService.executeRequestForNested(ResourceType.TODOS,userId).validateSuccessRequest().getResponseModelList(todos.class);
        test.info(userService.getDetails());
        Assertions.assertThat(receivedTodos).describedAs("posts list should not be empty").isNotEmpty();
        Assertions.assertThat(receivedTodos).allMatch(posts-> Objects.equals(posts.getUserId(), userId), "All todos should have the userId equal to 1");
        test.info("Fetched " + receivedTodos.size() + " todos for user with ID: " + userId);
    }

    @Test
    @TestId("1.3c")
    public void shouldFetchNestedAlbumsForUsersTest() {

        receivedAlbums = userService.executeRequestForNested(ResourceType.ALBUMS,userId).validateSuccessRequest().getResponseModelList(albums.class);
        test.info(userService.getDetails());
        Assertions.assertThat(receivedAlbums).describedAs("posts list should not be empty").isNotEmpty();
        Assertions.assertThat(receivedAlbums).allMatch(posts-> Objects.equals(posts.getUserId(), userId), "All albums should have the userId equal to 1");
        test.info("Fetched " + receivedAlbums.size() + " albums for user with ID: " + userId);
    }

    @Test
    @TestId("1.4")
    public void shouldFetchKeyValueUsersTest() {

        receivedUsers = userService.executeRequestForKeyValue("id", userId ).validateSuccessRequest().getResponseModelList(Users.class);
        test.info(userService.getDetails());
        Assertions.assertThat(receivedUsers).describedAs("users list should not be empty").isNotEmpty();
        test.info("Fetched " + receivedUsers.size() + " user with ID: " + userId);
        Assertions.assertThat(receivedUsers).allMatch(Users->Users.getId() == 1);
    }


}






