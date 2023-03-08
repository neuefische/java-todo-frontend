import React from 'react';
import './App.css';
import TodoHeader from "./component/TodoHeader";
import TodoBoard from "./component/TodoBoard/TodoBoard";
import InputBox from "./component/InputBox/InputBox";
import useTodo from "./hooks/useTodo";
import {Route, Routes} from "react-router";
import RegistrationForm from "./component/RegistrationForm/RegistrationForm";
import LoginForm from "./component/LoginForm/LoginForm";


function App() {
    const {handleAddButton, handleSaveChange, handleAdvanceButtonClick, todoList} =useTodo()

  return (
    <div className="App">
      <header className="App-header">
        <TodoHeader/>
      </header>
        <main>

        </main>
        <RegistrationForm></RegistrationForm>
        <Routes>
            <Route path={"/login"} element={<LoginForm/>}/>
            <Route path={"/"} element={<><TodoBoard todoList={todoList}
                                                    handleAdvanceButtonClick={handleAdvanceButtonClick}
                                                    handleSaveChange={handleSaveChange}/><InputBox
                handleAddButton={handleAddButton}/></>}/>
        </Routes>
    </div>
  )
}

export default App;
