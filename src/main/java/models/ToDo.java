package models;

/* Our models will be containers for our data in the project
* models won't have any functionality. models will be an object representation of our data
* mimiced the table structure
*
* Generally (not always) out models are going to look very similar to our database table
* */
public class ToDo {

    private Integer id;
    private String task;
    private Boolean completed;
    // noarg constructor
    public ToDo() {
    }

    public ToDo(String task) {
        this.task = task;
    }

    //allarg constructor
    public ToDo(Integer id, String task, Boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    /*all models will have getters and setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //generate -> toString then check member values
    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                '}';
    }
}
