import PageLayout from '../components/PageLayout'
import Header from '../components/Header'
import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { getTodoById } from '../service/todo-api-service'
import TodoForm from '../components/TodoForm'
import PropTypes from 'prop-types'

EditPage.propTypes = {
  onSave: PropTypes.func.isRequired,
}

export default function EditPage({ onSave }) {
  const { id } = useParams()

  const [todo, setTodo] = useState()

  useEffect(() => {
    getTodoById(id).then(setTodo).catch(console.error)
  }, [id])

  return (
    <PageLayout>
      <Header />
      {todo ? <TodoForm todo={todo} onSave={onSave} /> : <p>loading</p>}
    </PageLayout>
  )
}
