import useAuth from "../../hooks/useAuth";
import axios from "axios";

export default function LogOutButton() {
    const user = useAuth(false)
    function handleLogOut() {
        axios.post('/api/users/logout').then(() => {
            window.location.href = '/login'
        })
    }
    return (
        user? <button onClick={handleLogOut}>Log Out</button>: null
    )
}