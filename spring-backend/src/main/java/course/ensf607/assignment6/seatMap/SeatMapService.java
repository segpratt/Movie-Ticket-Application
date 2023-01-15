package course.ensf607.assignment6.seatMap;

import course.ensf607.assignment6.seat.Seat;
import course.ensf607.assignment6.seat.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SeatMapService {

    private final SeatMapRepository seatMapRepository;

    @Autowired
    public SeatMapService(SeatMapRepository seatMapRepository) {
        this.seatMapRepository = seatMapRepository;
    }

    public List<SeatMap> getAllSeats() {
        return seatMapRepository.findAll();
    }

    public void addNewSeatMap(SeatMap seatMap) {
        Optional<SeatMap> seatMapByRoom = seatMapRepository.findByTheatreRoom(seatMap.getTheatreRoom());
        if (seatMapByRoom.isPresent()) {
            throw new IllegalStateException("SeatMap already exist!");
        }
        seatMapRepository.save(seatMap);
    }

    public void updateSeatMap(SeatMap seatMap) {
        seatMapRepository.save(seatMap);
    }

    public SeatMap getSeatMapById(Long seatMapId) {
        Optional<SeatMap> seatMapById = seatMapRepository.findById(seatMapId);
        if (!seatMapById.isPresent()) {
            throw new IllegalStateException("SeatMap does not exist!");
        }
        return seatMapById.get();
    }


    public List<Seat> getAvailableSeats(SeatMap seatMap) {
        List<Seat> availableSeats = new ArrayList<>();
        for (Iterator<Seat> it = seatMap.getSeats().iterator(); it.hasNext(); ) {
            Seat s = it.next();
            if (s.isReserved() ==false)
                availableSeats.add(s);

        }

        return  availableSeats;

    }
}
