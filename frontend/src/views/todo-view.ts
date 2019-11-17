import { LitElement, html, property, css, customElement } from 'lit-element';
import { VisibilityFilter, getVisibleTodosSelector, Todo, State } from '../redux/reducer';
import { connect } from 'pwa-helpers';
import { store } from '../redux/store';
import { addTodo, updateTodoStatus, updateFilter, clearCompleted } from '../redux/actions';
import { main } from '../style';

@customElement('todo-view')
export class TodoView extends connect(store)(LitElement) {
  static get styles() {
    return [
      main,
      css`
        .container {
          display: block;
          max-width: 800px;
          margin: 0 auto;
        }
        .container .input-layout {
          width: 100%;
          display: flex;
        }
        .container .input-layout vaadin-text-field {
          flex: 1;
          margin-right: var(--spacing);
        }
        .container .todos-list {
          margin-top: var(--spacing);
        }
        .container .visibility-filters {
          margin-top: calc(4 * var(--spacing));
        }
      `,
    ];
  }
  @property({ type: Array }) todos!: Todo[];
  @property() filter!: VisibilityFilter;
  @property() task: string | undefined;

  stateChanged(state: State) {
    this.todos = getVisibleTodosSelector(state);
    this.filter = state.filter;
  }

  shortcutListener(e: KeyboardEvent) {
    if (e.key === 'Enter') {
      this.addTodo();
    }
  }

  updateTask(e: HTMLElementEvent<HTMLTextAreaElement>) {
    this.task = e.target.value;
  }

  addTodo() {
    if (this.task) {
      store.dispatch(addTodo(this.task));
      this.task = '';
    }
  }

  updateTodoStatus(updatedTodo: Todo, complete: boolean) {
    store.dispatch(updateTodoStatus(updatedTodo, complete));
  }

  filterChanged(e: CustomEvent<{ value: VisibilityFilter }>) {
    store.dispatch(updateFilter(e.detail.value));
  }

  clearCompleted() {
    store.dispatch(clearCompleted());
  }

  applyFilter(todos: Todo[]) {
    switch (this.filter) {
      case VisibilityFilter.SHOW_ACTIVE:
        return todos.filter(todo => !todo.complete);
      case VisibilityFilter.SHOW_COMPLETED:
        return todos.filter(todo => todo.complete);
      default:
        return todos;
    }
  }

  render() {
    return html`
      <div class="container">
        <div class="input-layout" @keyup="${this.shortcutListener}">
          <input type="text" placeholder="Task" value="${this.task || ''}" @input="${this.updateTask}" />

          <button @click="${this.addTodo}">
            Add Todo
          </button>
        </div>

        <div class="todos-list">
          ${this.todos.map(
            todo => html`
              <div class="todo-item">
                <vaadin-checkbox
                  ?checked="${todo.complete}"
                  @change="${(e: HTMLElementEvent<HTMLInputElement>) => this.updateTodoStatus(todo, e.target.checked)}"
                >
                  ${todo.task}
                </vaadin-checkbox>
              </div>
            `
          )}
        </div>
        <vaadin-radio-group class="visibility-filters" value="${this.filter}" @value-changed="${this.filterChanged}">
          ${Object.values(VisibilityFilter).map(
            filter => html`
              <vaadin-radio-button value="${filter}">
                ${filter}
              </vaadin-radio-button>
            `
          )}
        </vaadin-radio-group>
        <button @click="${this.clearCompleted}">
          Clear completed
        </button>
      </div>
    `;
  }
}
