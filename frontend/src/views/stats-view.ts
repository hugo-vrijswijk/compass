import { LitElement, property, html, customElement } from 'lit-element';
import { connect } from 'pwa-helpers';
import { store } from '../redux/store';
import { State, statsSelector } from '../redux/reducer';

interface ChartConfig {
  name: string;
  y: number;
}

customElement('stats-view');
export class StatsView extends connect(store)(LitElement) {
  @property({ type: Array }) chartConfig!: ChartConfig[];
  @property({ type: Boolean }) hasTodos = false;

  stateChanged(state: State) {
    const stats = statsSelector(state);
    this.chartConfig = [
      { name: 'Completed', y: stats.completed },
      { name: 'Active', y: stats.active },
    ];
    this.hasTodos = state.todos.length > 0;
  }

  getChart() {
    if (this.hasTodos) {
      return this.chartConfig.map(
        conf =>
          html`
            <p>${conf.name}: ${conf.y}</p>
          `
      );
    } else {
      return html`
        <p>Nothing to do! 🌴🍻☀️</p>
      `;
    }
  }

  render() {
    return html`
      <style>
        stats-view {
          display: block;
        }
      </style>

      ${this.getChart()}
    `;
  }
}
