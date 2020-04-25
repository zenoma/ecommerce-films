import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
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

        // return () => dispatch(actions.clearProduct());

    }, [id, dispatch]);


    if (!movie) {
        return null;
    }

    return (

        <div>
            <BackLink/>
            <h1 >
                {movie.title}
            </h1>
            <div>
                {movie.duration}
            </div>
            <div>
                {movie.synopsis}
            </div>
        </div>

    )

}

export default MovieDetails;