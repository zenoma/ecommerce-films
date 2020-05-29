import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

const Books = ({books}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.movieTitle'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.purchaseSeats'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.totalPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.sessionDate'/>
                </th>
            </tr>
        </thead>

        <tbody>
            {books.map(book => 
                <tr key={book.id}>
                    <td>
                        <FormattedDate value={new Date(book.dateBook)}/> - <FormattedTime value={new Date(book.dateBook)}/>
                    </td>
                    <td>
                        {book.movieTitle}
                    </td>
                    <td>
                        {book.tickets}
                    </td>
                    <td>
                        {book.price.toFixed(2)}
                    </td>
                    <td>
                    <FormattedDate value={new Date(book.dateMovieSession)}/> - <FormattedTime value={new Date(book.dateMovieSession)}/>
                    </td>
                </tr>
            )}
        </tbody>

    </table>

);

Books.propTypes = {
    books: PropTypes.array.isRequired
};

export default Books;

