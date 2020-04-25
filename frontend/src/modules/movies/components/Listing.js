import React from "react";
import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import * as actions from "../actions";
import DateSelector from "./DateSelector";
import CitySelector from "./CitySelector";
import CinemaSelector from "./CinemaSelector";
import Movies from "./Movies";
import common from "../../common";

import {FormattedMessage} from 'react-intl';

const Listing = () =>{
    const dispatch = useDispatch();
    const listingDate = useSelector(selectors.getListingDate);
    const listingItems = useSelector(selectors.getListing);
    const selectedCinema = useSelector(selectors.getSelectedCinema);

    return (
        <div className="container">
            <FormattedMessage id='project.movies.title'>
                {message => (<h1 className="mb-5 text-center">
                        {message}
                        </h1>)}
            </FormattedMessage>
            <div className="row">
                <div className="col-12 col-lg-3">
                    <FormattedMessage id='project.movies.CitySelector.selectCities'>
                        {message => (<label><strong>
                                {message}
                            </strong></label>)}
                    </FormattedMessage>
                    <CitySelector id="cities" className="custom-select my-2 mr-sm-2"
                        defaultValue={0}
                        onChange={e => dispatch(actions.getCinemas(e.target.value))}
                    />
                    <FormattedMessage id='project.movies.CinemaSelector.selectCinemas'>
                        {message => (<label><strong>
                                {message}
                            </strong></label>)}
                    </FormattedMessage>
                    <CinemaSelector id="cinemas" className="custom-select my-2 mr-sm-2"
                        defaultValue={0}
                        onChange={e => dispatch(actions.getListing(e.target.value, listingDate))}
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

export default Listing;