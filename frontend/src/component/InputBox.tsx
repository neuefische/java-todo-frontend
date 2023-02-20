import React, {ChangeEvent, useState} from "react";
import "./InputBox.css"

type addProps ={
    handleAddButton(title: string): void
}

export default function InputBox(props: addProps){

    const [inputField, setInput] = useState<string>("")
    function handleAddButton() {
        props.handleAddButton(inputField)
        setInput("") // dies könnte man an eine erfolgreiche Response des Request koppeln
    }
    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setInput(event.target.value)
    }

    return (
        <section className={"inputField"}>
            <input className={"inputText"} value={inputField} onChange={handleInputChange} type="text" />
            <button onClick={handleAddButton}>Add ToDo</button>
        </section>


    )
}