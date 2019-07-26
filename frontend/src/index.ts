import { setupServiceWorker } from './service-worker-setup';
import { setupRouter } from './router';

document.body.appendChild(document.createElement('compass-app'));
setupServiceWorker();
setupRouter();
