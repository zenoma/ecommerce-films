import React from "react";
import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import * as actions from "../actions";
import common from "../../common";

import {FormattedMessage} from 'react-intl';

const PurchasedCompleted = () =>{
    const ticket = useSelector(selectors.getTicket);

    return (

         <div className="container">
         <Link to={`/`}>{'Volver a Cartelera'}</Link>
         
            <div className="row">
                <div className="col-12 col-md-6">
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.id'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {ticket.id}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.creditCard'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {ticket.creditCard}
                    </div>
                    <div className="info mb-2">
                        <FormattedMessage id='project.books.fields.seats'>
                            {message => (<strong>{message}: </strong>)}
                        </FormattedMessage>
                        {ticket.seats}
                    </div>
                </div>
            </div>
        </div>

    );

}

export default PurchasedCompleted;