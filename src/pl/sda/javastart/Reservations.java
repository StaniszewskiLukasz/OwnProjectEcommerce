package pl.sda.javastart;

import java.util.HashSet;
import java.util.Set;

public class Reservations {
    private User user;
    private Set<ReservationsServices> reservationsServices = new HashSet<>();

    public Reservations(User user) {
        this.user = user;
    }

    public void addReservation(Services service) {
        ReservationsServices reservationProductTemp = reservationsServices.stream()
                .filter(reservationsServices -> reservationsServices.getService().equals(service))
                .findAny()
                .orElse(null);
        if (reservationProductTemp == null) {
            reservationProductTemp = new ReservationsServices();
            reservationProductTemp.setReservation(this);
            reservationProductTemp.setPrice(service.getPrice());
            reservationProductTemp.setService(service);
            reservationProductTemp.setQuantity(1);
        } else {
            reservationProductTemp.setQuantity(reservationProductTemp.getQuantity() + 1);
        }
        reservationsServices.add(reservationProductTemp);
    }

}
