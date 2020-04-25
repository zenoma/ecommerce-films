import * as actionTypes from './actionTypes';

import {combineReducers} from 'redux';

const initialState = {
    movies: null,
    listingDate: null,
    cities: null, 
    cinemas: null,
    selectedCinema: null,
    movie : null
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

const selectedCinema = (state = initialState.selectedCinema, action) => {
    switch(action.type) {
        case actionTypes.GET_LISTING_COMPLETED:
            return action.selectedCinema;
        case actionTypes.CLEAR_SELECTED_CINEMA:
            return initialState.selectedCinema;
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
        default:
            return state;
    }
}

const reducer = combineReducers({
    movies,
    listingDate,
    selectedCinema,
    cities, 
    cinemas,
    movie
});

export default reducer;