import "./TodoList.css";
import {Todo} from "../model/Todo";
import TodoCard from "./TodoCard";

export default function TodoList(props: { todos: Todo[] }) {


    return (
        <div>

            <div
                className={"cards"}>{props.todos.filter(({status}) => status === "OPEN").map((todo: Todo) =>
                <div className={"card"}>{TodoCard({todo})}</div>)}
            </div>
            <div
                className={"cards"}>{props.todos.filter(({status}) => status === "DOING").map((todo: Todo) =>
                <div className={"card"}>{TodoCard({todo})}</div>)}
            </div>
            <div
                className={"cards"}>{props.todos.filter(({status}) => status === "DONE").map((todo: Todo) =>
                <div className={"card"}>{TodoCard({todo})}</div>)}
            </div>


        </div>

    )
}