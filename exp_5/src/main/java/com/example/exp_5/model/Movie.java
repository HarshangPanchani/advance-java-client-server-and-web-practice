package com.example.exp_5.model;

import java.io.Serializable;

/**
 * Movie entity class representing a movie in the database
 */
public class Movie implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String title;
    private String director;
    private int year;
    
    // Default constructor
    public Movie() {
    }
    
    // Constructor with parameters
    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }
    
    // Constructor without ID for new movies
    public Movie(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + '}';
    }
}