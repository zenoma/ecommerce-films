import {config, appFetch} from './appFetch';

export const buyTickets = (seats, creditCard, sessionId, onSuccess, 
    onErrors) =>
    appFetch(`/booking/books/buy`, 
        config('POST', {seats, creditCard, sessionId}), onSuccess, onErrors);

export const deliverTicket = (creditCard, code, onSuccess, 
    onErrors) =>
    appFetch(`/booking/books/deliver`, 
        config('POST', {creditCard, code}), onSuccess, onErrors);

export const findBooks = ({page}, onSuccess) => 
    appFetch(`/booking/books?page=${page}`, config('GET'), onSuccess);