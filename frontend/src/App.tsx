import React from 'react';
import './App.css';
import TodoHeader from "./component/TodoHeader";
import TodoBoard from "./component/TodoBoard/TodoBoard";
import InputBox from "./component/InputBox/InputBox";
import useTodo from "./hooks/useTodo";


function App() {
    const {handleAddButton, handleSaveChange, handleAdvanceButtonClick, todoList} =useTodo()


  return (
    <div className="App">
      <header className="App-header">
        <TodoHeader/>
      </header>
        <main>
        <TodoBoard todoList={todoList} handleAdvanceButtonClick={handleAdvanceButtonClick}
                   handleSaveChange={handleSaveChange}/>
            <InputBox handleAddButton={handleAddButton}/>
        </main>
    </div>
  )
}

export default App;
