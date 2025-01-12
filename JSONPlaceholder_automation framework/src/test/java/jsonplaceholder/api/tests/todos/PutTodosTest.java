package jsonplaceholder.api.tests.todos;

import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.todos.PutTodoService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("PUT")
@ResourceName("Todo")

public class PutTodosTest extends TestBase {

    todos actualTask;
    todos task = new todos();
    PutTodoService todoService = new PutTodoService();

    @Test
    @TestId("4.2")
    public void shouldChangeExistingTaskTest() {

        task.setUserId(5);
        task.setId(1);
        task.setTitle("All is changed");
        task.setCompleted(true);

        test.info("<pre>" + todoService.gson.toJson(task) + "</pre>");
        actualTask = todoService.setBody(task).setPath(task.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(todoService.getDetails());
        Assertions.assertThat(actualTask).describedAs("task correct changed").usingRecursiveComparison().isEqualTo(task);

    }
}

// PUT jest używane do zastępowania całego zasobu nowymi danymi
//PATCH jest przeznaczone do aktualizacji tylko określonych części zasobu.