package course.ensf607.assignment6.seat;

import course.ensf607.assignment6.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public void addNewSeat(Seat seat) {
        Optional<Seat> seatById = seatRepository.findSeatById(seat.getId());
        if (seatById.isPresent()) {
            throw new IllegalStateException("Seat already exist!");
        }
        seatRepository.save(seat);

    }

    public Seat getSeatById(Long seatId) {
        Optional<Seat> seatById = seatRepository.findById(seatId);
        if (!seatById.isPresent()) {
            throw new IllegalStateException("Seat does not exist!");
        }
        return seatById.get();
    }

    public void reserve(Seat seat) {
        seat.reserve();
    }

    public void reserveById(Long id) {
        Seat seat = getSeatById(id);
        seat.reserve();
        seatRepository.save(seat);
    }

    public void unreserveById(Long id) {
        Seat seat = getSeatById(id);
        seat.unreserve();
        seatRepository.save(seat);
    }
}
