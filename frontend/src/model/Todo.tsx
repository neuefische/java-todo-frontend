import React from "react";
import {TodoModel} from "./TodoModel"
import "./Todo.css"

type TodoProps = {
    "todo": TodoModel
    "editMode": boolean
    handleAdvanceButtonClick(todoAdvance: TodoModel): void
}
export default function Todo (props: TodoProps){
    console.log(props.todo.status)

    function handleAdvanceButtonClick(){
        props.handleAdvanceButtonClick(props.todo)
    }
return (
    <>
    {props.editMode===false?
    <section className={"todo"}>
        <h1 className={"description"}>
            {props.todo.description}
        </h1>
        <p className={"buttons"}>
            <button className={"singlebutton"}>Edit</button>
            <button className={"singlebutton"} onClick={handleAdvanceButtonClick}>{props.todo.status==="DONE"? "Delete": "Advance"}</button>
        </p>
    </section>:
        <section className={"todoeditmode"}>
            <input className={"editdescription"} value={props.todo.description} type={"text"} />
            <select name={"editstatus"} className={"statusdropdown"}>
                <option value={"Open"}>Open</option>
                <option value={"In Progress"}>In Progress</option>
                <option value={"Done"}>Done</option>
            </select>
        </section>
    }
    </>
)
}