package com.sammycode.movie_api_intigration.service;

import com.sammycode.movie_api_intigration.model.Body;
import com.sammycode.movie_api_intigration.model.Details;
import com.sammycode.movie_api_intigration.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MoviesService {

    public Movie buildMovieWithDetails(String uri,Movie movie){
        String Baseurl = uri+movie.getName();
        Movie newMovie = new Movie();
        newMovie.setId(movie.getId());
        newMovie.setCreatedAt(movie.getCreatedAt());
        newMovie.setName(movie.getName());

        Details details = new Details();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<Body[]> responseEntity = restTemp.getForEntity(Baseurl, Body[].class);
        details.setSource(Baseurl);
        details.setBody(responseEntity.getBody());
        newMovie.setDetails(details);
        return newMovie;

    }

}
