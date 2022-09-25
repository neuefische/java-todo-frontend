import {Todo} from "../model/Todo";

export default function TodoDetails(props: { todo: Todo }) {
    return (
        <div className={"card"}>
            <h3>Description: {props.todo.description}</h3>
            <p>ID: {props.todo.id}</p>
            <p>Status: {props.todo.status}</p>
            <button><a href="/">back</a></button>
        </div>
    )
}