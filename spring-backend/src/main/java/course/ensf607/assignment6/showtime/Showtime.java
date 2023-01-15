package course.ensf607.assignment6.showtime;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.seat.Seat;
import course.ensf607.assignment6.theatre.Theatre;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.time.format.DateTimeFormatter;
//import course.ensf607.assignment6.showtime.Seat;

@Entity
@Transactional
@Table(name = "showtime")
public class Showtime {

    @Id
    @SequenceGenerator(name = "showtimesequence", sequenceName = "showtimesequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showtimesequence")
    private long stId;
    private String showtime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_stId", referencedColumnName = "stId")
    private Set<Seat> seats = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "mId")
    private Movie movie;

    public Showtime() {
    }

    public Showtime(Long id, LocalDateTime showtime) {
        this.stId = id;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd @ H:mm");
        String formatDateTime = showtime.format(formatter) + "PM";
        this.showtime = formatDateTime;
    }

    public Long getsId() {
        return stId;
    }

    public Showtime setsId(Long id) {
        this.stId = id;
        return this;
    }

    public String getShowtime() {
        return showtime;
    }

    public Showtime setShowtime(LocalDateTime showtime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd @ H:mm");
        String formatDateTime = showtime.format(formatter) + "PM";
        this.showtime = formatDateTime;
        return this;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public Showtime setSeats(Set<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }
}