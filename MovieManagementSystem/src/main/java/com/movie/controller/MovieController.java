package com.movie.controller;

import com.movie.model.Movie;
import com.movie.service.MovieService;
import com.movie.service.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {
    private MovieService movieService = new MovieServiceImpl();
    
    // Handler for listing all movies
    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "list-movies";
    }
    
    // Handler for showing new movie form
    @GetMapping("/movies/new")
    public String showForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }
    
    // Handler for saving a new movie
    @PostMapping("/movies")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }
    
    // Handler for showing edit form
    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "edit-movie";
    }
    
    // Handler for updating a movie
    @PostMapping("/movies/update")
    public String updateMovie(@ModelAttribute("movie") Movie movie) {
        movieService.updateMovie(movie);
        return "redirect:/movies";
    }
    
    // Handler for deleting a movie
    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
    
    // Home page redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/movies";
    }
}