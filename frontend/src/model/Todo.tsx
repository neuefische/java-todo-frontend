import React from "react";
import {TodoModel} from "./TodoModel"
import "./Todo.css"

type TodoProps = {
    "todo": TodoModel
    handleAdvanceButtonClick(todoAdvance: TodoModel): void
}
export default function Todo (props: TodoProps){
    console.log(props.todo.status)

    function handleAdvanceButtonClick(){
        props.handleAdvanceButtonClick(props.todo)
    }
return (
    <section className={"todo"}>
        <h1 className={"description"}>
            {props.todo.description}
        </h1>
        <p className={"buttons"}>
            <button className={"singlebutton"}>Edit</button>
            <button className={"singlebutton"} onClick={handleAdvanceButtonClick}>{props.todo.status==="DONE"? "Delete": "Advance"}</button>
        </p>

    </section>
)
}