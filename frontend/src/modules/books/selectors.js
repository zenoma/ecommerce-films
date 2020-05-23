const getModuleState = state => 
    state.books;

export const getTicket = state => 
    getModuleState(state).ticket;

export const getBookHistory = state => 
    getModuleState(state).bookHistory;