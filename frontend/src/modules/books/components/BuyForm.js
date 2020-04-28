import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';
import selectors from '../../users';

const BuyForm = (sessionId) => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [seats, setSeats] = useState('');
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.buyTickets(seats, creditCard.trim(), sessionId,
                () => history.push('/books/purchase-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div>
            {loggedIn && 
                <div>
                    <Errors errors={backendErrors}
                        onClose={() => setBackendErrors(null)}/>
                    <div className="card bg-light border-dark">
                        <h5 className="card-header">
                            <FormattedMessage id="project.books.BuyForm.title"/>
                        </h5>
                        <div className="card-body">
                            <form ref={node => form = node}
                                className="needs-validation" noValidate 
                                onSubmit={(e) => handleSubmit(e)}>
                                <div className="form-group row">
                                    <label htmlFor="seats" className="col-md-3 col-form-label">
                                        <FormattedMessage id="project.books.fields.seats"/>
                                    </label>
                                    <div className="col-md-4">
                                        <input type="number" id="seats" className="form-control mr-2"
                                            value={seats}
                                            onChange={e => setSeats(Number(e.target.value))}
                                            min="1"
                                            max="10"
                                            required/>
                                        <button type="submit" className="btn btn-primary">
                                            <FormattedMessage id="project.global.buttons.save"/>
                                        </button>
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
                                            <FormattedMessage id="project.global.buttons.buy"/>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>    
            }
        </div>
    );

}

BuyForm.propTypes = {
    sessionId: PropTypes.number
};


export default BuyForm;
