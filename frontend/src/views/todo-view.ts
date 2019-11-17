import { LitElement, html, customElement, property, css } from 'lit-element';
import '@vaadin/vaadin-text-field';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-checkbox';
import '@vaadin/vaadin-radio-button/vaadin-radio-button';
import '@vaadin/vaadin-radio-button/vaadin-radio-group';

enum VisibilityFilter {
  SHOW_ALL = 'All',
  SHOW_ACTIVE = 'Active',
  SHOW_COMPLETED = 'Completed'
}

interface Todo {
  task: string;
  complete: boolean;
}

@customElement('todo-view')
export class TodoView extends LitElement {
  static get styles() {
    return css`
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
    `;
  }
  @property({ type: Array }) todos: Todo[] = [];
  @property() filter: VisibilityFilter = VisibilityFilter.SHOW_ALL;
  @property() task = '';

  addTodo() {
    if (this.task) {
      this.todos = [
        ...this.todos,
        {
          task: this.task,
          complete: false
        }
      ];
      this.task = '';
    }
  }

  shortcutListener(e: KeyboardEvent) {
    if (e.key === 'Enter') {
      this.addTodo();
    }
  }

  updateTask(e: HTMLElementEvent<HTMLTextAreaElement>) {
    this.task = e.target.value;
  }

  updateTodoStatus(updatedTodo: Todo, complete: boolean) {
    this.todos = this.todos.map(todo =>
      updatedTodo === todo ? { ...updatedTodo, complete } : todo
    );
  }

  filterChanged(e: HTMLElementEvent<HTMLTextAreaElement>) {
    this.filter = e.target.value as VisibilityFilter;
  }

  clearCompleted() {
    this.todos = this.todos.filter(todo => !todo.complete);
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
          <vaadin-text-field
            placeholder="Task"
            value="${this.task}"
            @change="${this.updateTask}"
          >
          </vaadin-text-field>

          <vaadin-button theme="primary" @click="${this.addTodo}">
            Add Todo
          </vaadin-button>
        </div>

        <div class="todos-list">
          ${this.applyFilter(this.todos).map(
            todo => html`
              <div class="todo-item">
                <vaadin-checkbox
                  ?checked="${todo.complete}"
                  @change="${(e: HTMLElementEvent<HTMLInputElement>) =>
                    this.updateTodoStatus(todo, e.target.checked)}"
                >
                  ${todo.task}
                </vaadin-checkbox>
              </div>
            `
          )}
        </div>

        <vaadin-radio-group
          class="visibility-filters"
          value="${this.filter}"
          @value-changed="${this.filterChanged}"
        >
          ${Object.values(VisibilityFilter).map(
            filter => html`
              <vaadin-radio-button value="${filter}">
                ${filter}
              </vaadin-radio-button>
            `
          )}
        </vaadin-radio-group>
        <vaadin-button @click="${this.clearCompleted}">
          Clear completed
        </vaadin-button>
      </div>
    `;
  }
}
