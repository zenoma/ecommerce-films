import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import Success from '../../common/components/Success';

import * as actions from '../actions';
import BackLink from '../../common/components/BackLink';


const DeliverTicket = () =>{
    const dispatch = useDispatch();
    const [bookId, setBookId] = useState('');
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);

    const [ticketDelivered, setTicketDelivered] = useState(false);

    let form;

    const autoCloseAlert=()=>{
        setTimeout(() => {
            if(backendErrors){
                setBackendErrors(null);
            }
          }, 5000);
    }

    const onSuccessAlert=()=>{
        setTimeout(() => {
            setTicketDelivered(false);
          }, 5000);
    }

    const handleSubmit = event => {
       
        event.preventDefault();
        if (form.checkValidity()) {
            dispatch(actions.deliverTicket(bookId, creditCard.trim(),
                () => {
                    setTicketDelivered(true);
                    setBookId('');
                    setCreditCard('');
                    setBackendErrors(null);
                },
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (
        <div>
            {ticketDelivered &&
                <Success message='project.books.DeliverTicket.delivered' onLoad={onSuccessAlert()}/>
            }
            <BackLink/>
            <h1 className="mb-5 text-center">
                <FormattedMessage id='project.books.DeliverTicket.title'>
                    {message => (<strong>{message}</strong>)}
                </FormattedMessage>
            </h1>
            <Errors errors={backendErrors} onLoad={autoCloseAlert()}
                    onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node}
                className="needs-validation" noValidate 
                onSubmit={(e) => handleSubmit(e)}>
                <div className="form-group row">
                    <label htmlFor="bookId" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.books.fields.id"/>
                    </label>
                    <div className="col-md-4">
                        <input type="number" id="bookId" className="form-control mr-2"
                            value={bookId}
                            onChange={e => setBookId(Number(e.target.value))}
                            min="1"
                            required/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.global.validator.required'/>
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="creditCard" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.books.fields.creditCard"/>
                    </label>
                    <div className="col-md-4">
                        <input type="text" id="creditCard" className="form-control"
                            value={creditCard}
                            onChange={e => setCreditCard(e.target.value)}
                            required/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.global.validator.required'/>
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <div className="offset-md-3 col-md-1">
                        <button type="submit" className="btn btn-primary">
                            <FormattedMessage id="project.global.buttons.deliver"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    )

}

export default DeliverTicket;