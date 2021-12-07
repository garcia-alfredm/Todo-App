import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDao;
import dao.ToDoDaoImpl;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
import models.ToDo;
import services.ToDoService;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //ToDoService toDoService = new ToDoService(new ToDoDaoImpl());

        //toDoService.createToDo(new ToDo(0, "Is this more", false));
        //System.out.println(toDoService.getOneToDo(3));
        //toDoService.deleteTodo(3);

        //System.out.println(toDoService.getAllToDos());

        //ToDo mytodolist = new ToDo(0, "sweep", false);
        //ToDoDao toDoDao = new ToDoDaoImpl();    //upcasting, if we want to use mongo of mysql would still work
        //toDoDao.createToDo(new ToDo(0, "mop the floor", false));
        //toDoDao.updateToDo(3);
        //System.out.println(toDoDao.getOneToDo(3));
        //toDoDao.deleteToDo(4);
        //toDoDao.updateToDo(1);
        //System.out.println(toDoDao.getAllToDos());

        // Create Javalin Server
            Javalin app = Javalin.create(config -> {
                config.addStaticFiles("/frontend", Location.CLASSPATH);
            }).start(7000);

        //app.ToDoService toDoService = new ToDoService(new ToDoDaoImpl());
        new FrontController(app);


    }
/*
    public static void getAllToDos(Context context) throws JsonProcessingException {
        context.contentType("application/json"); // sending back json

        List<ToDo> toDoList = toDoService.getAllToDos(); //get all todos from db
        String jsonString = new ObjectMapper().writeValueAsString(toDoList); // used jackson to convert list obj to json

        context.result(jsonString);
    }*/
}
