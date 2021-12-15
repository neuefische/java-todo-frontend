import { useState } from 'react'
import { useHistory } from 'react-router-dom'

export default function useEditForm(todo, onSave) {
  const [formData, setFormData] = useState({
    description: todo.description,
    status: todo.status,
  })
  const history = useHistory()

  const handleSubmit = event => {
    event.preventDefault()
    const updatedTodo = { ...todo, ...formData }
    onSave(updatedTodo).then(() => history.push('/'))
  }

  const resetForm = () => {
    setFormData({
      description: todo.description,
      status: todo.status,
    })
  }

  const handleChange = event => {
    const { name, value } = event.target
    setFormData({ ...formData, [name]: value })
  }

  return { formData, handleSubmit, handleChange, resetForm }
}
