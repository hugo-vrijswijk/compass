import { createStore, compose } from 'redux';
import { reducer, State } from './reducer';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const STORAGE_KEY = '__todo_app__';

const saveState = (state: State) => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(state));
};

const loadState = (): State | undefined => {
  const json = localStorage.getItem(STORAGE_KEY);
  return json ? JSON.parse(json) : undefined;
};
export const store = createStore(reducer, loadState(), composeEnhancers());

store.subscribe(() => {
  saveState(store.getState());
});
