import {init} from './appFetch';
import * as userService from './userService';
import * as movieService from './movieService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, movieService};
