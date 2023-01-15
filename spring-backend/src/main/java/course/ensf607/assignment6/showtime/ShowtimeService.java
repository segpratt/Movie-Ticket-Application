package course.ensf607.assignment6.showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.ensf607.assignment6.movie.Movie;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getShowtimes() {
        return showtimeRepository.findAll();
    }

    public void addShowtime(Showtime showtime) {
        Optional<Showtime> showtimeById = showtimeRepository.findById(showtime.getsId());
        // Optional<Showtime> showtimeById =
        // showtimeRepository.findBy(showtime.getShowtime());
        if (showtimeById.isPresent()) {
            throw new IllegalStateException("Showtime already exist!");
        }
        showtimeRepository.save(showtime);
    }

    public void updateShowtime(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    public Showtime getShowtimeById(Long showtimeId) {
        Optional<Showtime> showtimeById = showtimeRepository.findById(showtimeId);
        if (!showtimeById.isPresent()) {
            throw new IllegalStateException("Showtime doesn't exist!");
        }
        return showtimeById.get();
    }

    public Set<Movie> getMoviesBasedOnShowtime(Set<Showtime> showtime) {
        Set<Movie> mov = new HashSet<>();
        for (Showtime s : showtime) {
            if (s.getMovie() != null) {
                mov.add(s.getMovie());
            }
        }
        return mov;
    }

    public Set<Showtime> getShowtimesBasedonMovie(Set<Showtime> showtime, Long mId) {
        Set<Showtime> show = new HashSet<>();
        for (Showtime s : showtime) {
            if (s.getMovie().getmId() == mId) {
                show.add(s);
            }
        }
        return show;
    }
}
