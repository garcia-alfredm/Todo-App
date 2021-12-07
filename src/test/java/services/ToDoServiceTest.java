package services;

import dao.ToDoDao;
import models.ToDo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    ToDoDao toDoDao = Mockito.mock(ToDoDao.class);
    ToDoService toDoService;

    public ToDoServiceTest(){
        this.toDoService = new ToDoService(toDoDao);
    }

    @Test
    void getAllToDos() {
        //arrange
        List<ToDo> todos = new ArrayList<>();
        todos.add(new ToDo(1, "sweep", false));
        todos.add(new ToDo(2, "laundry", false));
        todos.add(new ToDo(3, "mop", false));
        List<ToDo> expectedValue = todos;
        Mockito.when(toDoDao.getAllToDos()).thenReturn(todos);

        //act
        List<ToDo> actualValue = toDoService.getAllToDos();

        //assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOneToDo() {
        //assign
        ToDo expectedResult = new ToDo(1, "sweep", false);
        Mockito.when(toDoDao.getOneToDo(expectedResult.getId())).thenReturn(expectedResult);

        //act
        ToDo actualResult = toDoService.getOneToDo(expectedResult.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createToDoGreaterThan20() {
        //assign
        ToDo todo = new ToDo(1, "This task is more than twenty char", false);
        boolean expectedResult = false;

        //act
        boolean actualResult = toDoService.createToDo(todo);

        //assert
        assertEquals(expectedResult, actualResult);

        //assertFalse(assertEquals);
    }

    @Test
    void createToDoLessThanEquals20() {
        //assign
        ToDo todo = new ToDo(1, "Is this less than???", false);
        //Boolean expectedResult = true;

        //act
        Boolean actualResult = toDoService.createToDo(todo);

        //assert
        //assertEquals(expectedResult, actualResult);
        assertTrue(actualResult);
    }

    @Test
    void completeToDo() {
        //arragne
        Integer toDoId = 1;

        //act
        toDoService.completeToDo(toDoId);

        //assert
        Mockito.verify(toDoDao, Mockito.times(1)).updateToDo(toDoId);
    }

    @Test
    void deleteTodo() {
        //arrange
        Integer toDoId = 1;

        //act
        toDoService.deleteTodo(toDoId);

        //assert
        Mockito.verify(toDoDao, Mockito.times(1)).deleteToDo(toDoId);

    }
}