package course.ensf607.assignment6.showtime;

import course.ensf607.assignment6.movie.MovieConfig;
import course.ensf607.assignment6.seat.SeatConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ShowtimeConfig {
        static Showtime st1, st2, st3, st4, st5, st6, st7, st8;

        public static Set<Showtime> getShowtimes1() {
                Set<Showtime> showtimes = new HashSet<>();
                showtimes.add(st1.setMovie(MovieConfig.getInterstellar()).setSeats(SeatConfig.getSeats1()));
                showtimes.add(st2.setMovie(MovieConfig.getCars2()).setSeats(SeatConfig.getSeats2()));
                showtimes.add(st3.setMovie(MovieConfig.getInterstellar()).setSeats(SeatConfig.getSeats3()));

                return showtimes;
        }

        public static Set<Showtime> getShowtimes2() {
                Set<Showtime> showtimes = new HashSet<>();
                // showtimes.add(st3.setMovie(MovieConfig.getInterstellar()).setSeats(SeatConfig.getSeats3()));
                showtimes.add(st4.setMovie(MovieConfig.getCars2()).setSeats(SeatConfig.getSeats4()));
                return showtimes;
        }

        // public static Set<Showtime> getShowtimes3() {
        // Set<Showtime> showtimes = new HashSet<>();
        // showtimes.add(st5.setMovie(MovieConfig.getInterstellar()).setSeats(SeatConfig.getSeatsA()));
        // showtimes.add(st6.setMovie(MovieConfig.getCars2()).setSeats(SeatConfig.getSeatsB()));
        // return showtimes;
        // }

        // public static Set<Showtime> getShowtimes4() {
        // Set<Showtime> showtimes = new HashSet<>();
        // showtimes.add(st7.setMovie(MovieConfig.getInterstellar()).setSeats(SeatConfig.getSeatsC()));
        // showtimes.add(st8.setMovie(MovieConfig.getCars2()).setSeats(SeatConfig.getSeatsD()));
        // return showtimes;
        // }

        @Bean
        CommandLineRunner createShowtime(ShowtimeRepository showtimeRepository) {
                return args -> {
                        st1 = new Showtime(
                                        (long) 1,
                                        LocalDateTime.of(2022, 12, 7, 2, 20));
                        st2 = new Showtime(
                                        (long) 2,
                                        LocalDateTime.of(2022, 12, 8, 4, 30));
                        st3 = new Showtime(
                                        (long) 3,
                                        LocalDateTime.of(2022, 12, 8, 2, 0));
                        st4 = new Showtime(
                                        (long) 4,
                                        LocalDateTime.of(2022, 12, 9, 3, 30));
                        st5 = new Showtime(
                                        (long) 5,
                                        LocalDateTime.of(2022, 12, 10, 5, 10));
                        st6 = new Showtime(
                                        (long) 6,
                                        LocalDateTime.of(2022, 12, 11, 7, 30));
                        st7 = new Showtime(
                                        (long) 7,
                                        LocalDateTime.of(2022, 12, 12, 6, 0));
                        st8 = new Showtime(
                                        (long) 8,
                                        LocalDateTime.of(2022, 12, 13, 8, 30));

                        showtimeRepository.saveAllAndFlush(
                                        List.of(st1, st2, st3, st4, st5, st6, st7, st8));
                };
        };
}
