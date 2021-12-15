const nextTodoStatus = {
  OPEN: 'IN_PROGRESS',
  IN_PROGRESS: 'DONE',
}

export const nextStatus = status => nextTodoStatus[status]

const slugStatus = {
  todo: 'OPEN',
  doing: 'IN_PROGRESS',
  done: 'DONE',
}

export const slugToStatus = slug => slugStatus[slug]

const statusTitle = {
  OPEN: 'Todo',
  IN_PROGRESS: 'Doing',
  DONE: 'Done',
}

export const statusToTitle = status => statusTitle[status]
