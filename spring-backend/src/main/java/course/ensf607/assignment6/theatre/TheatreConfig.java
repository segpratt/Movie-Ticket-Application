package course.ensf607.assignment6.theatre;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.showtime.Showtime;
import course.ensf607.assignment6.showtime.ShowtimeConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class TheatreConfig {
    @Bean
    CommandLineRunner createTheatres(TheatreRepository theatreRepository) {
        return args -> {
            Theatre crowfoot = new Theatre(
                    (long) 1,
                    "Crowfoot")
                    .setShowtimes(ShowtimeConfig.getShowtimes1());

            Theatre chinook = new Theatre(
                    (long) 2,
                    "Chinook")
                    .setShowtimes(ShowtimeConfig.getShowtimes2());

            theatreRepository.saveAllAndFlush(
                    List.of(crowfoot, chinook));
        };
    };
}
