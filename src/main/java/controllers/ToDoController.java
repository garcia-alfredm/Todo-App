package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpl;
import io.javalin.http.Context;
import models.ToDo;
import services.ToDoService;

import java.util.List;

/*
* controllers are where we implement http requests and response methods
* */
public class ToDoController {

    static ToDoService toDoService = new ToDoService();

    public ToDoController(){};

    public static void getAllTodos(Context context) throws JsonProcessingException {
        context.contentType("application/json"); // sending back json
        List<ToDo> toDoList = toDoService.getAllToDos(); //get all todos from db
        //get json string
        String jsonString = new ObjectMapper().writeValueAsString(toDoList); // used jackson to convert list obj to json
        context.result(jsonString);
    }

    public static void getOneToDo(Context context) throws JsonProcessingException {
        context.contentType("application/json");
        Integer todoId = Integer.parseInt(context.queryParam("id"));
        ToDo todo = toDoService.getOneToDo(todoId);
        context.result(new ObjectMapper().writeValueAsString(todo));
        //context.result(new ObjectMapper().writeValueAsString(toDoService.getOneToDo(todoId);)); // is valid
    }

    public static void createToDo(Context context){
        ToDo todo = context.bodyAsClass(ToDo.class);
        if(toDoService.createToDo(todo)){
            context.result("todo created");
        }else{
            context.result("task was too long, make a shorter task");
        }
    }

    public static void updateToDo(Context context){
        Integer todoId = Integer.parseInt(context.queryParam("id"));
        toDoService.completeToDo(todoId);
        context.result("Todo with id:" + todoId + " has been completed");
    }

    public static void deleteToDo(Context context){
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.deleteTodo(todoId);
        context.result("Todo with id: " + " has been deleted");
    }
}
