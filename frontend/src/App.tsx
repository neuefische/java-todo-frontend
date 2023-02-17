import React, {useEffect, useState} from 'react';
import './App.css';
import TodoHeader from "./component/TodoHeader";
import TodoBoard from "./component/TodoBoard";
import {TodoModel} from "./model/TodoModel";
import axios from "axios";


function App() {

    const [fetch, setFetch] = useState<boolean>(false)
    const [todoList, setTodoList] = useState<TodoModel[]>([])
    useEffect(fetchTodoList, [fetch])

    function fetchTodoList() {
        axios.get("/api/todo")
            .then(r=>setTodoList(r.data))
            .catch(e => console.log(e))
        setFetch(false);
    }
    console.log(todoList)

    function handleAdvanceButtonClick(todoAdvance: TodoModel) {
        let todoToPut: TodoModel;
        if (todoAdvance.status=== "OPEN") {
            todoToPut  = {description: todoAdvance.description, id: todoAdvance.id, status: "IN_PROGRESS"}
        } else if (todoAdvance.status==="IN_PROGRESS"){
            todoToPut = {description: todoAdvance.description, status: "DONE", id: todoAdvance.id}
        } else todoToPut = {description: todoAdvance.description, status: "DONE", id: todoAdvance.id}
        console.log(todoToPut)
        axios.put("/api/todo/"+todoAdvance.id, todoToPut)
            .then()
        setFetch(true)
        }

  return (
    <div className="App">
      <header className="App-header">
        <TodoHeader/>
      </header>
        <TodoBoard todoList={todoList} handleAdvanceButtonClick={handleAdvanceButtonClick}/>

    </div>
  )
}

export default App;
