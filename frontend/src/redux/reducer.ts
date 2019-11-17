import { TodoAction } from './types';
import { createSelector } from 'reselect';

export enum VisibilityFilter {
  SHOW_ALL = 'All',
  SHOW_ACTIVE = 'Active',
  SHOW_COMPLETED = 'Completed',
}

export interface Todo {
  id: string;
  task: string;
  complete: boolean;
}

export interface State {
  todos: Todo[];
  filter: VisibilityFilter;
}

const INITIAL_STATE: State = {
  todos: [],
  filter: VisibilityFilter.SHOW_ALL,
};

const getTodosSelector = (state: State) => state.todos;
const getFilterSelector = (state: State) => state.filter;

export const getVisibleTodosSelector = createSelector(
  getTodosSelector,
  getFilterSelector,

  (todos, filter) => {
    switch (filter) {
      case VisibilityFilter.SHOW_COMPLETED:
        return todos.filter(todo => todo.complete);
      case VisibilityFilter.SHOW_ACTIVE:
        return todos.filter(todo => !todo.complete);
      default:
        return todos;
    }
  }
);

export const statsSelector = createSelector(getTodosSelector, todos => {
  const completed = todos.filter(todo => todo.complete).length;
  return {
    completed,
    active: todos.length - completed,
  };
});

export const reducer = (state = INITIAL_STATE, action: TodoAction): State => {
  switch (action.type) {
    case 'ADD_TODO':
      return {
        ...state,
        todos: [...state.todos, action.todo],
      };
    case 'UPDATE_TODO_STATUS':
      return {
        ...state,
        todos: state.todos.map(todo =>
          todo.id === action.todo.id ? { ...action.todo, complete: action.complete } : todo
        ),
      };
    case 'UPDATE_FILTER':
      return {
        ...state,
        filter: action.filter,
      };
    case 'CLEAR_COMPLETED':
      return {
        ...state,
        todos: state.todos.filter(todo => !todo.complete),
      };
    default:
      return state;
  }
};
