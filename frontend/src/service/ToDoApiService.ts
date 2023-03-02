import axios from "axios";
import {TodoModel} from "../model/TodoModel";
import {TodoModelPost} from "../model/TodoModelPost";

export default function ToDoApiService() {
    const apiURI = "/api/todo"
    function get(){
        return axios.get(apiURI)
            .then(r => r.data)
            .catch(e => console.error(e))
    }
    function put(id: string, todoToPut: TodoModel) {
        return axios.put(apiURI + "/" + id, todoToPut)
            .then(r => r.data)
            .catch(e => console.error(e))
    }
    function apiDelete(id: string) {
        return axios.delete(apiURI + "/" + id)
            .then(r => r.data)
            .catch(e => console.error(e))
    }
    function post(todoToPost: TodoModelPost) {
        return axios.post(apiURI, todoToPost)
            .then(r => r.data)
            .catch(e => console.error(e))
    }
    return {get, put, apiDelete, post}
}