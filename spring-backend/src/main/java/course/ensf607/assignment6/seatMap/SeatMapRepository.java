package course.ensf607.assignment6.seatMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatMapRepository extends JpaRepository<SeatMap, Long> {

    Optional<SeatMap> findByTheatreRoom(String theatreRoom);

}
