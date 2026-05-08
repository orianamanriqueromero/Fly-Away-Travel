package Book;

import Flights.Flights;
import User.User;
import jakarta.persistence.*;
        import lombok.*;
        import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flights flight; // Input del usuario

    private LocalDateTime bookingDate;
}