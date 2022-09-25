import './App.css';
import TodoList from "./components/TodoList";
import GetTodos from "./components/GetTodos";
import {useState} from "react";
import axios from "axios";

import TodoEdit from "./components/TodoEdit";
import GetTodoById from "./components/GetTodoById";
import {Todo} from "./model/Todo";
import TodoDetails from "./components/TodoDetails";


function App() {
    const [data, setData] = useState("");
    const [add, setAdd] = useState("");
    const todos: Todo[] = GetTodos();


    const addTodo = () => {
        axios.post("/api/todo", {id: "", description: add, status: "OPEN"}).then(() => {
            window.location.reload()
        })
    }
    const getId = () => {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get("id")!
    }
    const id: string = getId()
    const todo: Todo = GetTodoById({id})
    return (
        <div>
            <h1>Todo App</h1>
            <input onChange={(event) => setData(event.target.value)} placeholder={'search todos'}/>
            {(() => {
                switch (window.location.pathname) {
                    case "/details/":
                        return <TodoDetails todo={todo}/>;
                    case "/edit/":
                        return <TodoEdit todo={todo}/>;
                    default:
                        return <TodoList todos={todos.filter(({description}) => description.includes(data))}/>;
                }
            })()}
            <input onChange={(event) => setAdd(event.target.value)} placeholder={'add todos'}/>
            <button onClick={addTodo}>Add</button>
        </div>
    );
}

export default App;
