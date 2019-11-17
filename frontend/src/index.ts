import { setupServiceWorker } from './sw';
import './app';

document.body.appendChild(document.createElement('compass-app'));
setupServiceWorker();
