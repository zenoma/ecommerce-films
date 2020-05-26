import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import BackLink from '../../common/components/BackLink';
import {Errors} from '../../common';


const MovieDetails = () =>{

    const dispatch = useDispatch();
    const {id} = useParams();
    const movie = useSelector(selectors.getMovie);
    const [backendErrors, setBackendErrors] = useState(null);

    useEffect(() => {

        const movieId = Number(id);

        if (!Number.isNaN(movieId)) {
            dispatch(actions.getMovieById(movieId,
                errors=> setBackendErrors(errors)));
        }

        return () => dispatch(actions.clearMovieDetails());

    }, [id, dispatch]);


    if (!movie) {
        return <div>
            <BackLink/>
            <Errors errors={backendErrors}
            onClose={() => setBackendErrors(null)}/>
        </div>;
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