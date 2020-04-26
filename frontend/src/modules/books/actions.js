import backend from '../../backend';
import * as actionTypes from './actionTypes';

export const buyTickets = (seats, creditCard, sessionId, onSuccess, 
    onErrors) => dispatch =>
    backend.bookingService.buyTickets(seats, creditCard,
        sessionId, ({id}) => {
        dispatch(buyTicketCompleted(id));
        onSuccess();
    },
        onErrors);

const buyTicketCompleted = (bookId) => ({
    type: actionTypes.BUY_TICKET_COMPLETED,
    bookId
});

export const deliverTicket = (creditCard, code, onSuccess, onErrors) => dispatch => 
    backend.bookingService.deliverTicket(creditCard,
        code,  //TODO (funcion on onSuccess)///
        },
        onErrors);

const deliverTicketCompleted = () => ({
    type: actionTypes.DELIVER_TICKET_COMPLETED,
});


const clearBooksSearch = () => ({
    type: actionTypes.CLEAR_BOOKS_SEARCH
});

const findBooksCompleted = bookSearch => ({
    type: actionTypes.FIND_BOOKS_COMPLETED,
    bookSearch
});

export const findBooks = criteria => dispatch => {

    dispatch(clearBooksSearch());
    backend.bookingService.findBooks(criteria, 
        result => dispatch(findBooksCompleted({criteria, result})));

}    

export const previousFindBooksResultPage = criteria => 
    findBooks({page: criteria.page-1});

export const nextFindBooksResultPage = criteria => 
    findBooks({page: criteria.page+1});