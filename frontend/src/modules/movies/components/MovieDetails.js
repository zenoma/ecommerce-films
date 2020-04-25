import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import BackLink from '../../common/components/BackLink';


const MovieDetails = () =>{

    const dispatch = useDispatch();
    const {id} = useParams();
    const movie = useSelector(selectors.getMovie);

    useEffect(() => {

        const movieId = Number(id);

        if (!Number.isNaN(movieId)) {
            dispatch(actions.getMovieById(movieId));
        }

        return () => dispatch(actions.clearMovieDetails());

    }, [id, dispatch]);


    if (!movie) {
        return null;
    }

    return (

        <div>
            <BackLink/>
            <h1 className="mb-5 text-center">
                {movie.title}
            </h1>
            <div className="info mb-2">
                <FormattedMessage id='project.movies.fields.duration'>
                    {message => (<strong>{message}: </strong>)}
                </FormattedMessage>
            {movie.duration}
            </div>
            <div className="info">
                <FormattedMessage id='project.movies.fields.synopsis'>
                    {message => (<strong>{message}: </strong>)}
                </FormattedMessage>
            </div>
            <div>
                {movie.synopsis}
            </div>
        </div>

    )

}

export default MovieDetails;