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

const buyTicketCompleted = (ticket) => ({
    type: actionTypes.BUY_TICKET_COMPLETED,
    ticket
});

export const clearTicket = () =>({
    type: actionTypes.CLEAR_TICKET
});