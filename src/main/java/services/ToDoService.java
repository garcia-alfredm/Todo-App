package services;

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;

import java.util.List;

// service layer where we implement business logic
// if we had limitations on the length of the todo task, the logic would be done here

public class ToDoService {
    ToDoDao toDoDao;

    //use constructor to pass a mock object
    public ToDoService(ToDoDao toDoDao){
        this.toDoDao = toDoDao;
    }

    public ToDoService(){
        this.toDoDao = new ToDoDaoImpl();
    }

    public List<ToDo> getAllToDos(){
        return toDoDao.getAllToDos();
    }

    public ToDo getOneToDo(Integer id){
        return toDoDao.getOneToDo(id);
    }

    //business logic
    public Boolean createToDo(ToDo toDo){
        if(toDo.getTask().length() > 20){
            return false;
        }
        toDoDao.createToDo(toDo);
        return true;
    }

    public void completeToDo(Integer toDoId){
        toDoDao.updateToDo(toDoId);
    }

    public void deleteTodo(Integer toDoId){
        toDoDao.deleteToDo(toDoId);

    }
}
