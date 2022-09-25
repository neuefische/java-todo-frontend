import axios from "axios";
import {useEffect, useState} from "react";

export default function GetTodos() {
    const [todos, setTodos] = useState([])


    useEffect(() => {
        axios.get("/api/todo/")
            .then((response) => {
                return response.data
            })
            .then((data) => {
                setTodos(data)
            })
            .catch((error) => {
                console.error(error)
            })
    }, [])


    return (todos)
}

