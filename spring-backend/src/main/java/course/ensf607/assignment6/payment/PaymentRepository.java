package course.ensf607.assignment6.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

   // Optional<Payment> findPaymentByName(String name);
    Optional<Payment> findPaymentBypId(long pId);

}
