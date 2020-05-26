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

export const getMovieById = (movieId, onSuccess, onErrors) => {
    appFetch(`/movie/${movieId}`, config('GET'), onSuccess, onErrors);
}

export const getMovieSession = (movieSessionId, onSuccess, onErrors) => {
    appFetch(`/movie/movieSession/${movieSessionId}`, config('GET'), 
    onSuccess, 
    onErrors);
}