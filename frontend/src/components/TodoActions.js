import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'
import styled from 'styled-components/macro'

TodoActions.propTypes = {
  todo: PropTypes.object.isRequired,
  onAdvance: PropTypes.func,
  onDelete: PropTypes.func,
}

export default function TodoActions({ todo, onAdvance, onDelete }) {
  return (
    <Wrapper>
      <Link to={`/details/${todo.id}`}>Details</Link>
      <Link to={`/edit/${todo.id}`}>Edit</Link>
      {onAdvance && (
        <ButtonComp adv onClick={() => onAdvance(todo)}>
          Advance
        </ButtonComp>
      )}
      {onDelete && (
        <ButtonComp del onClick={() => onDelete(todo.id)}>
          Delete
        </ButtonComp>
      )}
    </Wrapper>
  )
}

const Wrapper = styled.section`
  display: flex;
  justify-content: space-between;
`

const ButtonComp = styled.button`
  ${props => (props.del ? 'background-color: lightcoral;' : '')}
  ${props => (props.adv ? 'background-color: lightblue;' : '')}
`
