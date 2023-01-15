package course.ensf607.assignment6.theatre;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.showtime.Showtime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "theatre")
public class Theatre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tId")
    private Long tId;
    private String name;

    // @OneToMany(mappedBy = "stId")
    // private Set<Showtime> showtimes = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "showsandmovies", joinColumns = { @JoinColumn(name = "tId") }, inverseJoinColumns = {
            @JoinColumn(name = "stId") })
    private Set<Showtime> showtimes = new HashSet<>();

    public Theatre() {
    }

    public Theatre(Long id, String name) {
        this.tId = id;
        this.name = name;
    }

    public Long gettId() {
        return tId;
    }

    public Theatre settId(Long id) {
        this.tId = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Theatre setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public Theatre setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
        return this;
    }

    public Theatre addShowtimes(Showtime showtime) {
        showtimes.add(showtime);
        return this;
    }
}

// hello world