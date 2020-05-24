import React from 'react';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import {render, fireEvent} from '@testing-library/react';
import {createMemoryHistory} from 'history'

import BuyForm from '../components/BuyForm';
import {IntlProvider} from 'react-intl';
import messages from '../../../i18n/messages';
import {Router} from 'react-router-dom';
import * as actions from '../actions';

const renderComponent = (component, initialState={})=> {

    const store = createStore(() => initialState);
    store.dispatch = jest.fn();
    const history = createMemoryHistory();

    return {history, ...render(
        <Provider store={store}>
            <IntlProvider locale="en" messages={messages['en']}>
                <Router history={history}>
                    {component}
                </Router>
            </IntlProvider>
        </Provider>
    )};
}

afterEach(() => actions.buyTickets.mockRestore());

test('buy - success', () => {

    const bookId = 1;
    const movieSessionId=1;
    const initialState = {book: {ticket: bookId}};
    const buySpy = jest.spyOn(actions, 'buyTickets').mockImplementation(
        (_bookId, _tickets, _creditcard, _movieSessionId, onSuccess, _onErrors) => 
            onSuccess());

    const {getByLabelText, getByRole, history} = renderComponent(<BuyForm/>,
        initialState);
    const ticketsInput = getByLabelText("Seats")
    const creditCardInput = getByLabelText('Credit Card');
    const buyButton = getByRole('button');
    const seats = 1;
    const creditcard = "1234567890123456"; 

    fireEvent.change(ticketsInput, {target: {value: seats}});
    fireEvent.change(creditCardInput, {target: {value: creditcard}});

    expect(ticketsInput.value).toEqual(seats);
    expect(creditCardInput.value).toEqual(creditcard);

    fireEvent.click(buyButton);

    expect(buySpy.mock.calls[0][0]).toEqual(bookId);
    expect(buySpy.mock.calls[0][1]).toEqual(seats);
    expect(buySpy.mock.calls[0][2]).toEqual(creditcard);
    expect(buySpy.mock.calls[0][2]).toEqual(movieSessionId);
    expect(history.length).toEqual(2);
    expect(history.location.pathname).toEqual('/books/purchase-completed');

});