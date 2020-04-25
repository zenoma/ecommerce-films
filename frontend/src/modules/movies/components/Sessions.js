import React from 'react';
import PropTypes from 'prop-types';

const Sessions = (movieSessions) => (
        <span>
            {movieSessions.sessions.map(session => 
                <span className="badge badge-primary mr-2" key = {session.id}> 
                    {session.hour} {/*Cambiar por link*/}
                </span>
            )}
        </span>
)


Sessions.propTypes = {
    movieSessions: PropTypes.array
};

export default Sessions;