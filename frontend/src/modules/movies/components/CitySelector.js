import React from 'react';
import {useSelector} from 'react-redux';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const CitySelector = (selectProps) => {
    const cities = useSelector(selectors.getCities);

    return(
        <select {...selectProps}>
            <FormattedMessage id='project.movies.CitySelector.selectCities'>
                {message => (<option value="" >{message}</option>)}
            </FormattedMessage>
            {cities && cities.map(city =>
                <option key={city.id} value={city.id}>{city.name}</option>
            )}
        </select>
    );
}

CitySelector.propTypes = {
    selectProps: PropTypes.object
};

export default CitySelector;