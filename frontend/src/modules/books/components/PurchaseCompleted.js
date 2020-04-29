import React from "react";
import {useSelector} from "react-redux";
import * as selectors from "../selectors";
import {FormattedMessage} from 'react-intl';
import Link from 'react-dom';
import PropTypes from 'prop-types';

const PurchaseCompleted = (selectProps) =>{

    return (

        <div className="container">
            <div className="row">
                <div className="col-12 col-md-6">
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.id'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {selectProps.ticket.id}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.creditCard'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {selectProps.ticket.creditCard}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.seats'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {selectProps.ticket.seats}
                    </div>
                     <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.confirmationMessage'>
                            {message => (<p>{message}.</p>)}
                        </FormattedMessage>
                    </div>
                </div>
            </div>
        </div>

    );

}

PurchaseCompleted.propTypes = {
    selectProps: PropTypes.object
};


export default PurchaseCompleted;