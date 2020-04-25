import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';
import Sessions from './Sessions';
import {Link} from 'react-router-dom';

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
                <tr className="d-flex" key={movie.movieId}>
                    <td className="col-6"><Link to= {`/movie/${movie.movieId}`}>{movie.movieTitle}</Link></td>
                    <td className="col-6"><Sessions sessions={movie.movieSessions}/> </td>
                </tr>
            )
    };

    return (
        <table className="table table-striped table-hover">

            <thead>
                <tr className="d-flex">
                    <th className="col-6">
                        <FormattedMessage id='project.global.fields.movieTitle'/>
                    </th>
                    <th className="col-6">
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