package dao;

import models.ToDo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoImpl implements ToDoDao{
    /* what is a DAO
    * data access object
    * It isolates the persistence layer, the DAO, from the application logic
    * generally, each model has a DAO
    *
    * JDBC - java database connectivity
    * - Allows java to connect to a database
    * - JDBC is modular, doesn't just connect to one specific database,
    *   it can connect depending on the database driver
    *
    * What is needed to connect to a db using JDBC?
    *  - database url
    *  - db username
    *  - db password
    *  - specific driver for your dbms
    *
    * Important classes and interfaces using JDBC
    *  - DriverManager (class)
    *       class for managing JDBC drivers
    *       DriverManager.getConnection() will generate our connect object
    *  - Connection (interface): active connection w/ our db
    *  - Statement (interface): object that represents our SQL statement
    *       doesn't protect agaisnt SQL injection
    *       PreparedStatement (interface): protects against SQL injection
    *       CallableStatement (interface): used for stores procedures
    *  - ResultSet (interface): object that will have our results
    *
    * Common errors
    *   "No suitable driver found": url has a typo or didn't add a dependency
    * */

    /*jdbc:postgresql://[your endpoint here]/[the specific database you want to connec to*/
    //String url = "jdbc:postgresql://jwa-db.cxwdgujyiyjs.us-east-1.rds.amazonaws.com/tododatabase";
    //String url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/tododatabase";

    //String username = "postgres";
    //String username = System.getenv("RDS_USERNAME");

    //String password = "p4ssw0rd";
    //String password = System.getenv("RDS_PASSWORD");

    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(ToDoDaoImpl.class);

    public ToDoDaoImpl(){
        /*jdbc:postgresql://[your endpoint here]/[the specific database you want to connec to*/
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/tododatabase";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ToDoDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();

        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql we will be executing
            String sql = "SELECT * FROM todos;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the sql statment and return results set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                todos.add(new ToDo(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
            //in the try() we put the variable we want auto closed
            //conn.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e);
        }
        return todos;
    }

    @Override
    public ToDo getOneToDo(Integer toDoId) {
        ToDo toDo = null;

        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql we will be executing
            //we use ? to replace variable we want then supply them
            String sql = "SELECT * FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            //1 is the index parameter
            ps.setInt(1, toDoId);
            //if we're setting a string we use .setString()

            //execute the sql statment and return results set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                toDo = new ToDo(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
            //in the try() we put the variable we want auto closed
            //conn.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return toDo;
    }

    @Override
    public void createToDo(ToDo toDo) {
        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO todos VALUES (DEFAULT, ?, DEFAULT);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark, the parameter index is which question mark you want to assign
            ps.setString(1, toDo.getTask());

            //execute the update
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateToDo(Integer toDoId) {
        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE todos SET completed = TRUE WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark, the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteToDo(Integer toDoId) {
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "DELETE FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark, the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
