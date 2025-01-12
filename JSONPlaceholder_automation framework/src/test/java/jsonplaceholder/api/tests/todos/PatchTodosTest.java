package jsonplaceholder.api.tests.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.todos.PatchTodoService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("PATCH")
@ResourceName("Todo")
public class PatchTodosTest extends TestBase {


    todos actualTask;
    todos task = new todos();
    PatchTodoService todoService = new PatchTodoService();

    @Test
    @TestId("4.1")
    public void shouldChangePartOfExistingTaskTest() {

        task.setUserId(1);
        task.setId(1);
        task.setTitle("I changed title only");
        task.setCompleted(false);

        test.info("<pre>" + todoService.gson.toJson(task) + "</pre>");
        actualTask = todoService.setBody(task).setPath(task.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(todoService.getDetails());
        Assertions.assertThat(actualTask).describedAs("task correct changed").usingRecursiveComparison().isEqualTo(task);
        test.info("Attention- in the case it is correct that resource was not change");
    }

}
