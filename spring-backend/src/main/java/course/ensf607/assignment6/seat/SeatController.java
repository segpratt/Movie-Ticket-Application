package course.ensf607.assignment6.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/seat")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getSeats() {
        return seatService.getAllSeats();
    }

    @PutMapping("/reserveSeat")
    public void reserveSeat(@RequestBody Seat seat) {
        seatService.reserve(seat);
    }

    @PutMapping("/reserveSeat/{id}")
    public void reserveSeatById(@PathVariable Long id) {
        seatService.reserveById(id);
    }

    @PostMapping
    public void registerNewSeat(@RequestBody Seat seat) {
        seatService.addNewSeat(seat);
    }
}
