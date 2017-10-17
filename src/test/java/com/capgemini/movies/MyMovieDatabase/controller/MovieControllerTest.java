package com.capgemini.movies.MyMovieDatabase.controller;

import com.capgemini.movies.MyMovieDatabase.model.Movie;
import com.capgemini.movies.MyMovieDatabase.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieRepository fakeRepository;

    @Mock
    private Movie fakeMovie;

    @Test
    public void testFindAll() {
        when(fakeRepository.findAll()).thenReturn(new ArrayList<>());
        Iterable<Movie> result = movieController.getAll();
        Assert.assertEquals(0, result.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindOne() {
        when(fakeRepository.findOne(1L)).thenReturn(fakeMovie);
        Movie result = movieController.get(1);
        Assert.assertEquals(fakeMovie, result);
        verify(fakeRepository, times(1)).findOne(1L);
    }

    @Test
    public void testAddGuest() {
        movieController.save(fakeMovie);
        verify(fakeRepository, times(1)).save(fakeMovie);
    }

    @Test
    public void testDeleteGuest() {
        movieController.del(1L);
        verify(fakeRepository, times(1)).delete(1L);
    }

    @Test(expected = Exception.class)
    public void testFindGuestNotFound() {
        when(fakeRepository.findOne(1L)).thenThrow(new Exception());
        movieController.get(1);
    }
}
