import styled from 'styled-components/macro'
import useEditForm from '../hooks/useEditForm'

export default function TodoForm({ todo, onSave }) {
  const { formData, handleSubmit, handleChange, resetForm } = useEditForm(
    todo,
    onSave
  )

  return (
    <Wrapper onSubmit={handleSubmit}>
      <label>
        Description
        <input
          type="text"
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
      </label>
      <label>
        Status
        <select name="status" value={formData.status} onChange={handleChange}>
          <option value="OPEN">open</option>
          <option value="IN_PROGRESS">doing</option>
          <option value="DONE">done</option>
        </select>
      </label>
      <button type="button" onClick={resetForm}>
        Reset
      </button>
      <button>Save</button>
    </Wrapper>
  )
}

const Wrapper = styled.form`
  display: grid;
  grid-gap: 12px;
  place-content: center;

  input,
  select {
    display: block;
    width: 100%;
  }
`
