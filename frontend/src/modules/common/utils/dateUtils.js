export const addDaystoDate = (date, days) => {
    let newDate = new Date(date);
    newDate.setDate(date.getDate() + days);
    return newDate;
}

export const formatDate = (date) => {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return `${year}-${month<10?`0${month}`:`${month}`}-${day<10?`0${day}`:`${day}`}`;
}

export const getArrayWithNextDays = (numberOfDays) => {
    const dates = [];
    const currentDate = new Date();
    
    for(let i=0;i<numberOfDays;i++){
        dates[i] = addDaystoDate(currentDate, i);
    }

    return dates;
}