package pl.sda.javastart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static pl.sda.javastart.Main.afterSignInMenu;

public class ReservationsSupport {

    private Map<Long, Reservations> reservationsMap = new HashMap<>();

    public void showReservations(){
        System.out.println(reservationsMap.get(Main.user.getId()).toString());
        afterSignInMenu();
    }

    public void addService(Services service) {
        Reservations currentReservation = getReservation();
        currentReservation.addReservation(service);
        addReservationToMap(currentReservation);
        showReservations();
    }

    private void addReservationToMap(Reservations newReservation){
        reservationsMap.put(Main.user.getId(),newReservation);
    }

    public Reservations getReservation() {
        return reservationsMap.getOrDefault(Main.user.getId(), new Reservations(Main.user));
    }


}
