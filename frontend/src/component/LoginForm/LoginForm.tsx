import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import useAuth from "../../hooks/useAuth";

export default function LoginForm() {
    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")
    const navigate = useNavigate()


    function handleUsername(event: ChangeEvent<HTMLInputElement>) {
        setUsername(event.target.value)
    }
    function handlePassword(event: ChangeEvent<HTMLInputElement>) {
        setPassword(event.target.value)
    }
    function handleSubmit (event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        axios.post("api/users/login", {}, {
            headers: {
                Authorization: `Basic ${window.btoa(`${username}:${password}`)}`}})
            .then(() => {
                const redirect = window.sessionStorage.getItem("signInRedirect") || "/";
                 window.sessionStorage.removeItem("signInRedirect");
                 navigate(redirect)})
            .catch(e => console.error(e))
    }

    return(
        <>
            <form onSubmit={handleSubmit}>
                <label>
                    Username
                    <input type="text" value={username} onChange={handleUsername}/>
                </label>
                <label>
                    Password
                    <input type="password" value={password} onChange={handlePassword}/>
                </label>
                <button type="submit">Login</button>
            </form>
            <Link to={"/register"}>Register new account</Link>
        </>
    )
}
