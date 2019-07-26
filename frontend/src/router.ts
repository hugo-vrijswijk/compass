
import { Router } from '@vaadin/router';
// import injector from './injector';

export function setupRouter() {
  window.addEventListener('load', () => {

    const outlet = document.querySelector('compass-app')!;

    const routes = [
      { path: '/', action: () => import(/* webpackChunkName: "home" */ './Home') },
    ];

    const router = new Router(outlet);
    router.setRoutes(routes);

  });
}
