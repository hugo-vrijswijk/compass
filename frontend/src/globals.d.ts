type HTMLElementEvent<T extends HTMLElement> = Event & {
  target: T;
  // probably you might want to add the currentTarget as well
  // currentTarget: T;
};

interface Window {
  __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof import('redux').compose;
}
