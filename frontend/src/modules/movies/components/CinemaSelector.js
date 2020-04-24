import React from 'react';
import {useSelector} from 'react-redux';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const CinemaSelector = (selectProps) => {
    const cinemas = useSelector(selectors.getCinemas);

    return (
        <select {...selectProps}>
            <FormattedMessage id='project.movies.CinemaSelector.selectCinemas'>
                {message => (<option value = "">{message}</option>)}
            </FormattedMessage>
            {cinemas && cinemas.map(cinema =>
                <option key={cinema.id} value={cinema.id}>{cinema.name}</option>
            )}
        </select>
    );
}

CinemaSelector.propTypes = {
    selectProps: PropTypes.object
};

export default CinemaSelector;