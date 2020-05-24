import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const BookHistory = () => {
    const dispatch = useDispatch();
    const history = useHistory();
    

    useEffect(() => {
        dispatch(actions.findBookHistory({page:0}));
        history.push('/users/book-history-result');
    });

    return null;
}


export default BookHistory;