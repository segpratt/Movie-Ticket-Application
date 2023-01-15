
package course.ensf607.assignment6.movie;

import course.ensf607.assignment6.theatre.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService, TheatreService theatreService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> registerNewMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added.");
    }

}
