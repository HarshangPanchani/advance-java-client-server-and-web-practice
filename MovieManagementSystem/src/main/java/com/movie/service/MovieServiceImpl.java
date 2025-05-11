package com.movie.service;

import com.movie.dao.MovieDAO;
import com.movie.dao.MovieDAOImpl;
import com.movie.model.Movie;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDAO movieDAO = new MovieDAOImpl();
    
    @Override
    public void saveMovie(Movie movie) {
        movieDAO.saveMovie(movie);
    }
    
    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }
    
    @Override
    public Movie getMovieById(int id) {
        return movieDAO.getMovieById(id);
    }
    
    @Override
    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
    }
    
    @Override
    public void deleteMovie(int id) {
        movieDAO.deleteMovie(id);
    }
}