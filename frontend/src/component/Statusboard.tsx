import React from "react";
import {TodoModel} from "../model/TodoModel";
import Todo from "../model/Todo";
import "./Todoboard.css"
type statusboard ={
    "board-title": string,
    "todoList": TodoModel[]
    handleAdvanceButtonClick(todoAdvance: TodoModel): void
    handleSaveChange(newStatus: string, newDesc: string, id: string): void

}
export default function Statusboard(props: statusboard) {
    return (
        <section className={"statusboard"}>
            <h2>
                {props["board-title"]}
            </h2>
            <div>
            {props.todoList.length<1?
                <h2>Empty</h2>:
                props.todoList.map(t =><Todo todo={t}
                                             handleAdvanceButtonClick={props.handleAdvanceButtonClick}
                                             handleSaveChange={props.handleSaveChange}/>)}
            </div>
        </section>
    )

}