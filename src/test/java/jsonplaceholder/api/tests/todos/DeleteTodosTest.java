package jsonplaceholder.api.tests.todos;


import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.todos.DeleteTodoService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("DELETE")
@ResourceName("Todo")
public class DeleteTodosTest extends TestBase {

    todos task = new todos();
    todos deletedTask;
    DeleteTodoService todoService = new DeleteTodoService();
    todos expectedTask = new todos();


    @Test
    @TestId("5.1")
    public void shouldDeleteExistingTaskTest() {

        task.setUserId(1);
        deletedTask = todoService.setPath(task.getId()).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(todoService.getDetails());
        Assertions.assertThat(deletedTask).describedAs("Task was not deleted by API").
                usingRecursiveComparison().isEqualTo(expectedTask);
    }

}
