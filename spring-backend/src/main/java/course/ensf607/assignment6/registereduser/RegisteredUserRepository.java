package course.ensf607.assignment6.registereduser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

    Optional<RegisteredUser> findByEmail(String email);

    RegisteredUser findByCardNo(int cardNo);

    Optional<RegisteredUser> findById(Long id);

    void deleteUserById(Long id);

    void deleteByEmail(String email);
}
