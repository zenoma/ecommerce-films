import * as actionTypes from './actionTypes';

import {combineReducers} from 'redux';

const initialState = {
    movies: null,
    listingDate: null,
    cities: null, 
    cinemas: null,
    movie : null,
    movieSession: null
};

const movies = (state = initialState.movies, action) => {
    switch (action.type) {
        case actionTypes.GET_LISTING_COMPLETED:
            return action.movies.length > 0 ? action.movies : initialState.movies;
        case actionTypes.CLEAR_MOVIES:
            return initialState.movies;
        default:
            return state;
    }
}

const listingDate = (state = initialState.listingDate, action) => {
    switch(action.type) {
        case actionTypes.GET_LISTING_COMPLETED:
            return action.date;
        default:
            return state;
    }
}

const cities = (state = initialState.cities, action) => {
    switch(action.type) {
        case actionTypes.GET_CITIES_COMPLETED:
            return action.cities;
        default:
            return state;
    }
}

const cinemas = (state = initialState.cinemas, action) => {
    switch(action.type){
        case actionTypes.GET_CINEMAS_COMPLETED:
            return action.cinemas;
        default:
            return state;
    }
}

const movie = (state = initialState.movie, action) => {
    switch (action.type) {
        case actionTypes.FIND_MOVIE_BY_ID:
            return action.movie; 
        case actionTypes.CLEAR_MOVIE_DETAILS:
            return initialState.movie;
        default:
            return state;
    }
}
    

const movieSession = (state = initialState.movieSession, action) => {
    switch(action.type){
        case actionTypes.GET_MOVIESESSION_COMPLETED:
            return action.movieSession;
        case actionTypes.CLEAR_MOVIESESSION:
            return initialState.movieSession;
        default:
            return state;
    }
}

const reducer = combineReducers({
    movies,
    listingDate,
    cities, 
    cinemas,
    movie,
    movieSession
});

export default reducer;