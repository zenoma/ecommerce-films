import React from "react";
import {useSelector} from "react-redux";
import * as selectors from "../selectors";
import {FormattedMessage} from 'react-intl';
import Link from 'react-dom';

const PurchasedCompleted = () =>{
    const ticket = useSelector(selectors.getTicket);

    return (

         <div className="container">
         <Link to={`/`}>  
            <FormattedMessage id='project.global.fields.goHome'>
                {message => (<strong>{message}: </strong>)}
            </FormattedMessage>
        </Link>
         
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