import * as actionTypes from './actionTypes';
import common from '../common';
import backend from '../../backend';

const getListingCompleted = (movies, date) => ({
    type: actionTypes.GET_LISTING_COMPLETED,
    movies, date
});

export const getListing = (cinemaId, listingDate) => dispatch => {
    if(listingDate==null){
        listingDate=common.dateUtils.formatDate(new Date());
    }
    backend.movieService.getListing(cinemaId, listingDate,
        response => dispatch(getListingCompleted(response, listingDate)))
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