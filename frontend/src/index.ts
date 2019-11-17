import { setupServiceWorker } from './sw';
import './views/todo-view';

document.body.appendChild(document.createElement('todo-view'));
setupServiceWorker();
