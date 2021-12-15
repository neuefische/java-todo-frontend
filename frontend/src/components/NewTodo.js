import { useState } from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components/macro'

NewTodo.propTypes = {
  onAdd: PropTypes.func.isRequired,
}

export default function NewTodo({ onAdd }) {
  const [description, setDescription] = useState('')

  const handleSubmit = event => {
    event.preventDefault()
    if (description) {
      onAdd(description).then(() => setDescription(''))
    }
  }

  return (
    <Wrapper onSubmit={handleSubmit}>
      <input
        type="text"
        value={description}
        onChange={event => setDescription(event.target.value)}
      />
      <button>Add</button>
    </Wrapper>
  )
}

const Wrapper = styled.form`
  display: grid;
  grid-template-columns: 1fr min-content;
  padding: 12px;
`
