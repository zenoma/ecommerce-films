import React from 'react';
import BuyForm from '../components/BuyForm';
import {createStore} from 'redux';
import { Provider } from 'react-redux';
import PurchaseCompleted from '../components/PurchaseCompleted';
import {render, fireEvent} from '@testing-library/react';
import * as actions from '../actions';
import { IntlProvider } from 'react-intl';
import messages from '../../../i18n/messages';


const renderComponent = (component, initialState={})=> {

    const store = createStore(() => initialState);
    store.dispatch = jest.fn();

    return {history, ...render(
        <Provider store={store}>
            <IntlProvider locale="en" messages={messages['en']}>
                    {component}
            </IntlProvider>
        </Provider>
    )};
}


test('buy - success', () => {
    const bookId = 0;
    const movieSessionId=2;
    const initialState = {books: {ticket: bookId}};
    const buySpy = jest.spyOn(actions, 'buyTickets').mockImplementation(
        (_tickets, _creditcard, _movieSessionId, onSuccess, _onErrors) => 
            onSuccess());

    const {getByLabelText, getByRole} = renderComponent(
        <BuyForm sessionId = {movieSessionId}>
            <PurchaseCompleted ticket = {bookId}/>
        </BuyForm>,
        initialState);
    const ticketsInput = getByLabelText("Seats")
    const creditCardInput = getByLabelText('Credit card');
    const buyButton = getByRole('button');
    const seats = 5;
    const creditcard = "1234567890123456"; 

    fireEvent.change(ticketsInput, {target: {value: seats}});
    fireEvent.change(creditCardInput, {target: {value: creditcard}});

    expect(Number(ticketsInput.value)).toEqual(seats);
    expect(creditCardInput.value).toEqual(creditcard);

    fireEvent.click(buyButton);
    
    expect(Number(buySpy.mock.calls[0][0])).toEqual(seats);
    expect(buySpy.mock.calls[0][1]).toEqual(creditcard);
    expect(buySpy.mock.calls[0][2]).toEqual(movieSessionId);
});



test('buy - error', () => {
    const bookId = 1;
    const movieSessionId=1;
    const initialState = {books: {ticket: 0}};
    const backendError = "Some backend error";

    jest.spyOn(actions, 'buyTickets').mockImplementation(
        (_tickets, _creditcard, _movieSessionId, _onSuccess, onErrors) => 
            onErrors({globalError: backendError}));

    const {getByLabelText, getByRole, container} = renderComponent(
        <BuyForm sessionId = {movieSessionId}>
            <PurchaseCompleted ticket = {bookId}/>
        </BuyForm>,
        initialState);
    
    const ticketsInput = getByLabelText("Seats")
    const creditCardInput = getByLabelText('Credit card');
    const buyButton = getByRole('button');
    const seats = 5;
    const creditcard = "1234567890123456"; 

    fireEvent.change(ticketsInput, {target: {value: seats}});
    fireEvent.change(creditCardInput, {target: {value: creditcard}});
    
    expect(Number(ticketsInput.value)).toEqual(seats);
    expect(creditCardInput.value).toEqual(creditcard);
    fireEvent.click(buyButton);

    expect(container).toHaveTextContent(backendError);
    
});
