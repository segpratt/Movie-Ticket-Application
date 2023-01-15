package course.ensf607.assignment6.admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner createStaff(AdminRepository adminRepository) {
        return args -> {
            Admin staff1 = new Admin(
                    (long) 1,
                    "George",
                    "George@theatre.com",
                    "pass");
            Admin staff2 = new Admin(
                    (long) 2,
                    "Bob",
                    "Bob@theatre.com",
                    "pass");

            adminRepository.saveAllAndFlush(
                    List.of(staff1, staff2));
        };
    };
}