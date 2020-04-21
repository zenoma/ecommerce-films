import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';
import Sessions from './Sessions';

const Movies = (listingItems) => {

    let contentBody =
        <tr>
            <td colSpan = '2'>
                <FormattedMessage id='project.global.fields.listingItemsEmpty'/>
            </td>
        </tr>

    if (listingItems.movies != null){
        contentBody = 
            listingItems.movies.map(movie => 
                <tr key={movie.movieId}>
                    <td>{movie.movieTitle}</td>
                    <td><Sessions sessions={movie.movieSessions}/> </td>
                </tr>
            )
    };

    return (
        <table className="table table-striped table-hover">

            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.movieTitle'/>
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.movieSessions'/>
                    </th>
                </tr>
            </thead>

            <tbody>{contentBody}</tbody>

        </table>
    );
}


Movies.propTypes = {
    listingItems: PropTypes.array
};

export default Movies;