package course.ensf607.assignment6.theatre;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.movie.MovieService;
import course.ensf607.assignment6.seat.Seat;
import course.ensf607.assignment6.showtime.Showtime;
import course.ensf607.assignment6.showtime.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/theatres")
public class TheatreController {

    private final TheatreService theatreService;
    private final ShowtimeService showtimeService;
    private final MovieService movieService;

    @Autowired
    public TheatreController(TheatreService theatreService,
            ShowtimeService showtimeService, MovieService movieService) {
        this.movieService = movieService;
        this.theatreService = theatreService;
        this.showtimeService = showtimeService;
    }

    @GetMapping()
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }

    @PostMapping("/addTheatre")
    public void addTheatre(@RequestBody Theatre theatre) {
        theatreService.addTheatre(theatre);
    }

    @GetMapping("/{tId}/movies")
    public Set<Movie> getMovieBasedOnTheatre(@PathVariable Long tId) {
        Theatre theatre = theatreService.getTheatreById(tId);
        Set<Showtime> showtime = theatre.getShowtimes();
        Set<Movie> movies = showtimeService.getMoviesBasedOnShowtime(showtime);
        return movies;
    }

    @GetMapping("/{tId}/movies/{name}")
    public Movie getMovieBasedOnTheatreandName(@PathVariable Long tId,
            @PathVariable String name) {
        Theatre theatre = theatreService.getTheatreById(tId);
        Set<Showtime> showtime = theatre.getShowtimes();
        Set<Movie> movies = showtimeService.getMoviesBasedOnShowtime(showtime);
        Movie movie = movieService.getMovieByName(movies, name);
        return movie;
    }

    @GetMapping("/{tId}/showtimes/{mId}")
    public Set<Showtime> getShowtimesBasedOnTheatreAndMovie(@PathVariable Long tId, @PathVariable Long mId) {
        Theatre theatre = theatreService.getTheatreById(tId);
        Set<Showtime> showtime = theatre.getShowtimes();
        Set<Showtime> shows = showtimeService.getShowtimesBasedonMovie(showtime, mId);
        return shows;
    }

    @GetMapping("/{stId}/seats")
    public Set<Seat> getSeatsBasedOnShowtime(@PathVariable Long stId) {
        Set<Seat> seats = showtimeService.getShowtimeById(stId).getSeats();
        return seats;
    }

}
