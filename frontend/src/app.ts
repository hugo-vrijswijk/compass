import { customElement, LitElement, html } from 'lit-element';
import { Router } from '@vaadin/router';
import { routes } from './routes';
@customElement('compass-app')
export class App extends LitElement {
  constructor() {
    super();
    window.addEventListener('load', () => {
      this.initRouter();
    });
  }

  initRouter() {
    const router = new Router(this.shadowRoot!.querySelector('main'));
    router.setRoutes(routes);
  }

  render() {
    return html`
      <header>
        <h1>Todo app</h1>
      </header>
      <nav>
        <a href="/">Todos</a>
        <a href="/stats">Stats</a>
      </nav>
      <main></main>
    `;
  }
}
