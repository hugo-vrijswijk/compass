import { LitElement, html, customElement } from 'lit-element';
import '@/schema';
@customElement('compass-app')
export class App extends LitElement {

  private message: HelloResponse = { message: "" };
  render() {
    return html`
    <header>
      <h1>Todo app</h1>
    </header>
    <nav>
      <a href="/">Todos</a>
      <a href="/stats">Stats</a>
    </nav>
    <main>${this.message.message}</main>`;
  }

}
