import {config, appFetch} from './appFetch';

export const getListing = (cinemaId, date, onSuccess) => {
    appFetch(`/movie/listing?cinemaId=${cinemaId}&date=${date}`, config('GET'), onSuccess);

}