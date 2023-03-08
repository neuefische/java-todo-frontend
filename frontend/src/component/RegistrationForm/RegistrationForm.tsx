import {ChangeEvent, FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
export default function RegistrationForm() {
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
        axios.post("api/users/", {username, password})
            .then()
            .catch(e => console.error(e))
    }

    return(
        <form onSubmit={handleSubmit}>
            <label>
                Username
                <input type="text" value={username} onChange={handleUsername}/>
            </label>
            <label>
                Password
                <input type="password" value={password} onChange={handlePassword}/>
            </label>
            <button type="submit">Sign Up</button>
        </form>
    )
}