import axios from "axios";
import {useEffect, useState} from "react";
import {Todo} from "../model/Todo";

export default function GetTodoById(props: { id: string }) {
    const [todo, setTodo] = useState({id: "", description: "", status: ""})


    useEffect(() => {
        axios.get("/api/todo/" + props.id)
            .then((response) => {
                return response.data
            })
            .then((data) => {
                setTodo(data)
            })
            .catch((error) => {
                console.error(error)
            })
    }, [])


    return (todo)
}

