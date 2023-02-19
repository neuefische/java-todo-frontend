import React, {ChangeEvent, useState} from "react";
import {TodoModel} from "./TodoModel"
import "./Todo.css"

type TodoProps = {
    "todo": TodoModel
    handleAdvanceButtonClick(todoAdvance: TodoModel): void
    handleSaveChange(newStatus: string, newDesc: string, id: string): void
}
export default function Todo (props: TodoProps){
    console.log(props.todo.status)
    const [editMode, setEdit] = useState<boolean>(false)
    const [editDesc, setDesc] =useState<string>(props.todo.description)
    const [editStatus, setStatus] = useState<string>(props.todo.status)

    function handleAdvanceButtonClick(){
        props.handleAdvanceButtonClick(props.todo)
    }
    function handleEditButtonClick() {
        setEdit(true)
    }
    function handleCancelButton(){
        setEdit(false)
    }
    function handleDescChange(event: ChangeEvent<HTMLInputElement>) {
        setDesc(event.target.value)
    }
    function handleStatusChange(event: ChangeEvent<HTMLSelectElement>) {
        setStatus(event.target.value)
    }
    function handleSaveChange() {
        props.handleSaveChange(editStatus, editDesc, props.todo.id)
        setEdit(false)
    }
return (
    <>
    {!editMode?
    <section className={"todo"}>
        <h1 className={"description"}>
            {props.todo.description}
        </h1>
        <p className={"buttons"}>
            <button className={"singlebutton"} onClick={handleEditButtonClick}>Edit</button>
            <button className={"singlebutton"} onClick={handleAdvanceButtonClick}>{props.todo.status==="DONE"? "Delete": "Advance"}</button>
        </p>
    </section> :
        <section className={"todo"}>
            <div className={"editfields"}>
                <label htmlFor={"eddesc"}>Description: </label>
                <input className={"editdescription"} id={"eddesc"} defaultValue={props.todo.description}
                       type={"text"} onChange={handleDescChange}/>

                <label htmlFor={"statusdrpdwn"}>Set Status: </label>
                <select name={"editstatus"} className={"statusdropdown"} id={"statusdrpdwn"}
                        defaultValue={props.todo.status} onChange={handleStatusChange}>
                    <option value={"OPEN"}>Open</option>
                    <option value={"IN_PROGRESS"}>In Progress</option>
                    <option value={"DONE"}>Done</option>
                </select>
            </div>
            <p className={"editbuttons"}>
                <button className={"singlebutton"} onClick={handleSaveChange}>Save Changes</button>
                <button className={"singlebutton"} onClick={handleCancelButton}>Cancel</button>
            </p>
        </section>
    }
    </>
)
}