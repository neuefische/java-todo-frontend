import React, {ChangeEvent, useState} from "react";
import {text} from "stream/consumers";

type addProps ={
    handleAddButton(title: string): void
}

export default function InputBox(props: addProps){

    const [inputField, setInput] = useState<string>("")
    function handleAddButton() {
        props.handleAddButton(inputField)
        setInput("")
    }
    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setInput(event.target.value)
    }

    return (
        <section className={"inputField"}>
            <input value={inputField} onChange={handleInputChange} type="text" />
            <button onClick={handleAddButton}>Add ToDo</button>
        </section>


    )
}