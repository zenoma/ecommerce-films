import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';

import {MovieDetails,MovieSessionView} from '../../movies';
import {DeliverTicket,BookHistory, BookHistoryResult} from '../../books';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const role = useSelector(users.selectors.getRole);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/movie/:id"><MovieDetails/></Route>
                <Route exact path="/movie/movie-session/:id"><MovieSessionView/></Route>
                {role==="TICKETSELLER" && <Route exact path="/books/deliver"><DeliverTicket/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                {role==="USER" && <Route exact path="/users/book-history"><BookHistory/></Route>}
                {role==="USER" && <Route exact path="/users/book-history-result"><BookHistoryResult/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
