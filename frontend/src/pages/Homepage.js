import Header from '../components/Header'
import BoardsOverview from '../components/BoardsOverview'
import NewTodo from '../components/NewTodo'
import PageLayout from '../components/PageLayout'

export default function Homepage({
  todos,
  advanceTodo,
  removeTodo,
  createNewTodo,
}) {
  return (
    <PageLayout>
      <Header />
      <BoardsOverview
        todos={todos}
        onAdvance={advanceTodo}
        onDelete={removeTodo}
      />
      <NewTodo onAdd={createNewTodo} />
    </PageLayout>
  )
}
