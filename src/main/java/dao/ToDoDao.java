package dao;

import models.ToDo;

import java.util.List;

/* defining our CRUD operations: create read update delete
* this is our persistence layer -what access database to save data
* */
public interface ToDoDao {
    List<ToDo> getAllToDos();

    ToDo getOneToDo(Integer toDoId);

    void createToDo(ToDo toDo);

    void updateToDo(Integer toDoId);

    void deleteToDo(Integer toDoId);


}
