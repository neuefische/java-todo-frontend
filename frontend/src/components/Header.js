import Navbar from './Navbar'
import styled from 'styled-components/macro'

export default function Header() {
  return (
    <header>
      <Heading>Super Kanban</Heading>
      <Navbar />
    </header>
  )
}

const Heading = styled.h1`
  margin: 0;
  padding: 12px;
`
