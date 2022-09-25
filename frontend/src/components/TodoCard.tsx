import "./TodoCard.css";
import {Todo} from "../model/Todo";
import axios from "axios";


export default function TodoCard(props: { todo: Todo }) {
    const deleteTodo = () => {
        axios.delete("/api/todo/" + props.todo.id).then(() => {
            window.location.reload()
        })
    }
    const details: string = "/details/?id=" + props.todo.id
    const edit: string = "/edit/?id=" + props.todo.id

    const advance = () => {
        if (props.todo.status != "DONE") {
            return <button onClick={advanceTodo}>advance</button>
        }
    }

    const advanceTodo = () => {
        let newStatus: string = "";
        if (props.todo.status == "OPEN") {
            newStatus = "DOING"
        } else {
            newStatus = "DONE"
        }
        axios.put("api/todo/" + props.todo.id, {
            id: props.todo.id,
            description: props.todo.description,
            status: newStatus
        })
            .then(() => {
                window.location.reload()
            })
    }

    return (
        <div className={"todo-card"}>
            <h3>{props.todo.description}</h3>
            <p>{props.todo.status}</p>
            <button><a href={details}>details</a></button>
            <button><a href={edit}>edit</a></button>
            {advance()}
            <button onClick={deleteTodo}>delete</button>
        </div>
    )
}


