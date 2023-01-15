package course.ensf607.assignment6.theatre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    Optional<Theatre> findByName(String name);

}
