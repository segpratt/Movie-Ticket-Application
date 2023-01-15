package course.ensf607.assignment6.showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping()
    public List<Showtime> getShowtimes() {
        return showtimeService.getShowtimes();
    }

    @PostMapping("/addShowtime")
    public ResponseEntity<String> registerNewShowtime(@RequestBody Showtime showtime) {
        showtimeService.addShowtime(showtime);
        return ResponseEntity.ok("Showtime added.");
    }

}
