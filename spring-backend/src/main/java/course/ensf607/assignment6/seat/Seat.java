package course.ensf607.assignment6.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import course.ensf607.assignment6.seatMap.SeatMap;
import course.ensf607.assignment6.ticket.Ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat implements Serializable {

    @Id
    @SequenceGenerator(name = "seatesequence", sequenceName = "seatesequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seatesequence")
    private long id;

    private char letter_row;

    private boolean isReserved;
    private String seat;

    private int number_row;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "seat_map_id")
    private SeatMap seatMap;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    // private Ticket ticket;

    public Seat() {
    }

    public Seat(Long id, char letter_row, boolean isReserved, int number_row, SeatMap seatMap) {
        this.id = id;
        this.letter_row = letter_row;
        this.isReserved = isReserved;
        this.number_row = number_row;
        this.seat = String.valueOf(letter_row + number_row);
        this.seatMap = seatMap;
    }

    public Seat(char letter_row, boolean isReserved, int number_row, SeatMap seatMap) {
        this.letter_row = letter_row;
        this.isReserved = isReserved;
        this.number_row = number_row;
        this.seat = String.valueOf(letter_row + number_row);
        this.seatMap = seatMap;
    }

    public Seat(Long id, char letter_row, boolean isReserved, int number_row) {
        this.id = id;
        this.letter_row = letter_row;
        this.isReserved = isReserved;
        this.number_row = number_row;
        this.seat = String.valueOf(letter_row + number_row);
    }

    public Long getId() {
        return id;
    }

    public Seat setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        isReserved = true;
    }

    public void unreserve() {
        isReserved = false;
    }

    public String getSeat() {
        return seat;
    }

    public Seat setRowLabel(String row_label) {
        this.seat = row_label;
        return this;
    }

    public char getLetter_row() {
        return letter_row;
    }

    public void setLetter_row(char letter_row) {
        this.letter_row = letter_row;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getNumber_row() {
        return number_row;
    }

    public void setNumber_row(int number_row) {
        this.number_row = number_row;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat='" + seat + '\'' +
                '}';
    }
}
