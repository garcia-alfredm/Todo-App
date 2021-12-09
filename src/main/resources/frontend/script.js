/* 
<div class="todo-item" id="1">
    <div class="todo-info">
        <span class="todo-task">Sweep the Floor</span>
        <span class="todo-status green">Complete</span>
    </div>
    <div class="todo-btns">
        <button class="btn btn-primary" id="complete-btn-1">Complete</button>
        <button class="btn btn-danger" id="delete-btn-1">Delete</button>
    </div>
</div>
 */

let domain = "";

window.addEventListener("load", () => {
    populateTodos();
})

async function populateTodos(){
    //fetch todos from backend
    let response = await fetch(`${domain}/todo`);
    let data = await response.json();   //will be an array

    //reverse order of appearance of elements
    //use id to sort
    data.sort((a,b) => {
        if(a.id > b.id)
            return -1;
        else return 1;
    })

    //clear container
    let todoContainer = document.getElementById("todo-container");
    todoContainer.innerHTML = "";
    //todoContainer.replaceChildren(); is same

    //loop thru each todo and create the dom elements
    data.forEach(todo => {
        let todoItemElem = document.createElement("div");
        todoItemElem.className = "todo-item";
        todoItemElem.id = todo.id;

        todoItemElem.innerHTML = `
        <div class="todo-info">
            <span class="todo-task">${todo.task}</span>
            ${todo.completed ? '<span class="todo-status green">Complete</span>' : '<span class="todo-status red">Incomplete</span>'}
        </div>
        <div class="todo-btns">
            ${todo.completed ? '' :`<button class="btn btn-primary" id="complete-btn-${todo.id}" onclick="completeTodo(event)">Complete</button>`}
            <button class="btn btn-danger" id="delete-btn-${todo.id}" onclick="deleteTodo(event)">Delete</button>
        </div>`

        //append to container
        todoContainer.appendChild(todoItemElem);
    });
}

async function createTodo(e){
    e.preventDefault();

    //get the value from task input
    let taskInputElem = document.getElementById("task-input");
    let task = taskInputElem.value;

    //send post req with task in the body
    await fetch(`${domain}/todo`, {
        method: "POST",
        body: JSON.stringify({
            task: task
        })
    })

    taskInputElem.value = "";
    populateTodos();
}

async function completeTodo(e){
    console.log(e.target.id);

    //get id of todo to complete
    let id = e.target.id.slice("complete-btn-".length, e.target.id.length);
    
    //send req. to complete todo
    await fetch(`${domain}/todo/${id}`, {
        method: "PATCH"
    })

    populateTodos();
}

async function deleteTodo(e){

    //get id of todo to complete
    let id = e.target.id.slice("delete-btn-".length, e.target.id.length);
    
    //send req. to complete todo
    await fetch(`${domain}/todo/${id}`, {
        method: "DELETE"
    })

    populateTodos();
}