-- Database Creation (if not exists)
CREATE DATABASE IF NOT EXISTS movie_db;
USE movie_db;

-- Table Creation
CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    year INT NOT NULL
);

-- Sample Data
INSERT INTO movies (title, director, year) VALUES 
    ('The Shawshank Redemption', 'Frank Darabont', 1994),
    ('The Godfather', 'Francis Ford Coppola', 1972),
    ('The Dark Knight', 'Christopher Nolan', 2008),
    ('Pulp Fiction', 'Quentin Tarantino', 1994); 