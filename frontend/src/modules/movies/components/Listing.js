import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import PropTypes from 'prop-types';
import * as selectors from "../selectors";
import * as actions from "../actions";
import FavoriteCinema from "./FavoriteCinema";
import DateSelector from "./DateSelector";
import CitySelector from "./CitySelector";
import CinemaSelector from "./CinemaSelector";
import Movies from "./Movies";
import common from "../../common";

import {FormattedMessage} from 'react-intl';

const Listing = (props) =>{
    const dispatch = useDispatch();
    const listingDate = useSelector(selectors.getListingDate);
    const listingItems = useSelector(selectors.getListing);
    const [selectedCity, setSelectedCity] = useState(0);
    const [selectedCinema, setSelectedCinema] = useState(0);

    const setCity = cityId => {
        setSelectedCity(Number(cityId));
        setCinema(0)
        dispatch(actions.getCinemas(cityId));
    }

    const setCinema = cinemaId => {
        setSelectedCinema(Number(cinemaId));
        if (Number(cinemaId) !== 0) {
            dispatch(actions.getListing(cinemaId, listingDate));
        }
    }

    if(!selectedCity && props.city){
        setCity(props.city)
    }

    if(!selectedCinema && props.cinema){
        setCinema(props.cinema)
    }


    return (
        <div className="container">
            <FormattedMessage id='project.movies.title'>
                {message => (
                    <h1 className="mb-5 text-center">{message}</h1>
                )}
            </FormattedMessage>
            <div className="row">
                <div className="col-12 col-lg-3">
                    <FavoriteCinema
                        cinema={Number(selectedCinema)}
                        city={Number(selectedCity)}
                    />
                    <FormattedMessage id='project.movies.CitySelector.selectCities'>
                        {message => (<label><strong>
                                {message}
                            </strong></label>)}
                    </FormattedMessage>
                    <CitySelector id="cities" className="custom-select my-2 mr-sm-2"
                        value={selectedCity}
                        onChange={e => setCity(e.target.value)}
                    />
                    <FormattedMessage id='project.movies.CinemaSelector.selectCinemas'>
                        {message => (<label><strong>
                                {message}
                            </strong></label>)}
                    </FormattedMessage>
                    <CinemaSelector id="cinemas" className="custom-select my-2 mr-sm-2"
                        value={selectedCinema}
                        onChange={e => setCinema(e.target.value)}
                    />
                    <FormattedMessage id='project.movies.DateSelector.selectDate'>
                        {message => (<label><strong>
                                {message}
                            </strong></label>)}
                    </FormattedMessage>
                    <DateSelector id="listingDate" className="custom-select my-2 mr-sm-2"
                        value={listingDate!=null ? listingDate : common.dateUtils.formatDate(new Date())}
                        onChange={e => dispatch(actions.getListing(selectedCinema, e.target.value))}
                    />
                </div>
                <div className="col-12 col-lg-9">
                    <Movies movies={listingItems}/>
                </div>
            </div>
        </div>
    );
};


Listing.propTypes = {
    city: PropTypes.number,
    cinema: PropTypes.number,
};


export default Listing;