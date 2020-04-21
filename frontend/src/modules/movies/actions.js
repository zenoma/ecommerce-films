import * as actionTypes from './actionTypes';

import backend from '../../backend';

const getListingCompleted = movies => ({
    type: actionTypes.GET_LISTING_COMPLETED,
    movies
});

export const getListing = (cinemaId, listingDate) => dispatch => {
    let date = listingDate == null ? getCurrentDate(): listingDate;

    backend.movieService.getListing(cinemaId,date,
        response => dispatch(getListingCompleted(response)))
}


function getCurrentDate(){

    let newDate = new Date()
    let date = newDate.getDate();
    let month = newDate.getMonth() + 1;
    let year = newDate.getFullYear();
    
    return `${year}-${month<10?`0${month}`:`${month}`}-${date}`;
}
