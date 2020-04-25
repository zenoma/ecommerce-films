import {config, appFetch} from './appFetch';

export const getListing = (cinemaId, date, onSuccess) => {
    appFetch(`/movie/listing?cinemaId=${cinemaId}&date=${date}`, config('GET'), onSuccess);
}

export const getCities = (onSuccess) => {
    appFetch(`/movie/cities`, config('GET'), onSuccess);
}

export const getCinemas = (cityId, onSuccess) => {
    appFetch(`/movie/cinemas?cityId=${cityId}`, config('GET'), onSuccess);
}

export const getMovieSession = (movieSessionId, onSuccess) => {
    appFetch(`/movie/movieSession/${movieSessionId}`, config('GET'), onSuccess);
}