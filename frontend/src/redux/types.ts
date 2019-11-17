import { Todo, VisibilityFilter } from './reducer';

export interface AddTodoAction {
  type: 'ADD_TODO';
  todo: Todo;
}

export interface UpdateTodoStatusAction {
  type: 'UPDATE_TODO_STATUS';
  todo: Todo;
  complete: boolean;
}

export interface UpdateFilterAction {
  type: 'UPDATE_FILTER';
  filter: VisibilityFilter;
}

export interface ClearCompletedAction {
  type: 'CLEAR_COMPLETED';
}

export type TodoAction = AddTodoAction | UpdateTodoStatusAction | UpdateFilterAction | ClearCompletedAction;
