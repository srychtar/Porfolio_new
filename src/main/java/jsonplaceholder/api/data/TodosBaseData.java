package jsonplaceholder.api.data;
import jsonplaceholder.api.models.todos;

public class TodosBaseData extends DataGeneratorBase {

    public todos createTodo(){

        todos task = new todos();
        task.setUserId(faker().number().numberBetween(1,99));
        task.setId(faker().number().numberBetween(1,99));
        task.setTitle(faker().job().keySkills());
        task.setCompleted(faker().bool().bool());
        return task;
    }
}
