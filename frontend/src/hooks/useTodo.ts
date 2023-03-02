import {useEffect, useState} from "react";
import {TodoModel} from "../model/TodoModel";
import ToDoApiService from "../service/ToDoApiService";

export default function useTodo(){
    const [todoList, setTodoList] = useState<TodoModel[]>([])
    const {get, put, apiDelete, post} = ToDoApiService()
    useEffect(fetchTodoList, [])

    function fetchTodoList() {
        get().then(d =>setTodoList(d))
    }

    function handleAdvanceButtonClick(todoAdvance: TodoModel) {
        let todoToPut: TodoModel;
        if (todoAdvance.status=== "OPEN") {
            todoToPut  = {description: todoAdvance.description, id: todoAdvance.id, status: "IN_PROGRESS"}
            put(todoAdvance.id, todoToPut)
                .then(d => setTodoList([...todoList.filter((todo) => todo.id!==d.id), d]))
        } else if (todoAdvance.status==="IN_PROGRESS"){
            todoToPut = {description: todoAdvance.description, status: "DONE", id: todoAdvance.id}
            put(todoAdvance.id, todoToPut)
                .then(d => setTodoList([...todoList.filter((todo) => todo.id!==d.id), d]))
        } else {
            apiDelete(todoAdvance.id)
                .then(d => setTodoList([...todoList.filter((todo) => todo.id!==d.id)]))
        }
    }
    function handleAddButton(title: string) {
        post({description: title, status: "OPEN"})
            .then(d => setTodoList([...todoList, d]))
    }
    function handleSaveChange(newStatus:string, newDesc: string, id: string){
        put(id, {description: newDesc, status: newStatus, id: id})
                .then(d => setTodoList([...todoList.filter((todo) => todo.id!==d.id), d]))
    }
    return {handleAddButton, handleAdvanceButtonClick, handleSaveChange, todoList}
}