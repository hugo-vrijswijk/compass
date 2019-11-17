import { LitElement, html, customElement } from 'lit-element';

@customElement('not-found-view')
export class NotFoundView extends LitElement {
  render() {
    return html`
      <h1>Page not found!</h1>
      <p>Please check your url</p>
    `;
  }
}
