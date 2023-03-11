import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
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
            .then(() => {
                navigate("/login")
                setUsername("")
                setPassword("")})
            .catch(e => console.error(e))
    }

    return(
        <>
            <div className={"login-container"}>
                <Link className={"link-register"} to={"/login"}>Login</Link>
                <form className={"form-submit"} onSubmit={handleSubmit}>
                    <label className={"login-element"}>
                        <p className={"login-label"}>Username:</p>
                        <input className={"input-login"} type="text" value={username} onChange={handleUsername}/>
                    </label>
                    <label className={"login-element"}>
                        <p className={"login-label"}>Password:</p>
                        <input className={"input-login"} type="password" value={password} onChange={handlePassword}/>
                    </label>
                    <button className={"button-submit"} type="submit">Sign Up</button>
                </form>
            </div>
        </>
    )
}