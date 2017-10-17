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


/**
 * This
 */
@RestController
@RequestMapping("/api/movies/")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    /**
     * In this method, a movie is added to the database.
     * @param movie: a movie from the browser, that needs to be added to the database, with an assigned ID, a title
     *             and a boolean indicating whether the movie was watched or not.
     */
    @RequestMapping(value="", method= RequestMethod.POST)
    public void add(@Valid @RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * This method returns all the items in the database.
     * @return an iterable containing the movies and their properties in the database.
     */
    @RequestMapping(value="",method = RequestMethod.GET)
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    /**
     * This method deletes a selected movie from the database, chosen by the user.
     * @param id: the assigned ID of the movie that needs to be deleted.
     */
    @RequestMapping(value="{id}/", method= RequestMethod.DELETE)
    public void del(@PathVariable long id) {
        movieRepository.delete(id);
    }

    /**
     * This method gets the information of a specific movie, chosen by the user.
     * @param id: the assigned ID of the movie.
     * @return the information of the movie matching the given ID.
     */
    @RequestMapping(value="{id}/", method= RequestMethod.GET)
    public Movie get(@PathVariable long id) {
        return movieRepository.findOne(id);
    }

    /**
     * This method saves the updated information for a movie with an ID given by the user in the database.
     * @param movie: The properties of the movie that needs to be updated.
     */
    @RequestMapping(value="", method=RequestMethod.PUT)
    public void save(@Valid @RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * This method handles the validation of the title of the movie. This needs to be at least one character
     * long. This method loops through all the errors arising when trying to add or edit a movie and gets the
     * messages of those errors.
     * @param ex: the MethodArgumentNotValidException arising when the input is not valid.
     * @return the messages belonging to the errors that occured.
     */
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
