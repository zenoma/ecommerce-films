import backend from '../../backend';
import * as actionTypes from './actionTypes';

export const buyTickets = (seats, creditCard, sessionId, onSuccess, 
    onErrors) => dispatch =>
    backend.bookService.buyTickets(seats, creditCard,
        sessionId, ({id}) => {
            dispatch(buyTicketCompleted(id));
            onSuccess();
    },
    onErrors);

export const buyTicketCompleted = (ticket) => ({
    type: actionTypes.BUY_TICKET_COMPLETED,
    ticket
});

export const clearTicket = () =>({
    type: actionTypes.CLEAR_TICKET
});

export const deliverTicket = (bookId, creditCard, onSuccess, 
    onErrors) => dispatch => {
        backend.bookService.deliverTicket(creditCard, bookId, onSuccess, onErrors);
}


const findBooksCompleted = bookHistory =>({
    type: actionTypes.FIND_BOOKS_COMPLETED,
    bookHistory
});

const clearBookHistory = () => ({
    type: actionTypes.CLEAR_BOOKS_HISTORY
});

export const findBookHistory = criteria => dispatch => {
    dispatch(clearBookHistory());
    backend.bookService.findBooks(criteria, 
        result => dispatch(findBooksCompleted({criteria, result})));
}

export const previousFindBooksResultPage = criteria => 
    findBookHistory({page: criteria.page-1});

export const nextFindBooksResultPage = criteria => 
    findBookHistory({page: criteria.page+1});