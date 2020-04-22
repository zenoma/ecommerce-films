import React from "react";
import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import * as actions from "../actions";
import DateSelector from "./DateSelector";
import CitySelector from "./CitySelector";
import Movies from "./Movies";
import common from "../../common";

const Listing = () =>{
    const dispatch = useDispatch();
    const listingDate = useSelector(selectors.getListingDate);
    const listingItems = useSelector(selectors.getListing);
    
    return (
        <div id="content">
            <DateSelector id="listingDate" className="custom-select my-2 mr-sm-2"
                value={listingDate!=null ? listingDate : common.dateUtils.formatDate(new Date())}
                onChange={e => dispatch(actions.getListing(1, e.target.value))}
            />
            <CitySelector id="cities" className="custom-select my-2 mr-sm-2"
                onChange={e => console.log("Cargar Cinemas de la City seleccionada")}
            />
            <Movies movies={listingItems}/>
        </div>
    );
};

export default Listing;