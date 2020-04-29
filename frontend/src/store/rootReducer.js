import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import movies from '../modules/movies';
import books from '../modules/books';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    movies: movies.reducer,
    books: books.reducer
});

export default rootReducer;
