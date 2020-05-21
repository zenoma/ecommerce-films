import React from 'react';
import PropTypes from 'prop-types';
import {useIntl} from 'react-intl';

const Success = ({message}) => {
    const intl = useIntl();

    return (
        <div className="alert alert-success alert-dismissible fade show" role="alert">
            {intl.formatMessage({id: message})}
            <button type="button" className="close" data-dismiss="alert" aria-label="Close" >
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    );
}

Success.propTypes = {
    message: PropTypes.string
};

export default Success;