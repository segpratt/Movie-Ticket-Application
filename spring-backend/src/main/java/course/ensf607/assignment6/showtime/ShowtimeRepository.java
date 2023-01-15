package course.ensf607.assignment6.showtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

     // Optional<Showtime> findByTime(LocalDate time);
     Optional<Showtime> findById(long stId);

}
