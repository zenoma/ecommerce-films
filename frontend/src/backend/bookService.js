import {config, appFetch} from './appFetch';

export const buyTickets = (tickets, creditcard, movieSessionId, onSuccess, 
    onErrors) =>
    appFetch(`/booking/books/buy`, 
        config('POST', {tickets, creditcard, movieSessionId}), onSuccess, onErrors);

export const deliverTicket = (creditcard, code, onSuccess, 
    onErrors) =>
    appFetch(`/booking/books/deliver`, 
        config('POST', {creditcard, code}), onSuccess, onErrors);

export const findBooks = ({page}, onSuccess) => 
    appFetch(`/booking/books?page=${page}`, config('GET'), onSuccess);