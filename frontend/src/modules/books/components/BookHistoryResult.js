import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Books from './Books';



const BookHistoryResult = () =>{
    const bookHistory = useSelector(selectors.getBookHistory);
    const dispatch = useDispatch();


    if (!bookHistory){
        return null;
    }

    if (bookHistory.result.items.length === 0){
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.books.FindBookHistory.noBooks'/>
            </div>
        );
    }
    return (

        <div>
            <Books books={bookHistory.result.items}/>
            <Pager 
                back={{
                    enabled: bookHistory.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindBooksResultPage(bookHistory.criteria))}}
                next={{
                    enabled: bookHistory.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindBooksResultPage(bookHistory.criteria))}}/>
        </div>

    );
}

export default BookHistoryResult