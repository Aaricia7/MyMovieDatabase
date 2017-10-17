package com.capgemini.movies.MyMovieDatabase.controller;

import com.capgemini.movies.MyMovieDatabase.model.Movie;
import com.capgemini.movies.MyMovieDatabase.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value="", method= RequestMethod.POST)
    public void add(@Valid @RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @RequestMapping(value="",method = RequestMethod.GET)
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    @RequestMapping(value="{id}/", method= RequestMethod.DELETE)
    public void del(@PathVariable long id) {
        movieRepository.delete(id);
    }

    @RequestMapping(value="{id}/", method= RequestMethod.GET)
    public Movie get(@PathVariable long id) {
        return movieRepository.findOne(id);
    }

    @RequestMapping(value="", method=RequestMethod.PUT)
    public void save(@Valid @RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ArrayList<String> errors = new ArrayList<>();
        for (FieldError field : fieldErrors){
            errors.add(field.getDefaultMessage());
        }
        return errors;
    }

}
