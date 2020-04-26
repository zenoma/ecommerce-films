import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    book: null
};

const book = (state = initialState.book, action) => {

    switch (action.type) {

        case actionTypes.BUY_TICKET_COMPLETED:
            return ;

        case actionTypes.DELIVER_TICKET_COMPLETED:
            return ;

        case actionTypes.FIND_BOOKS:
            return ;

        default:
            return state;

    }

}

const reducer = combineReducers({
    book
});

export default reducer;


