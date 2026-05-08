package Flights;

import org.springframework.beans.factory.annotation.Autowired;

public class FlightService {
    @Autowired
    private FlightsRepository flightRepository;

    //mtodo CREAR VUELO

    public Flights crearVuelo(Flights flight) {
        //Número de vuelo: A-Z, 0-9, máximo 6 caracteres (ej: AA984)
               // máximo 6 caracteres (ej: AA984)
        if (flight.getFlightNumber().length()>6){
            throw new RuntimeException("el número de vuelo debe tener al menos 6 caracteres");
        }
               // debe contener A-Z, 0-9
        if (flight.getFlightNumber().matches(".*[a-zA-Z].*") || flight.getFlightNumber().matches(".*\\d.*")){
            throw new RuntimeException("El número de vuelo es inválido");
        }

        // Hora salida < Hora llegada
        if (flight.getDepartureTime().isBefore(flight.getArrivalTime())){
            throw new RuntimeException("la hora de salida debe ser menor a la hora de llegada");
        }

        // Asientos disponibles > 0

        if (flight.getAvailableSeats()<0) {
            throw new RuntimeException ("el numero de asientos no puede ser negativo");
        }
        // Números de vuelo únicos
        if (flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
            throw new RuntimeException("El numero de vuelo ya existe");
        }

        return flightRepository.save(flight);
    }
}
