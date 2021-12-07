package dao;

import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoDaoImplTest {

    ToDoDao toDoDao;

    ToDoDaoImplTest(){
        this.toDoDao = new ToDoDaoImpl(H2Util.url, H2Util.username,H2Util.password);
    }

    @BeforeEach
    void setUp() throws SQLException {
        H2Util.createTable();
    }

    @AfterEach
    void tearDown(){
        H2Util.dropTable();
    }

    @Test
    void getAllToDosIT() {
        //assign
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        List<ToDo> actualResult = toDoDao.getAllToDos();

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getOneToDoIT() {
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));


        ToDo actualResult = toDoDao.getOneToDo(2);

        assertEquals(expectedResult.get(1).toString(), actualResult.toString());
    }

    @Test
    void createToDoIT() {
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        Integer actualResult = toDoDao.getAllToDos().size();

        assertEquals(expectedResult.size(), actualResult);
    }

    @Test
    void updateToDoIT() {
        ToDo toDotoPass = new ToDo(1, "sweep", false);
        ToDo exptectedResult = new ToDo(1, "sweep", true);
        toDoDao.createToDo(toDotoPass);

        toDoDao.updateToDo(toDotoPass.getId());
        ToDo actualResults = toDoDao.getOneToDo(toDotoPass.getId());

        assertEquals(exptectedResult.toString(), actualResults.toString());

    }

    @Test
    void deleteToDoIT() {
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        toDoDao.deleteToDo(2);
        expectedResult.remove(1);

        List<ToDo> actualResult = toDoDao.getAllToDos();

        assertEquals(expectedResult.toString(), actualResult.toString());
        assertNull(toDoDao.getOneToDo(2));
    }
}