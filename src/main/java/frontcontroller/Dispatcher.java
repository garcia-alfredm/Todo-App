package frontcontroller;

import controllers.ToDoController;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import static io.javalin.apibuilder.ApiBuilder.*;

/*
* Where endoints will be routed to specific methods
* */
public class Dispatcher {
    public Dispatcher(Javalin app){

        //must have RESTful endpoints
        //2nd param is an implementation of the functional interface
        /*app.get("/todo", ToDoController::getAllTodos);
        app.get("/todo/{id}", ToDoController::getOneToDo);
        app.post("createTodo", ToDoController::createToDo);
        app.patch("updateToDo", ToDoController::updateToDo);
        app.delete("deleteToDo", ToDoController::deleteToDo);*/

        ToDoController toDoController = new ToDoController();

        app.routes(() -> {
            path("todo", () -> {
                //:: is the method reference operator
                get(ToDoController::getAllTodos); // /todo GET
                post(ToDoController::createToDo); // /todo POST
                path("{id}", () -> {
                    get(ToDoController::getOneToDo); // /todo/3 GET   get todo with todo id that is 3
                    patch(ToDoController::updateToDo); // /todo/3 PATCH
                    delete(ToDoController::deleteToDo); // /todo/3 DELETE
                });
            });

        });
    }
}
