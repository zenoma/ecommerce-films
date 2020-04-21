import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import movies from '../modules/movies';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    movies: movies.reducer
});

export default rootReducer;
