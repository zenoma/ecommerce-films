import {init} from './appFetch';
import * as userService from './userService';
import * as movieService from './movieService';
import * as bookService from './bookService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, movieService, bookService};
