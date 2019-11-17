import './views/todo-view';
import './views/stats-view';
import './views/not-found-view';
export const routes: any[] = [
  {
    path: '/',
    component: 'todo-view',
  },
  {
    path: '/stats',
    component: 'stats-view',
    action: () => import(/* webpackChunkName: "stats" */ './views/stats-view'), //
  },
  {
    path: '(.*)',

    component: 'not-found-view',
    action: () => import(/* webpackChunkName: "not-found-view" */ './views/not-found-view'),
  },
];
