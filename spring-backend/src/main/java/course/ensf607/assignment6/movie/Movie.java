package course.ensf607.assignment6.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import course.ensf607.assignment6.showtime.Showtime;
import course.ensf607.assignment6.theatre.Theatre;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mId")
    private Long mId;
    private String name;
    private LocalDate releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    public Movie() {
    }

    public Movie(Long id, String name, LocalDate releaseDate) {
        this.mId = mId;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Movie(String name, LocalDate releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Long getmId() {
        return mId;
    }

    public Movie setmId(Long id) {
        this.mId = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Movie setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    // public Set<Theatre> getTheatres() {
    // return theatres;
    // }

    // public Movie setTheatres(Set<Theatre> theatres) {
    // this.theatres = theatres;
    // return this;
    // }

    // public Set<Showtime> getShowtimes() {
    // return showtimes;
    // }

    // public Movie setShowtimes(Set<Showtime> showtimes) {
    // this.showtimes = showtimes;
    // return this;
    // }

    // public void addShowtime(Showtime showtime) {
    // showtimes.add(showtime);
    // }
}