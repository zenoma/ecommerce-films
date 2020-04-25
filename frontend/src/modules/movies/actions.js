import * as actionTypes from './actionTypes';
import common from '../common';
import backend from '../../backend';

const getListingCompleted = (movies, date, selectedCinema) => ({
    type: actionTypes.GET_LISTING_COMPLETED,
    movies, date, selectedCinema
});

export const getListing = (cinemaId, listingDate) => dispatch => {
    if(listingDate==null){
        listingDate=common.dateUtils.formatDate(new Date());
    }
    if(cinemaId != null){
        backend.movieService.getListing(cinemaId, listingDate, 
            response => dispatch(getListingCompleted(response, listingDate, cinemaId)))
    }
}

const getCitiesCompleted = (cities) => ({
    type: actionTypes.GET_CITIES_COMPLETED,
    cities
})

export const getCities = () => dispatch => {
    backend.movieService.getCities(response => {
        dispatch(getCitiesCompleted(response))
    });
}

const getCinemasCompleted = (cinemas) => ({
    type : actionTypes.GET_CINEMAS_COMPLETED,
    cinemas
});

export const getCinemas = (city) => dispatch => {
    dispatch(clearSelectedCinema());
    dispatch(clearMovies());
    backend.movieService.getCinemas(city, response => {
        dispatch(getCinemasCompleted(response))
    })
};

const getMovieSessionCompleted = (movieSession) => ({
    type: actionTypes.GET_MOVIESESSION_COMPLETED,
    movieSession
});

export const getMovieSession = (id) => dispatch => {
    dispatch(clearMovieSession());
    backend.movieService.getMovieSession(id, response => {
        dispatch(getMovieSessionCompleted(response))
    });
}

const clearSelectedCinema = () =>({
    type: actionTypes.CLEAR_SELECTED_CINEMA
});

const clearMovies = () =>({
    type: actionTypes.CLEAR_MOVIES
});


const getMovieByIdCompleted = movie => ({
    type: actionTypes.FIND_MOVIE_BY_ID,
    movie
})

export const getMovieById = id => dispatch =>{
    backend.movieService.getMovieById(id,
        movie => dispatch(getMovieByIdCompleted(movie)));
}
const clearMovieSession = () =>({
    type: actionTypes.CLEAR_MOVIESESSION
});
