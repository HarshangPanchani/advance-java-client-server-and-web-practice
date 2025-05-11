package com.example.exp_5.bean;

import com.example.exp_5.dao.MovieDAO;
import com.example.exp_5.model.Movie;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * JSF Managed Bean for Movie operations
 */
@Named("movieBean")
@SessionScoped
public class MovieBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private MovieDAO movieDAO;
    private List<Movie> movies;
    private Movie selectedMovie;
    private Movie newMovie;
    
    /**
     * Initialize the bean
     */
    @PostConstruct
    public void init() {
        movieDAO = new MovieDAO();
        loadMovies();
        newMovie = new Movie();
        selectedMovie = new Movie();
    }
    
    /**
     * Load all movies from database
     */
    public void loadMovies() {
        movies = movieDAO.getAllMovies();
    }
    
    /**
     * Save a new movie
     * 
     * @return Navigation outcome
     */
    public String saveMovie() {
        try {
            boolean result = movieDAO.addMovie(newMovie);
            if (result) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Movie added successfully"));
                newMovie = new Movie(); // Reset the form
                loadMovies(); // Refresh the list
                return "viewMovies?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add movie"));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }
    
    /**
     * Prepare for movie editing
     * 
     * @param id Movie ID to edit
     * @return Navigation outcome
     */
    public String prepareEdit(int id) {
        selectedMovie = movieDAO.getMovieById(id);
        if (selectedMovie == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Movie not found"));
            return null;
        }
        return "editMovie?faces-redirect=true";
    }
    
    /**
     * Update an existing movie
     * 
     * @return Navigation outcome
     */
    public String updateMovie() {
        try {
            boolean result = movieDAO.updateMovie(selectedMovie);
            if (result) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Movie updated successfully"));
                loadMovies(); // Refresh the list
                return "viewMovies?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to update movie"));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }
    
    /**
     * Delete a movie
     * 
     * @param id Movie ID to delete
     */
    public void deleteMovie(int id) {
        try {
            boolean result = movieDAO.deleteMovie(id);
            if (result) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Movie deleted successfully"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete movie"));
            }
            loadMovies(); // Refresh the list
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    
    // Getters and Setters
    
    public List<Movie> getMovies() {
        return movies;
    }
    
    public Movie getSelectedMovie() {
        return selectedMovie;
    }
    
    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
    
    public Movie getNewMovie() {
        return newMovie;
    }
    
    public void setNewMovie(Movie newMovie) {
        this.newMovie = newMovie;
    }
}