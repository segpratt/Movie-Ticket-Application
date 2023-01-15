package course.ensf607.assignment6.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(Long id);

    Optional<Ticket> findBySeat(Long seat);

    // Optional<Ticket> findByShowTime(Long id);

}