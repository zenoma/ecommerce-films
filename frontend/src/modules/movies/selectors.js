const getModuleState = state => state.movies;

export const getListing = state => getModuleState(state).movies;
