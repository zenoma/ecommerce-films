import React from 'react';
import PropTypes from 'prop-types';

const Sessions = (movieSessions) => (
        <span>
            {movieSessions.sessions.map(session => 
                <div key = {session.id}> 
                    {session.hour} {/*Cambiar por link*/}
                </div>
            )}
        </span>
)


Sessions.propTypes = {
    movieSessions: PropTypes.array
};

export default Sessions;