package course.ensf607.assignment6.seat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SeatConfig {
        static Seat A1, A2, A3, A4,
                        B1, B2, B3, B4,
                        C1, C2, C3, C4,
                        D1, D2, D3, D4,
                        E1, E2, E3, E4,
                        F1, F2, F3, F4,
                        G1, G2, G3, G4,
                        H1, H2, H3, H4,
                        I1, I2, I3, I4,
                        J1, J2, J3, J4,
                        K1, K2, K3, K4,
                        L1, L2, L3, L4,
                        M1, M2, M3, M4,
                        N1, N2, N3, N4,
                        O1, O2, O3, O4,
                        P1, P2, P3, P4;

        public static Set<Seat> getSeats1() {
                Set<Seat> seats = new HashSet<>();
                seats.add(A1);
                seats.add(A2);
                seats.add(A3);
                seats.add(A4);
                seats.add(B1);
                seats.add(B2);
                seats.add(B3);
                seats.add(B4);
                seats.add(C1);
                seats.add(C2);
                seats.add(C3);
                seats.add(C4);
                seats.add(D1);
                seats.add(D2);
                seats.add(D3);
                seats.add(D4);
                return seats;
        }

        public static Set<Seat> getSeats2() {
                Set<Seat> seats = new HashSet<>();
                seats.add(E1);
                seats.add(E2);
                seats.add(E3);
                seats.add(E4);
                seats.add(F1);
                seats.add(F2);
                seats.add(F3);
                seats.add(F4);
                seats.add(G1);
                seats.add(G2);
                seats.add(G3);
                seats.add(G4);
                seats.add(H1);
                seats.add(H2);
                seats.add(H3);
                seats.add(H4);
                return seats;
        }

        public static Set<Seat> getSeats3() {
                Set<Seat> seats = new HashSet<>();
                seats.add(I1);
                seats.add(I2);
                seats.add(I3);
                seats.add(I4);
                seats.add(J1);
                seats.add(J2);
                seats.add(J3);
                seats.add(J4);
                seats.add(K1);
                seats.add(K2);
                seats.add(K3);
                seats.add(K4);
                seats.add(L1);
                seats.add(L2);
                seats.add(L3);
                seats.add(L4);
                return seats;
        }

        public static Set<Seat> getSeats4() {
                Set<Seat> seats = new HashSet<>();
                seats.add(M1);
                seats.add(M2);
                seats.add(M3);
                seats.add(M4);
                seats.add(N1);
                seats.add(N2);
                seats.add(N3);
                seats.add(N4);
                seats.add(O1);
                seats.add(O2);
                seats.add(O3);
                seats.add(O4);
                seats.add(P1);
                seats.add(P2);
                seats.add(P3);
                seats.add(P4);
                return seats;
        }

        @Bean
        CommandLineRunner createSeats(SeatRepository seatRepository) {
                return args -> {
                        A1 = new Seat(
                                        (long) 1,
                                        'A',
                                        false,
                                        1);
                        A2 = new Seat(
                                        (long) 2,
                                        'A',
                                        false,
                                        2);
                        A3 = new Seat(
                                        (long) 3,
                                        'A',
                                        true,
                                        3);
                        A4 = new Seat(
                                        (long) 4,
                                        'A',
                                        true,
                                        4);
                        B1 = new Seat(
                                        (long) 5,
                                        'B',
                                        false,
                                        1);
                        B2 = new Seat(
                                        (long) 6,
                                        'B',
                                        false,
                                        2);
                        B3 = new Seat(
                                        (long) 7,
                                        'B',
                                        false,
                                        3);
                        B4 = new Seat(
                                        (long) 8,
                                        'B',
                                        false,
                                        4);
                        C1 = new Seat(
                                        (long) 9,
                                        'C',
                                        false,
                                        1);
                        C2 = new Seat(
                                        (long) 10,
                                        'C',
                                        false,
                                        2);
                        C3 = new Seat(
                                        (long) 11,
                                        'C',
                                        false,
                                        3);
                        C4 = new Seat(
                                        (long) 12,
                                        'C',
                                        false,
                                        4);
                        D1 = new Seat(
                                        (long) 13,
                                        'D',
                                        false,
                                        1);
                        D2 = new Seat(
                                        (long) 14,
                                        'D',
                                        false,
                                        2);
                        D3 = new Seat(
                                        (long) 15,
                                        'D',
                                        false,
                                        3);
                        D4 = new Seat(
                                        (long) 16,
                                        'D',
                                        false,
                                        4);
                        E1 = new Seat(
                                        (long) 17,
                                        'A',
                                        false,
                                        1);
                        E2 = new Seat(
                                        (long) 18,
                                        'A',
                                        false,
                                        2);
                        E3 = new Seat(
                                        (long) 19,
                                        'A',
                                        false,
                                        3);
                        E4 = new Seat(
                                        (long) 20,
                                        'A',
                                        false,
                                        4);
                        F1 = new Seat(
                                        (long) 21,
                                        'B',
                                        false,
                                        1);
                        F2 = new Seat(
                                        (long) 22,
                                        'B',
                                        false,
                                        2);
                        F3 = new Seat(
                                        (long) 23,
                                        'B',
                                        false,
                                        3);
                        F4 = new Seat(
                                        (long) 24,
                                        'B',
                                        false,
                                        4);
                        G1 = new Seat(
                                        (long) 25,
                                        'C',
                                        false,
                                        1);
                        G2 = new Seat(
                                        (long) 26,
                                        'C',
                                        false,
                                        2);
                        G3 = new Seat(
                                        (long) 27,
                                        'C',
                                        false,
                                        3);
                        G4 = new Seat(
                                        (long) 28,
                                        'C',
                                        false,
                                        4);
                        H1 = new Seat(
                                        (long) 29,
                                        'D',
                                        false,
                                        1);
                        H2 = new Seat(
                                        (long) 30,
                                        'D',
                                        false,
                                        2);
                        H3 = new Seat(
                                        (long) 31,
                                        'D',
                                        false,
                                        3);
                        H4 = new Seat(
                                        (long) 32,
                                        'D',
                                        false,
                                        4);
                        I1 = new Seat(
                                        (long) 33,
                                        'A',
                                        false,
                                        1);
                        I2 = new Seat(
                                        (long) 34,
                                        'A',
                                        false,
                                        2);
                        I3 = new Seat(
                                        (long) 35,
                                        'A',
                                        true,
                                        3);
                        I4 = new Seat(
                                        (long) 36,
                                        'A',
                                        true,
                                        4);
                        J1 = new Seat(
                                        (long) 37,
                                        'B',
                                        false,
                                        1);
                        J2 = new Seat(
                                        (long) 38,
                                        'B',
                                        false,
                                        2);
                        J3 = new Seat(
                                        (long) 39,
                                        'B',
                                        false,
                                        3);
                        J4 = new Seat(
                                        (long) 40,
                                        'B',
                                        false,
                                        4);
                        K1 = new Seat(
                                        (long) 41,
                                        'C',
                                        false,
                                        1);
                        K2 = new Seat(
                                        (long) 42,
                                        'C',
                                        false,
                                        2);
                        K3 = new Seat(
                                        (long) 43,
                                        'C',
                                        false,
                                        3);
                        K4 = new Seat(
                                        (long) 44,
                                        'C',
                                        false,
                                        4);
                        L1 = new Seat(
                                        (long) 45,
                                        'D',
                                        false,
                                        1);
                        L2 = new Seat(
                                        (long) 46,
                                        'D',
                                        false,
                                        2);
                        L3 = new Seat(
                                        (long) 47,
                                        'D',
                                        false,
                                        3);
                        L4 = new Seat(
                                        (long) 48,
                                        'D',
                                        false,
                                        4);
                        M1 = new Seat(
                                        (long) 49,
                                        'A',
                                        false,
                                        1);
                        M2 = new Seat(
                                        (long) 50,
                                        'A',
                                        false,
                                        2);
                        M3 = new Seat(
                                        (long) 51,
                                        'A',
                                        true,
                                        3);
                        M4 = new Seat(
                                        (long) 52,
                                        'A',
                                        true,
                                        4);
                        N1 = new Seat(
                                        (long) 53,
                                        'B',
                                        false,
                                        1);
                        N2 = new Seat(
                                        (long) 54,
                                        'B',
                                        false,
                                        2);
                        N3 = new Seat(
                                        (long) 55,
                                        'B',
                                        false,
                                        3);
                        N4 = new Seat(
                                        (long) 56,
                                        'B',
                                        false,
                                        4);
                        O1 = new Seat(
                                        (long) 57,
                                        'C',
                                        false,
                                        1);
                        O2 = new Seat(
                                        (long) 58,
                                        'C',
                                        false,
                                        2);
                        O3 = new Seat(
                                        (long) 59,
                                        'C',
                                        false,
                                        3);
                        O4 = new Seat(
                                        (long) 60,
                                        'C',
                                        false,
                                        4);
                        P1 = new Seat(
                                        (long) 61,
                                        'D',
                                        false,
                                        1);
                        P2 = new Seat(
                                        (long) 62,
                                        'D',
                                        false,
                                        2);
                        P3 = new Seat(
                                        (long) 63,
                                        'D',
                                        false,
                                        3);
                        P4 = new Seat(
                                        (long) 64,
                                        'D',
                                        false,
                                        4);

                        seatRepository.saveAllAndFlush(
                                        List.of(A1, A2, A3, A4,
                                                        B1, B2, B3, B4,
                                                        C1, C2, C3, C4,
                                                        D1, D2, D3, D4,
                                                        E1, E2, E3, E4,
                                                        F1, F2, F3, F4,
                                                        G1, G2, G3, G4,
                                                        H1, H2, H3, H4,
                                                        I1, I2, I3, I4,
                                                        J1, J2, J3, J4,
                                                        K1, K2, K3, K4,
                                                        L1, L2, L3, L4,
                                                        M1, M2, M3, M4,
                                                        N1, N2, N3, N4,
                                                        O1, O2, O3, O4,
                                                        P1, P2, P3, P4));
                };
        };
}
