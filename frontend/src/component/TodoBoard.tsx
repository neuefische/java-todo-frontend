import React from "react";
import {TodoModel} from "../model/TodoModel";
import Statusboard from "./Statusboard";

type boardProps = {
    todoList: TodoModel[]
    handleAdvanceButtonClick(todoAdvance: TodoModel): void
    handleSaveChange(newStatus: string, newDesc: string, id: string): void

}

export default function TodoBoard(props: boardProps){

    return (
        <main className={"todoboard"}>
            <Statusboard board-title={"Open"} todoList={props.todoList.filter(t=> t.status==="OPEN")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}
                         handleSaveChange={props.handleSaveChange}/>
            <Statusboard board-title={"In Progress"} todoList={props.todoList.filter(t => t.status==="IN_PROGRESS")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}
                         handleSaveChange={props.handleSaveChange}/>
            <Statusboard board-title={"Done"} todoList={props.todoList.filter(t => t.status==="DONE")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}
                         handleSaveChange={props.handleSaveChange}/>
        </main>
    )
}