import { Link } from 'react-router-dom'
import styled from 'styled-components/macro'

export default function TodoDetails({ description, status }) {
  return (
    <Wrapper>
      <h2>{description}</h2>
      <p>Status: {status}</p>
      <Link to="/">Back</Link>
    </Wrapper>
  )
}

const Wrapper = styled.section`
  padding: 12px;
`
