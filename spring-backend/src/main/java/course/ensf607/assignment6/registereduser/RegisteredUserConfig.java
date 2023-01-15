package course.ensf607.assignment6.registereduser;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.movie.MovieRepository;
import course.ensf607.assignment6.payment.Payment;
import course.ensf607.assignment6.payment.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class RegisteredUserConfig {

    private RegisteredUser user1;
    private RegisteredUser user2;
    private RegisteredUser guest;
    @Bean
    CommandLineRunner createUsers(RegisteredUserRepository registeredUserRepository) {
        return args -> {
            user1 = new RegisteredUser(
                    (long) 1,
                    "user1@gmail.com",
                    "pass",
                    "Joe",
                    "123 Street",
                    391,
                    123456789,
                    1828,
                    500.00,
                    LocalDate.of(2014, 11, 8)
            );
            user2 = new RegisteredUser(
                    (long) 2,
                    "user2@gmail.com",
                    "pass",
                    "Jim",
                    "321 Street",
                    691,
                    987654321,
                    1825,
                    600.00,
                    LocalDate.of(2018, 11, 8)
            );

            guest = new RegisteredUser(
                    (long) 3,
                    "guest",
                    "guest",
                    "guest",
                    "guest",
                    0,
                    0,
                    0,
                    100.00);

            registeredUserRepository.saveAllAndFlush(
                    List.of(user1, user2, guest));
        };
    };

    @Bean
    CommandLineRunner createPayment(PaymentRepository paymentRepository) {
        return args -> {

            Payment paymentOne = new Payment(
                    "annual",
                    500.00,
                    user1
            );

            Payment paymentTwo = new Payment(
                    "annual",
                    600,
                    user2
            );

            Payment paymentThree = new Payment(
                    "Normal User",
                    0,
                    guest
            );

            paymentRepository.saveAllAndFlush(
                    List.of(paymentOne, paymentTwo, paymentThree));
        };
    };
}
