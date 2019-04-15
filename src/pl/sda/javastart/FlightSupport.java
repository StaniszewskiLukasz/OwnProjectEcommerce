package pl.sda.javastart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static pl.sda.javastart.Main.afterSignInMenu;

public class FlightSupport {
    private String airportToCheck = null;
    private String nextAirportToCheck = null;
    private boolean firstAirportSearching = false;
    private boolean secondAirportSearching = false;
    private List<Flight> flights = new ArrayList<>();
    private List<Flight> airports = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Flight currentFlight = new Flight();
    private ServicesSupport servicesSupport = new ServicesSupport();

    {
        createRouteMap();
    }

    private void createRouteMap() {
        try (FileReader fileReader = new FileReader("D:\\Programowanie\\Nauka\\Pliki txt\\Miasta.txt")) {
            readFile(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.equals("")) {
                Flight airport = new Flight();
                airport.setAirport(line.toUpperCase()); //fixme
                airports.add(airport);
            }
        }
    }

    public void generalMethodFlights() {
        System.out.println("1. W obie strony");
        System.out.println("2. W jedną stronę");
        Integer choice = scanner.nextInt();
        switch (choice) {
            case 1:
                flightWithReturn();
                break;
            case 2:
                oneWayFlight();
                break; // fixme czy tu powinien być break?
            default:
                System.out.println("Wybrałeś nieprawidłową opcję.");
        }
    }

    private void questionAboutFlight() {
        System.out.println("Lista miast jakie możesz odwiedzić");
        System.out.println(airports.toString());
        System.out.println("Skąd chcesz lecieć. Wpisz nazwę miasta.");
        airportToCheck = scanner.next();
        System.out.println("Dokąd chcesz lecieć. Wpisz nazwę miasta.");
        nextAirportToCheck = scanner.next();
    }

    private void flightWithReturn() {  // fixme trzeba zrefaktorować i skrócić kod
        questionAboutFlight();
        availabilityOfFlights();
        if (airportToCheck.equalsIgnoreCase(nextAirportToCheck)) {
            System.out.println();
            System.out.println("Wybierz dwa róźne miasta");
            System.out.println();
            flightWithReturn();
        }
//        for (Flight airport : airports) { // tak chyba się nie da bo on za każdym razem porównuje oba stringi do jednego obiektu z listy i zawsze false
//            System.out.println(airport.toString());
//            System.out.println(airportToCheck);
//            if(airport.toString().trim().equalsIgnoreCase(airportToCheck) && airport.toString().trim().equalsIgnoreCase(nextAirportToCheck)){
//               ifExist = true;
//               break;
//            }
//        }
//        boolean ifExist = airports.stream()
//                .anyMatch(airport -> airport.getAirport().equalsIgnoreCase(airportToCheck.trim()) &&
//                        airport.getAirport().equalsIgnoreCase(nextAirportToCheck.trim()));
        Flight chosenFlight = new Flight();
        if (firstAirportSearching && secondAirportSearching) {
            try {
                System.out.println("Wpisz liczbę dni pomiędzy wylotem a powrotem");
                BigDecimal numberOfDays = scanner.nextBigDecimal();
                System.out.println("Wybierz opcję. 1.Bez przesiadki   2.Z przesiadką");
                BigDecimal priceDiscount = scanner.nextBigDecimal();
                System.out.println("Liczba dorosłych. Wybierz.");
                BigDecimal numberOfPassengers = scanner.nextBigDecimal();
                System.out.println("Liczba niepełnoletnich powyżej 10 roku życia. Wybierz.");
                BigDecimal numberOfYouthPassengers = scanner.nextBigDecimal();
                System.out.println("Liczba dzieci poniżej 10 roku życia. Wybierz.");
                BigDecimal numberOfChildrenPassengers = scanner.nextBigDecimal();
                System.out.println("Wybierz klasę. 1.Ekonomiczna   2.Biznes");
                BigDecimal priceAdvance = scanner.nextBigDecimal();
                System.out.println("Bagaż dodatkowy. Wpisz liczbę sztuk.");
                BigDecimal nextPriceAdvance = scanner.nextBigDecimal();
                BigDecimal extraLuggage = new BigDecimal(200);
                BigDecimal basisPrice = new BigDecimal(1000);
                BigDecimal price = countFlightPriceForRoundTrip(numberOfDays, priceDiscount, numberOfPassengers, numberOfYouthPassengers,
                        numberOfChildrenPassengers, priceAdvance, nextPriceAdvance, extraLuggage, basisPrice);
                createFlight(airportToCheck, nextAirportToCheck, chosenFlight, price);
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Takiego lotu nie obsługujemy");
            flightWithReturn();
        }
        System.out.println("Lot w obie strony " + showMeMyFlight(chosenFlight));
        flightSubmenu();
    }

    private void oneWayFlight() { // fixme trzeba zrefaktorować i skrócić kod
        questionAboutFlight();
        availabilityOfFlights();
        if (airportToCheck.equalsIgnoreCase(nextAirportToCheck)) {
            System.out.println();
            System.out.println("Wybierz dwa róźne miasta");
            System.out.println();
            oneWayFlight();
        }
        Flight chosenFlight = new Flight();
        if (firstAirportSearching && secondAirportSearching) {
            try {
                System.out.println("Wybierz opcję. 1.Bez przesiadki   2.Z przesiadką");
                BigDecimal priceDiscount = scanner.nextBigDecimal();
                System.out.println("Liczba dorosłych. Wybierz.");
                BigDecimal numberOfPassengers = scanner.nextBigDecimal();
                System.out.println("Liczba niepełnoletnich powyżej 10 roku życia. Wybierz.");
                BigDecimal numberOfYouthPassengers = scanner.nextBigDecimal();
                System.out.println("Liczba dzieci poniżej 10 roku życia. Wybierz.");
                BigDecimal numberOfChildrenPassengers = scanner.nextBigDecimal();
                System.out.println("Wybierz klasę. 1.Ekonomiczna   2.Biznes");
                BigDecimal priceAdvance = scanner.nextBigDecimal();
                System.out.println("Bagaż dodatkowy. Wpisz liczbę sztuk.");
                BigDecimal nextPriceAdvance = scanner.nextBigDecimal();
                BigDecimal extraLuggage = new BigDecimal(200);
                BigDecimal basisPrice = new BigDecimal(600);
                BigDecimal price = countFlightPriceForOneWay(priceDiscount, numberOfPassengers, numberOfYouthPassengers,
                        numberOfChildrenPassengers, priceAdvance, nextPriceAdvance, extraLuggage, basisPrice);
                createFlight(airportToCheck, nextAirportToCheck, chosenFlight, price);
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Takiego lotu nie obsługujemy");
            oneWayFlight();
        }
        System.out.println("Lot w jedną stronę " + showMeMyFlight(chosenFlight));
        flightSubmenu();
    }

    private void availabilityOfFlights() {
        for (Flight airport : airports) {
            if (airport.toString().trim().equalsIgnoreCase(airportToCheck)) {
                firstAirportSearching = true;
                break;
            }
        }
        for (Flight airport : airports) {
            if (airport.toString().trim().equalsIgnoreCase(nextAirportToCheck)) {
                secondAirportSearching = true;
                break;
            }
        }
    }

    private void createFlight(String airportToCheck, String nextAirportToCheck, Flight chosenFlight, BigDecimal price) {
        chosenFlight.setId(servicesSupport.idForNextService());
        chosenFlight.setPrice(price);
        chosenFlight.setName(airportToCheck + "-" + nextAirportToCheck);
        currentFlight = chosenFlight;
        flights.add(chosenFlight);
        servicesSupport.services.add(chosenFlight);
    }

    private String showMeMyFlight(Flight flight) {
        return flight.getName() + " o numerze " + flight.getId() + "." + " Cena wybranego wariantu to: " + flight.getPrice() + "zł";
    }

    private void flightSubmenu() {
        System.out.println();
        System.out.print("1.Sprawdź szczegóły lotu i kontynuuj rezerwację");
        System.out.print("                   ");
        System.out.println("2.Powrót do menu głównego");
        Integer choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addToReservations(currentFlight);
                break;
            case 2:
                afterSignInMenu();
                break;
        }
    }

    public void addToReservations(Flight currentFlight) {
        ReservationsSupport service = new ReservationsSupport();
        service.addService(currentFlight);
    }

    private BigDecimal countFlightPriceForRoundTrip(BigDecimal numberOfDays, BigDecimal priceDiscount, BigDecimal numberOfPassengers,
                                                    BigDecimal numberOfYouthPassengers, BigDecimal numberOfChildrenPassengers,
                                                    BigDecimal priceAdvance, BigDecimal nextPriceAdvance, BigDecimal extraLuggage,
                                                    BigDecimal basisPrice) {
        return (basisPrice.divide(priceDiscount).multiply(numberOfPassengers).multiply(priceAdvance))
                .add(countPriceForExtraLuggage(nextPriceAdvance, extraLuggage)).add(numberOfDays)
                .add(countPriceForYouth(numberOfYouthPassengers, basisPrice))
                .add(countPriceForChildren(numberOfChildrenPassengers, basisPrice));
    }

    private BigDecimal countFlightPriceForOneWay(BigDecimal priceDiscount, BigDecimal numberOfPassengers,
                                                 BigDecimal numberOfYouthPassengers, BigDecimal numberOfChildrenPassengers,
                                                 BigDecimal priceAdvance, BigDecimal nextPriceAdvance, BigDecimal extraLuggage,
                                                 BigDecimal basisPrice) {
        return (basisPrice.divide(priceDiscount).multiply(numberOfPassengers).multiply(priceAdvance))
                .add(countPriceForExtraLuggage(nextPriceAdvance, extraLuggage))
                .add(countPriceForYouth(numberOfYouthPassengers, basisPrice))
                .add(countPriceForChildren(numberOfChildrenPassengers, basisPrice));
    }

    private BigDecimal countPriceForExtraLuggage(BigDecimal nextPriceAdvance, BigDecimal extraLuggage) {
        if (nextPriceAdvance.intValue() > 0) {
            extraLuggage = extraLuggage.multiply(nextPriceAdvance);
        } else {
            extraLuggage = new BigDecimal(0);
        }
        return extraLuggage;
    }

    private BigDecimal countPriceForYouth(BigDecimal numberOfYouthPassengers, BigDecimal basisPrice) {
        BigDecimal priceForYouth = new BigDecimal(0);
        if (numberOfYouthPassengers.intValue() == 1) {
            priceForYouth = basisPrice.divide(new BigDecimal(2));
        } else if (numberOfYouthPassengers.intValue() > 1) {
            priceForYouth = basisPrice.divide(new BigDecimal(2).multiply(numberOfYouthPassengers));
        }
        return priceForYouth;
    }

    private BigDecimal countPriceForChildren(BigDecimal numberOfChildrenPassengers, BigDecimal basisPrice) {
        BigDecimal priceForChildren = new BigDecimal(0);
        if (numberOfChildrenPassengers.intValue() == 1) {
            priceForChildren = basisPrice.divide(new BigDecimal(4));
        } else if (numberOfChildrenPassengers.intValue() > 1) {
            priceForChildren = basisPrice.divide(new BigDecimal(4).multiply(numberOfChildrenPassengers));
        }
        return priceForChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSupport that = (FlightSupport) o;
        return Objects.equals(flights, that.flights) &&
                Objects.equals(airports, that.airports) &&
                Objects.equals(scanner, that.scanner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flights, airports, scanner);
    }
}
