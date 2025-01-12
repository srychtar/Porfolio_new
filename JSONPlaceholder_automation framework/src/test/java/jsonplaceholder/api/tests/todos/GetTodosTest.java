package jsonplaceholder.api.tests.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.todos.GetTodoService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;
import org.assertj.core.api.Assertions;

import java.util.List;

@Listeners(TestBase.class)
@RequestType("GET")
@ResourceName("Todo")
public class GetTodosTest extends TestBase {


    GetTodoService todoService = new GetTodoService();
    todos task1 = new todos();
    todos receivedTask;
    List<todos> receivedTasks;


    @Test
    @TestId("1.1")
    public void shouldFindExistingTaskTest() {

        task1.setId(1);
        task1.setCompleted(false);
        task1.setTitle("delectus aut autem");
        task1.setUserId(1);
        test.info("<pre>" + todoService.gson.toJson(task1) + "</pre>");
        receivedTask = todoService.setPath(task1.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(todoService.getDetails());
        Assertions.assertThat(receivedTask).describedAs("Task ID is different").usingRecursiveComparison().isEqualTo(task1);
        test.info("Expected Task ID: " + task1.getId());
        test.info("Actual Task ID: " + receivedTask.getId());
    }

    @Test
    @TestId("1.2")
    public void shouldFetchAllTaskTest() {

        receivedTasks= todoService.executeRequestForAll().validateSuccessRequest().getResponseUserModelList();
        test.info(todoService.getDetails());
        Assertions.assertThat(receivedTasks).describedAs("Users list should no be empty").isNotEmpty();
        test.info("Fetched " + receivedTasks.size() + " task.");
    }



}
