package com.capgemini.movies.MyMovieDatabase.repository;


import com.capgemini.movies.MyMovieDatabase.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
