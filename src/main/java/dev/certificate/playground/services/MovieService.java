package dev.certificate.playground.services;


import dev.certificate.playground.models.Movie;
import dev.certificate.playground.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {

       return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with id="+id+" couldn't be found");
        return movie.get();
    }

    public Movie addMovie(Movie movie) {
        movie.setCreatedAt(LocalDateTime.now());
        movie.setModifiedAt(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie) {
        Optional<Movie> movieToBeUpdated = movieRepository.findById(movie.getId());
        if(movieToBeUpdated.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie Doesn't exist");
      return movieRepository.save(Movie.update(movie,movieToBeUpdated.get()));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
