import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';
import './styles/Sessions.css';

const Sessions = (movieSessions) => (
        <span>
            {movieSessions.sessions.map(session => 
                <span className="badge badge-primary mr-2" key = {session.id}> 
                    <Link to={`/movie/movie-session/${session.id}`}>
                        {session.hour.substring(0,5)}
                    </Link>
                </span>
            )}
        </span>
)


Sessions.propTypes = {
    movieSessions: PropTypes.array
};

export default Sessions;