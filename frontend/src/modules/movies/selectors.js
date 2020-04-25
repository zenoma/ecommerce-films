const getModuleState = state => state.movies;

export const getListing = state => getModuleState(state).movies;

export const getListingDate = state => getModuleState(state).listingDate;

export const getCities = state => getModuleState(state).cities;

export const getCinemas = state => getModuleState(state).cinemas;

export const getSelectedCinema = state => getModuleState(state).selectedCinema;

export const getMovie = state => getModuleState(state).movie;

export const getMovieSession = state => getModuleState(state).movieSession;
