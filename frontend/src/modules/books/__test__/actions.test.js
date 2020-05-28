import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';

import * as actions from '../actions';
import backend from '../../../backend';

const middlewares = [thunk]
const mockStore = configureMockStore(middlewares);

afterEach(() => backend.bookService.buyTickets.mockRestore());

test('buyTickets - success', () => {

    const ticket = 1;
    const backendBuyTicketsSpy = jest.spyOn(backend.bookService, 'buyTickets').mockImplementation(
        (_tickets, _creditcard, _movieSessionId, onSuccess, _onErrors) => 
            onSuccess({id: ticket}));
    const tickets = 5;
    const creditcard = '1234567890123456';
    const movieSessionId = 1;
    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();
    const action = actions.buyTickets(tickets, creditcard, movieSessionId, onSuccess, onErrors);
    const expectedActions = [actions.buyTicketCompleted(ticket)];
    const store = mockStore({});

    store.dispatch(action);

    expect(backendBuyTicketsSpy.mock.calls[0][0]).toEqual(tickets);
    expect(backendBuyTicketsSpy.mock.calls[0][1]).toEqual(creditcard);
    expect(backendBuyTicketsSpy.mock.calls[0][2]).toEqual(movieSessionId);
    expect(store.getActions()).toEqual(expectedActions);
    expect(onSuccess).toHaveBeenCalled();
    expect(onErrors).not.toHaveBeenCalled();

});

test('buyTickets - backend error', () => {

    const backendErrors = {globalError: "Some backend error"};

    const backendBuyTicketsSpy = jest.spyOn(backend.bookService, 'buyTickets').mockImplementation(
        (_tickets, _creditcard, _movieSessionId, _onSuccess, onErrors) => 
            onErrors(backendErrors));

    const tickets = 1;
    const creditcard = '1234567890123456';
    const movieSessionId = 1;
    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();
    const action = actions.buyTickets(tickets, creditcard, movieSessionId, onSuccess, onErrors);
    const expectedActions = [];
    const store = mockStore({});

    store.dispatch(action);

    expect(store.getActions()).toEqual(expectedActions);
    expect(onSuccess).not.toHaveBeenCalled();
    expect(onErrors).toHaveBeenCalled();
    expect(onErrors.mock.calls[0][0]).toEqual(backendErrors);


});
