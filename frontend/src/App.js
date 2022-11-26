import { useEffect, useState } from 'react'
import {
  deleteTodo,
  getAllTodos,
  postTodo,
  putTodo,
} from './service/todo-api-service'
import { nextStatus } from './service/todo-service'
import {
  BrowserRouter as Router,
  Route, Routes,
} from 'react-router-dom'
import Homepage from './pages/Homepage'
import DetailsPage from './pages/DetailsPage'
import BoardPage from './pages/BoardPage'
import EditPage from './pages/EditPage'

export default function App() {
  const [todos, setTodos] = useState([])

  useEffect(() => {
    getAllTodos()
      .then(todos => setTodos(todos))
      .catch(error => console.error(error))
  }, [])

  const createNewTodo = description =>
    postTodo(description)
      .then(() => getAllTodos())
      .then(todos => setTodos(todos))
      .catch(error => console.error(error))

  const advanceTodo = todo => {
    const newTodo = { ...todo, status: nextStatus(todo.status) }
    putTodo(newTodo)
      .then(() => getAllTodos())
      .then(todos => setTodos(todos))
      .catch(error => console.error(error))
  }

  const removeTodo = id =>
    deleteTodo(id)
      .then(() => getAllTodos())
      .then(todos => setTodos(todos))
      .catch(error => console.error(error))

  const updateTodo = todo =>
    putTodo(todo)
      .then(() => getAllTodos())
      .then(todos => setTodos(todos))
      .catch(error => console.error(error))

  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Homepage
            todos={todos}
            advanceTodo={advanceTodo}
            removeTodo={removeTodo}
            createNewTodo={createNewTodo}
        />}/>
        <Route path="/board/:statusSlug" element={<BoardPage
            todos={todos}
            onAdvance={advanceTodo}
            onDelete={removeTodo}
        />} />
        <Route path="/edit/:id" element={
          <EditPage onSave={updateTodo} />} />
        <Route path="/details/:id" element={<DetailsPage />}/>
      </Routes>
    </Router>
  )
}
