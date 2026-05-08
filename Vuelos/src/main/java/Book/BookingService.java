package Book;
import Flights.FlightsRepository;
import User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class BookingService {

    @Autowired
    private BookRepository bookingRepository;

    @Autowired
    private FlightsRepository flightRepository;

    @Autowired
    private UserRepository userRepository;


    public void reiniciarBaseDeDatos() {
        bookingRepository.deleteAll();
        flightRepository.deleteAll();
        userRepository.deleteAll();
    }
}
