package course.ensf607.assignment6.seatMap;

import course.ensf607.assignment6.seat.Seat;
import javax.persistence.*;
import java.util.List;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seatMap")
public class SeatMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeatMap_generator")
    private Long id;


    //Temp Variable
    private String theatreRoom;

    private int reserveCapacity;

    @OneToMany@JoinTable(
            name = "seats_assigned_to_map",
            joinColumns = @JoinColumn(name = "seatMap_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private Set<Seat> seats;

    public SeatMap(){}

    public SeatMap(Long id, String theatreRoom, int reserveCapacity, Set<Seat> seats) {
        this.id = id;
        this.theatreRoom = theatreRoom;
        this.reserveCapacity = reserveCapacity;
        this.seats = seats;
    }

    public SeatMap(String theatreRoom, int reserveCapacity, Set<Seat> seats) {
        this.theatreRoom = theatreRoom;
        this.reserveCapacity = reserveCapacity;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheatreRoom() {
        return theatreRoom;
    }

    public void setTheatreRoom(String theatreRoom) {
        this.theatreRoom = theatreRoom;
    }

    public int getReserveCapacity() {
        return reserveCapacity;
    }

    public void setReserveCapacity(int reserveCapacity) {
        this.reserveCapacity = reserveCapacity;
    }

    public Set<Seat> seats() {
        return seats;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setAddedSeats(Set<Seat> addedSeats) {
        this.seats = addedSeats;
    }

    public void addedSeats(Seat seat) { seats.add(seat);}

}
