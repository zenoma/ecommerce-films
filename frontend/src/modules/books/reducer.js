import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    ticket: null,
    bookHistory: null
};

const ticket = (state = initialState.ticket, action) => {

    switch (action.type) {

        case actionTypes.BUY_TICKET_COMPLETED:
            return action.ticket;
        case actionTypes.CLEAR_TICKET:
            return initialState.ticket;
        default:
            return state;

    }

}

const bookHistory = (state = initialState.bookHistory, action) => {
    switch (action.type) {

        case actionTypes.FIND_BOOKS_COMPLETED:
            return action.bookHistory;
        case actionTypes.CLEAR_BOOKS_HISTORY:
            return initialState.bookHistory;
        default:
            return state;
    }
}


const reducer = combineReducers({
    ticket,
    bookHistory
});

export default reducer;


