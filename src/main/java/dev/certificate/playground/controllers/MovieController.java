package dev.certificate.playground.controllers;

import dev.certificate.playground.config.ApiSuccessPayLoad;
import dev.certificate.playground.models.Movie;
import dev.certificate.playground.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/movie-svc")
public class MovieController {

    Logger logger= LoggerFactory.getLogger(MovieController.class);

    MovieService movieService;
    MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    ResponseEntity<ApiSuccessPayLoad> getAllMovies(){

       List<Movie> movies= movieService.getAllMovies();
       return new ResponseEntity<>(new ApiSuccessPayLoad(movies), HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    ResponseEntity<ApiSuccessPayLoad> getMovieById(@PathVariable Long id){
        Movie movie= movieService.getMovieById(id);
        return new ResponseEntity<>(new ApiSuccessPayLoad(movie), HttpStatus.OK);
    }

    @PostMapping("/movie")
    ResponseEntity<ApiSuccessPayLoad> addMovie(@Valid @RequestBody Movie movie){

        Movie addedMovie=movieService.addMovie(movie);
        logger.info(addedMovie.getCreatedAt().toString());
        return new ResponseEntity<>(new ApiSuccessPayLoad(addedMovie),HttpStatus.CREATED);
    }

    @PutMapping("/movie")
    ResponseEntity<ApiSuccessPayLoad> updateMovie(@RequestBody Movie movie){

        Movie updatedMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(new ApiSuccessPayLoad(updatedMovie),HttpStatus.CREATED);
    }

    @DeleteMapping("/movie/{id}")
    ResponseEntity<ApiSuccessPayLoad> deleteMovie(@PathVariable Long id){

        movieService.deleteMovie(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
