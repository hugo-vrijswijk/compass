import { rootInjector } from 'typed-inject';
import { Home } from './Home';


const appInjector = rootInjector
  .provideClass('home', Home);

export default appInjector;
