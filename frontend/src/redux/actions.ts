import nanoid from 'nanoid';
import { VisibilityFilter, Todo } from './reducer';
import { AddTodoAction, UpdateTodoStatusAction, UpdateFilterAction, ClearCompletedAction } from './types';

export const addTodo = (task: string): AddTodoAction => {
  return {
    type: 'ADD_TODO',
    todo: {
      id: nanoid(),
      task,
      complete: false,
    },
  };
};

export const updateTodoStatus = (todo: Todo, complete: boolean): UpdateTodoStatusAction => {
  return {
    type: 'UPDATE_TODO_STATUS',
    todo,
    complete,
  };
};

export const updateFilter = (filter: VisibilityFilter): UpdateFilterAction => {
  return {
    type: 'UPDATE_FILTER',
    filter,
  };
};

export const clearCompleted = (): ClearCompletedAction => {
  return {
    type: 'CLEAR_COMPLETED',
  };
};
