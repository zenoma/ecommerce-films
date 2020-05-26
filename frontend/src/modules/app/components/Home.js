import React from 'react';
import {Listing} from '../../movies';

const Home = () => <Listing city = {Number(localStorage.getItem('city'))} cinema = {Number(localStorage.getItem('cinema'))}/>;

export default Home;
