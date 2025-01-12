package jsonplaceholder.api.tests.todos;
import jsonplaceholder.api.models.todos;
import jsonplaceholder.api.services.todos.PostTodoService;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import jsonplaceholder.api.data.TodosBaseData;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import jsonplaceholder.api.tests.TestBase;

@Listeners(TestBase.class)
@RequestType("POST")
@ResourceName("Todo")
public class PostTodosTest extends TestBase {

    todos actualTask;
    TodosBaseData todosData = new TodosBaseData();
    todos task = todosData.createTodo();

    PostTodoService todoService = new PostTodoService();


    @Test
    @TestId("2.1")
    public void shouldCreateTaskTest() {
        test.info("<pre>" + todoService.gson.toJson(task) + "</pre>");
        actualTask = todoService.setBody(task).executeRequestForSingle().validateSuccessRequest().getResponseModel();
        test.info(todoService.getDetails());
        Assertions.assertThat(actualTask).describedAs("task correct created").usingRecursiveComparison().isNotEqualTo(task);

    }

    @Test
    @TestId("3.1")
    public void shouldNotCreateEmptyTaskTest() {

        todos emptyTodo = new todos();

        test.info("<pre>" + todoService.gson.toJson(emptyTodo) + "</pre>");
        actualTask = todoService.setBody(emptyTodo).executeRequestForSingle().validateFailureRequest().getResponseModel();
        test.info(todoService.getDetails());

    }


}
 /* Ponieważ jest to fikcyjne API to przy typowej asercji sprawdzającej czy zasób faktycznie się utworzył wyskoczy nam błąd.
Podczas tworzenia zasobu dostajemy infrormację że zasób został utworzony ale finalnie nie tworzy nam się i baza danych
zostaje taka sama.
Dlatego asercję: Assertions.assertThat(actualTask).describedAs("task incorrect created").usingRecursiveComparison().isEqualTo(task);
zmieniono na Assertions.assertThat(actualTask).describedAs("task correct created").usingRecursiveComparison().isNotEqualTo(task);
*/

/* W tym przypadku tzw. sprzątanie nie jest potrzebne bo nic nie utworzyliśmy. W prawdziwym przypadku nie obyło by się bez:
    @AfterMethod
      public void cleanUpAfterTest(){
        todos clean = given().body(actualTask).spec(RequestBuilder.getRequestSpecification())
                .when().delete("todos")
                .then().statusCode(HttpStatus.SC_OK).extract().as(todos.class);
    }
 */

/* tak wyglądałby kod bez użycia ROP i samo- walidujących się asercji
 given().body(task).spec(RequestBuilder.getRequestSpecification()).when().post("posts")
 .then().statusCode(HttpStatus.SC_CREATED).extract().as(todos.class);

  assertEquals(actual_task1.getId(), task1.getId(), "uncorrected id");
  assertEquals(actual_task1.getUserId(), task1.getUserId(), "uncorrected UserId");

 */