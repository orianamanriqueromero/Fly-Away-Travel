package Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @DeleteMapping("/cleanup")
    public String cleanup() {
        bookingService.reiniciarBaseDeDatos();
        return "OK";
    }

}
