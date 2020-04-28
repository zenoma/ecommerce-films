import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import books from '../../books';
import MovieDetails from '../../movies/components/MovieDetails';

import MovieSessionView from '../../movies/components/MovieSessionView';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const ticket = useSelector(books.selectors.getTicket);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/movie/:id"><MovieDetails/></Route>
                <Route exact path="/movie/movie-session/:id"><MovieSessionView/></Route>
                {loggedIn && ticket && <Route exact path="/books/purchase-completed"><PurchaseCompleted/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
