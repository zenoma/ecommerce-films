const getModuleState = state => state.movies;

export const getListing = state => getModuleState(state).movies;

export const getListingDate = state => getModuleState(state).listingDate;