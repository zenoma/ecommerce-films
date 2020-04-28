import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useParams} from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';

import {BackLink} from '../../common';

import {FormattedMessage} from 'react-intl';

import common from '../../common';

const MovieSessionVIew = () =>{
    const dispatch = useDispatch();

    const session = useSelector(selectors.getMovieSession);
    const {id} = useParams();
    let movieSessionId;

    useEffect(() => {
        movieSessionId = Number(id);
        if (!Number.isNaN(movieSessionId)) {
            dispatch(actions.getMovieSession(movieSessionId));
        }
    }, [id, dispatch]);

    if (!session) {
        return null;
    }
    
    return (
        <div className="container">
            <BackLink/>

            <h1 className="mb-5 text-center">{session.movieTitle}</h1>
            <div className="row">
                <div className="col-12 col-md-6">
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.duration'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {session.movieDuration}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.price'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {session.price} €
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.date'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {common.dateUtils.formatDateTime(session.date)}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.cinema'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {session.cinemaName}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.room'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {session.roomName}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.movies.fields.capacity'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                         {session.seats}
                    </div>
                </div>
                <div className="col-12 col-md-6">
                    <BuyForm sessionId = {movieSessionId}>
                </div>
            </div>
        </div>
    );
};

export default MovieSessionVIew;