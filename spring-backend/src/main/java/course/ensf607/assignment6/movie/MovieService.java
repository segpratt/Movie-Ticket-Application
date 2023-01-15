
package course.ensf607.assignment6.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public String addMovie(Movie movie) {
        Optional<Movie> movieByName = movieRepository.findByName(movie.getName());
        if (movieByName.isPresent()) {
            return "Movie already exist!";
        }
        movieRepository.save(movie);
        return "Movie added.";
    }

    public String removeMovie(Movie movie) {
        Optional<Movie> movieByName = movieRepository.findByName(movie.getName());
        if (!movieByName.isPresent()) {
            return "Movie already exist!";
        }
        movieRepository.deleteById(movieByName.get().getmId());
        return "Movie removed.";
    }

    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie getMovieById(Long movieId) {
        Optional<Movie> movieById = movieRepository.findById(movieId);
        if (!movieById.isPresent()) {
            throw new IllegalStateException("Showtime doesn't exist!");
        }
        return movieById.get();
    }

    public Movie getMovieByName(Set<Movie> movie, String name) {
        boolean value = false;
        Movie moviefinal = null;
        Iterator<Movie> other = movie.iterator();
        while (other.hasNext()) {
            Movie mov = other.next();
            if (mov.getName().equals(name)) {
                value = true;
                moviefinal = mov;
                break;
            }
        }
        if (value == false) {
            throw new IllegalStateException("No Movie with that name");
        }

        return moviefinal;

    }
}
