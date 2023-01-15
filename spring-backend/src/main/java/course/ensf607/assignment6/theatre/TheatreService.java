package course.ensf607.assignment6.theatre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.ensf607.assignment6.movie.Movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    @Autowired
    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatres() {
        return theatreRepository.findAll();
    }

    public void addTheatre(Theatre theatre) {
        Optional<Theatre> theatreByName = theatreRepository.findByName(theatre.getName());
        if (theatreByName.isPresent()) {
            throw new IllegalStateException("Theatre already exist!");
        }
        theatreRepository.save(theatre);
    }

    public void updateTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
    }

    public Theatre getTheatreById(Long theatreId) {
        Optional<Theatre> theatreById = theatreRepository.findById(theatreId);
        if (!theatreById.isPresent()) {
            throw new IllegalStateException("Theatre doesn't exist!");
        }
        return theatreById.get();
    }

    // public Set<Movie> getMovieListById(Long theatreId) {
    // Theatre theatreById = theatreRepository.findById(theatreId).get();
    // if (theatreById == null) {
    // throw new IllegalStateException("Theatre doesn't exist!");
    // }
    // return theatreById.getMovies();
    // }

}
