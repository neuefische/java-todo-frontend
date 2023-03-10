import React from "react";
import Statusboard from "../Statusboard/Statusboard";
import useAuth from "../../hooks/useAuth";
import InputBox from "../InputBox/InputBox";
import useTodo from "../../hooks/useTodo";


export default function TodoBoard(){
    const currentUser = useAuth(true)
    const {handleAddButton, handleSaveChange, handleAdvanceButtonClick, todoList} =useTodo()

    return (
        <>
            { currentUser?
                <>
                    <main className={"todoboard"}>
                        <Statusboard board-title={"Open"} todoList={todoList.filter(t=> t.status==="OPEN")}
                                     handleAdvanceButtonClick={handleAdvanceButtonClick}
                                     handleSaveChange={handleSaveChange}/>
                        <Statusboard board-title={"In Progress"} todoList={todoList.filter(t => t.status==="IN_PROGRESS")}
                                     handleAdvanceButtonClick={handleAdvanceButtonClick}
                                     handleSaveChange={handleSaveChange}/>
                        <Statusboard board-title={"Done"} todoList={todoList.filter(t => t.status==="DONE")}
                                     handleAdvanceButtonClick={handleAdvanceButtonClick}
                                 handleSaveChange={handleSaveChange}/>
                    </main>
                    <InputBox handleAddButton={handleAddButton}/>
                </>:
                null }
    </>
    )
}