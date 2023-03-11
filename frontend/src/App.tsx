import React from 'react';
import './App.css';
import TodoHeader from "./component/TodoHeader";
import TodoBoard from "./component/TodoBoard/TodoBoard";
import {Route, Routes} from "react-router";
import RegistrationForm from "./component/RegistrationForm/RegistrationForm";
import LoginForm from "./component/LoginForm/LoginForm";
import NavBar from "./component/NavBar/NavBar";
import axios from "axios";
import Cookies from 'js-cookie'


//add interceptor
axios.interceptors.request.use(
    function (config) {
        return fetch('/api/csrf').then(() => {
            config.headers['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN')
            return config
        })
    },
    function (error) {
        return Promise.reject(error)
    }
)
function App() {

  return (
    <div className="App">
      <header className="App-header">
        <TodoHeader/>
      </header>
        <NavBar/>
        <Routes>
            <Route path={"/"} element={<TodoBoard/>}/>
            <Route path={"/login"} element={<LoginForm/>}/>
            <Route path={"/register"} element={<RegistrationForm/>}/>
        </Routes>
    </div>
  )
}

export default App;
