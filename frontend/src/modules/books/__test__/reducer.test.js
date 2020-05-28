import reducer from '../reducer';
import * as actions from '../actions';

test('BUY_TICKET_COMPLETED', () => {

    const ticket = 0;
    const bookId = 1;
    const initialState = {ticket: {id: ticket}}

    const state = reducer(initialState, actions.buyTicketCompleted(bookId));

    expect(state.ticket).toEqual(bookId);
    
});
