import * as actionTypes from './actionTypes';

import {combineReducers} from 'redux';

const initialState = {
    movies: null
};

const movies = (state = initialState.movies, action) => {
    switch (action.type) {
        case actionTypes.GET_LISTING_COMPLETED:
            return action.movies;
        default:
            return state;
    }
}

const reducer = combineReducers({
    movies
});

export default reducer;