const getModuleState = state => state.books;

export const getTicket = state => getModuleState(state).ticket;