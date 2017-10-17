package com.capgemini.movies.MyMovieDatabase.controller;

import com.capgemini.movies.MyMovieDatabase.model.Movie;
import com.capgemini.movies.MyMovieDatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value="", method= RequestMethod.POST)
    public void add(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @RequestMapping(value="",method = RequestMethod.GET)
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

}
