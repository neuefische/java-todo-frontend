import PropTypes from 'prop-types'
import styled from 'styled-components/macro'
import TodoActions from './TodoActions'

TodoItem.propTypes = {
  todo: PropTypes.shape({
    id: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired,
  }).isRequired,
  onAdvance: PropTypes.func,
  onDelete: PropTypes.func,
}

export default function TodoItem({ todo, onAdvance, onDelete }) {
  return (
    <TodoItemStyleComp>
      <h3>{todo.description}</h3>
      <TodoActions todo={todo} onAdvance={onAdvance} onDelete={onDelete} />
    </TodoItemStyleComp>
  )
}

const TodoItemStyleComp = styled.section`
  border: 1px solid #333;
  border-radius: 8px;
  padding: 12px;
  box-shadow: 1px 2px 8px #666;
`
