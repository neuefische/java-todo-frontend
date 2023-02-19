import React, {useEffect, useState} from 'react';
import './App.css';
import TodoHeader from "./component/TodoHeader";
import TodoBoard from "./component/TodoBoard";
import {TodoModel} from "./model/TodoModel";
import axios from "axios";
import InputBox from "./component/InputBox";


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
            axios.put("/api/todo/"+todoAdvance.id, todoToPut)
                .then()
        } else if (todoAdvance.status==="IN_PROGRESS"){
            todoToPut = {description: todoAdvance.description, status: "DONE", id: todoAdvance.id}
            axios.put("/api/todo/"+todoAdvance.id, todoToPut)
                .then()
        } else {
            axios.delete("/api/todo/"+todoAdvance.id).then()
        }
        setFetch(true)
    }
    function handleAddButton(title: string) {
        axios.post("/api/todo", {description: title, status: "OPEN"}).then()
        setFetch(true)
    }
    function handleSaveChange(newStatus:string, newDesc: string, id: string){
        axios.put("/api/todo/"+id, {description: newDesc, status: newStatus, id: id}).then()
        setFetch(true)
    }

  return (
    <div className="App">
      <header className="App-header">
        <TodoHeader/>
      </header>
        <main>
        <TodoBoard todoList={todoList} handleAdvanceButtonClick={handleAdvanceButtonClick}
                   handleSaveChange={handleSaveChange}/>
            <InputBox handleAddButton={handleAddButton}/>
        </main>

    </div>
  )
}

export default App;
