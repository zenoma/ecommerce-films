import React from "react";
import {useSelector} from "react-redux";
import * as selectors from "../selectors";
import Movies from "./Movies";

const Listing = () =>{

    const listingItems = useSelector(selectors.getListing);

    return (
        <Movies movies={listingItems}/>
    );
};

export default Listing;