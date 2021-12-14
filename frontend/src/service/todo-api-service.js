import axios from 'axios'

const getHeader = (token) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        },
    }
}

export const getTodos = (token) => {
    return axios.get('/api/todo', getHeader(token)).then(response => response.data)
}

export const postTodo = (newDescription, token) => {
    const newTodo = {
        description: newDescription,
        status: 'OPEN',
    }

    return axios.post('/api/todo', newTodo, getHeader(token)).then(response => response.data)
}

export const putTodo = (todo, token) => {
    return axios.put(`/api/todo/${todo.id}`, todo, getHeader(token)).then(response => response.data)
}

export const deleteTodo = (id, token) => {
    return axios.delete(`/api/todo/${id}`, getHeader(token))
}

export const getTodoBy = (id, token) => {
    return axios.get(`/api/todo/${id}`, getHeader(token))
}
