import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import PageLayout from '../components/PageLayout'
import Header from '../components/Header'
import TodoDetails from '../components/TodoDetails'
import { getTodoById } from '../service/todo-api-service'

export default function DetailsPage() {
  const { id } = useParams()

  const [todo, setTodo] = useState()

  useEffect(() => {
    getTodoById(id)
      .then(fetchedTodo => setTodo(fetchedTodo))
      .catch(error => console.error(error))
  }, [id])

  if (!todo) {
    return <p>loading</p>
  }


  return (
    <PageLayout>
      <Header />
      <TodoDetails {...todo} />
    </PageLayout>
  )
}
