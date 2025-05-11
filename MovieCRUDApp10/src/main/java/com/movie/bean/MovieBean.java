package com.movie.bean;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean implements Serializable {
    
    private Movie movie;
    private List<Movie> movies;
    private MovieDAO movieDAO;
    
    public MovieBean() {
        movieDAO = new MovieDAO();
        movie = new Movie(0, "", "", 0); // Default movie object
    }
    
    @PostConstruct
    public void init() {
        loadMovies();
    }
    
    public void loadMovies() {
        movies = movieDAO.getAllMovies();
    }
    
    public String addMovie() {
        movieDAO.addMovie(movie);
        movie = new Movie(0, "", "", 0); // Reset form
        loadMovies();
        return "index?faces-redirect=true";
    }
    
    public String prepareEdit(Movie movie) {
        this.movie = movie;
        return "edit?faces-redirect=true";
    }
    
    public String updateMovie() {
        movieDAO.updateMovie(movie);
        movie = new Movie(0, "", "", 0); // Reset form
        loadMovies();
        return "index?faces-redirect=true";
    }
    
    public String deleteMovie(int id) {
        movieDAO.deleteMovie(id);
        loadMovies();
        return "index?faces-redirect=true";
    }
    
    public String navigateToAdd() {
        movie = new Movie(0, "", "", 0); // Reset form
        return "add?faces-redirect=true";
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public List<Movie> getMovies() {
        return movies;
    }
    
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
} 