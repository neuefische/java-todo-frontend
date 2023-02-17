import React from "react";
import {TodoModel} from "../model/TodoModel";
import Statusboard from "./Statusboard";

type boardProps = {
    todoList: TodoModel[]
    handleAdvanceButtonClick(todoAdvance: TodoModel): void

}

export default function TodoBoard(props: boardProps){

    return (
        <main className={"todoboard"}>
            <Statusboard board-title={"Open"} todoList={props.todoList.filter(t=> t.status==="OPEN")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}/>
            <Statusboard board-title={"In progress"} todoList={props.todoList.filter(t => t.status==="IN_PROGRESS")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}/>
            <Statusboard board-title={"Done"} todoList={props.todoList.filter(t => t.status==="DONE")}
                         handleAdvanceButtonClick={props.handleAdvanceButtonClick}/>
        </main>
    )
}