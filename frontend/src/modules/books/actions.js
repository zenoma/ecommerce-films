import backend from '../../backend';
import * as actionTypes from './actionTypes';

export const buyTickets = (seats, creditCard, sessionId, onSuccess, 
    onErrors) => dispatch =>
    backend.bookingService.buyTickets(seats, creditCard,
        sessionId, ({id}) => {
        dispatch(buyTicketCompleted({id, seats, creditCard, sessionId}));
        onSuccess();
    },
        onErrors);

const buyTicketCompleted = (ticket) => ({
    type: actionTypes.BUY_TICKET_COMPLETED,
    ticket
});