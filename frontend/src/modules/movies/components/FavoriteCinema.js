import React, {useState} from "react";
import PropTypes from 'prop-types';

import {FormattedMessage} from 'react-intl';

import { FaRegStar } from 'react-icons/fa';
import './styles/Favorite.css';
import { Success } from "../../common";

const FavoriteCinema = (props) => {
    let cinema=Number(localStorage.getItem('cinema'));
    const [isChanged, setIsChanged] = useState(false);

    const setFavorite = event => {
        event.preventDefault();
        setIsChanged(false);
        localStorage.setItem('city', props.city);
        localStorage.setItem('cinema', props.cinema);
        setIsChanged(true);
    }

    return (
        <div className="content">
            {isChanged && <Success message = 'project.movies.FavoriteCinema.selected'/>}
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