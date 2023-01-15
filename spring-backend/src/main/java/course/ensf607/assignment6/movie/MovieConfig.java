package course.ensf607.assignment6.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class MovieConfig {
    static Movie m1, m2;

    public static Movie getInterstellar() {
        return m1;
    }

    public static Movie getCars2() {
        return m2;
    }

    @Bean
    CommandLineRunner createMovies(MovieRepository movieRepository) {
        return args -> {
            m1 = new Movie(
                    (long) 1,
                    "Interstellar",
                    LocalDate.of(2014, 11, 7));
            m2 = new Movie(
                    (long) 2,
                    "Cars 2",
                    LocalDate.of(2011, 6, 24));

            movieRepository.saveAllAndFlush(
                    List.of(m1, m2));
        };
    };
}
