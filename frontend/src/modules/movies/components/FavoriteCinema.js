import React from "react";
import PropTypes from 'prop-types';

import {FormattedMessage} from 'react-intl';

import { FaRegStar } from 'react-icons/fa';
import './styles/Favorite.css';

const FavoriteCinema = (props) => {
    let cinema=Number(localStorage.getItem('cinema'));

    const setFavorite = event => {
        event.preventDefault();

        localStorage.setItem('city', props.city);
        localStorage.setItem('cinema', props.cinema);
    }

    return (
        <div className="content">
            {props.cinema!==0 && cinema!==props.cinema && 
                <div className="favorite" onClick={(e) => setFavorite(e)}>
                    <span>
                        <FaRegStar />
                    </span>
                    <span>
                        <FormattedMessage id='project.movies.favorite'>
                            {message => (<strong>{message}</strong>)}
                        </FormattedMessage>
                    </span>
                </div>
            }
        </div>
    )
}

FavoriteCinema.propTypes = {
    city: PropTypes.number,
    cinema: PropTypes.number
};

export default FavoriteCinema;