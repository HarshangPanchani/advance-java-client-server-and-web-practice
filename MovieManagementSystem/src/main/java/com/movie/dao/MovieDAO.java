package com.movie.dao;

import com.movie.model.Movie;
import java.util.List;

public interface MovieDAO {
    void saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}