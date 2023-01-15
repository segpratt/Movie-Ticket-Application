package course.ensf607.assignment6.seatMap;

import course.ensf607.assignment6.seat.Seat;
import course.ensf607.assignment6.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/seatMap")
public class SeatMapController {

    private final SeatMapService seatMapService;

    private final SeatService seatService;

    @Autowired
    public SeatMapController(SeatMapService seatMapService, SeatService seatService) {

        this.seatMapService = seatMapService;
        this.seatService = seatService;
    }

    @GetMapping("/getAvailableSeats")
    public List<Seat> getAvailableSeats(SeatMap seatMap) {
        return seatMapService.getAvailableSeats(seatMap);
    }

   /* @GetMapping("/reservedSeatsFull")
    public boolean reservedSeatsMaxed() {
        return seatMapService.reservedSeatsMaxed();
    }

    */




    @PostMapping("/addSeatMap")
    public void registerNewSeatMap(@RequestBody SeatMap seatMap) {
        seatMapService.addNewSeatMap(seatMap);
    }

/*
    @PutMapping("{seatMapId}/seats/{seatId}")
    public SeatMap addSeatToSeatMap(@PathVariable Long seatMapId,
                                    @PathVariable Long seatId) {
        SeatMap seatMap = seatMapService.getSeatMapById(seatMapId);
        Seat seat = seatService.getSeatById(seatId);
        seatMap.addedSeats(seat);
        seatMapService.updateSeatMap(seatMap);
        return seatMap;

    }

*/
}




