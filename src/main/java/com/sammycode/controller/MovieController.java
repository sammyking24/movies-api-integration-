package com.sammycode.controller;

import com.sammycode.movie_api_intigration.model.Movie;
import com.sammycode.movie_api_intigration.model.ResourceNotFoundException;
import com.sammycode.movie_api_intigration.repository.MovieRepository;
import com.sammycode.movie_api_intigration.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MoviesService service;
    @Autowired
    MovieRepository movieRepo;

    @Value("${serviceURL}")
    String serviceURL;

//    GET ALL MOVIES
    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }
//    CREATE NEW MOVIE
    @PostMapping("/movies")
    public  Movie createNewMove(@Validated @RequestBody Movie movie){
        return movieRepo.save(movie);

    }
//    get a single ,moviw by Id
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Long id){
        Movie newMovie = new Movie();
        newMovie = movieRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie","Movie Id",id));
        return service.buildMovieWithDetails(serviceURL,newMovie);
    }

//    get a single Movie by Name
    @GetMapping(value = "/movies", params = "name", produces = "application/json")
    public Movie getMovieByName(@RequestParam(value = "name")String name) throws IOException{
        return service.buildMovieWithDetails(serviceURL,getAllMovies()
                .stream()
                .filter(movie -> movie.getName()
                        .equals(name))
                .findFirst().get());
    }

//    UPDATE MOVIE
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable(value = "id") Long id, @Validated @RequestBody Movie movie){
        Movie updateMovie = movieRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie","Movie Id", id));
        updateMovie.setName(movie.getName());
        return movieRepo.save(updateMovie);
    }

//    Delete Movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable (value = "id") Long id){
        Movie movie = movieRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Movie","Movie Id",id));
        movieRepo.delete(movie);
        return ResponseEntity.ok().build();
    }
}
