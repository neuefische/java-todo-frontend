import { useParams } from 'react-router-dom'
import Header from '../components/Header'
import Board from '../components/Board'
import PropTypes from 'prop-types'
import { slugToStatus, statusToTitle } from '../service/todo-service'
import PageLayout from '../components/PageLayout'
import styled from 'styled-components/macro'

BoardPage.propTypes = {
  todos: PropTypes.array.isRequired,
  onAdvance: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
}

export default function BoardPage({ todos, onAdvance, onDelete }) {
  const { statusSlug } = useParams()

  const status = slugToStatus(statusSlug)

  const filteredTodos = todos.filter(todo => todo.status === status)

  const title = statusToTitle(status)

  return (
    <PageLayout>
      <Header />
      <BoardStyled
        title={title}
        todos={filteredTodos}
        onAdvance={status !== 'DONE' ? onAdvance : undefined}
        onDelete={status === 'DONE' ? onDelete : undefined}
      />
    </PageLayout>
  )
}

const BoardStyled = styled(Board)`
  padding: 0 12px;
`
