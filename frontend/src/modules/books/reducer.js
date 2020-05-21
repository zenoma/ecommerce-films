import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    ticket: null

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

const reducer = combineReducers({
    ticket
});

export default reducer;


