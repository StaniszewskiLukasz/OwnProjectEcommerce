package pl.sda.javastart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AccommodationSupport {
    private String placeToCheck = null; // fixme czy to jest dobra opcja, że wrzucone jest to jako zmienna w klasie a nie w metodzie?
    private boolean placeSearching = false; // fixme czy to jest dobra opcja?
    private String[] names = new String[]{"Róża", "Pod Dzikiem", "Na Dziku", "SmródBrudUbóstwoByHilton", "TrumpTower", "KaczyńskiTower"};
    private List<Accommodation> cities = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<Homestays> homestays = new ArrayList<>();
    private List<Camping> campings = new ArrayList<>();
    private ServicesSupport servicesSupport = new ServicesSupport();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    {
        createCitiesOfAccommodation();
        createPlacesAtHotel();
        createPlacesAtHomestays();
        createPlacesAtCamping();
    }


    private void createCitiesOfAccommodation() {
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
                Accommodation place = new Accommodation();
                place.setCity(line.toUpperCase()); //fixme
                cities.add(place);
            }
        }
    }

    public void generalMethodAccommodation() {
        System.out.println("Lista miast jakie możesz odwiedzić");
        System.out.println(cities.toString());
        System.out.println("Gdzie szukasz noclegu?");
        placeToCheck = scanner.next();
        for (Accommodation city : cities) {
            if (city.toString().trim().equalsIgnoreCase(placeToCheck)) {
                placeSearching = true;
                break;
            }
        }
        if (placeSearching) {
            System.out.println("Czego szukasz:");
            System.out.print("1.Hotelu");
            System.out.print("          ");
            System.out.print("2.Pensjonatu");
            System.out.print("          ");
            System.out.print("3.Kempingu");
            System.out.print("          ");
            Integer choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hotelReservation();
                    break;
                case 2:
                    homestaysReservation();
                    break;
                case 3:
                    campingReservation();
                    break; // fixme czy tu powinien być break?
                default:
                    System.out.println("Wybrałeś nieprawidłową opcję.");
            }
        } else {
            System.out.println("Nie mamy dla Ciebie noclegu w tym mieście. Wybierz inne miasto.");
            generalMethodAccommodation();
        }
    }

    private void createPlacesAtHotel() {
        for (Long i = 0L; i < 25; i++) {
            if (i % 2 == 0) {
                Hotel hotel = new Hotel();
                hotel.setId(servicesSupport.idForNextService());
                hotel.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                hotel.setName("Hotel " + hotel.getCity());
                hotel.setCountOfFreeRooms(i.intValue() * 2);
                hotel.setDaysToCheckOut(i.intValue() * 3);
                hotel.setCountOfFreeBeds(hotel.getCountOfFreeRooms() * 2);
                hotel.setCountOfStars(random.nextInt(5));
                hotel.setBreakfast(true);
                if (hotel.getCity().equalsIgnoreCase("Barcelona") || hotel.getCity().equalsIgnoreCase("Ejlat")) {
                    hotel.setBeachfront(true);
                } else {
                    hotel.setBeachfront(false);
                }
                if (i > 15) {
                    hotel.setFreeParkingPlaces(i.intValue() - 12);
                } else {
                    hotel.setFreeParkingPlaces(i.intValue());
                }
                hotel.setPetsAllowed(false);
                hotel.setWiFi(true);
                hotel.setDistanceFromCityCentre(35 - i.intValue());
                hotel.setAirConditioning(true);
                hotel.setLift(false);
                hotels.add(hotel);
                servicesSupport.services.add(hotel);
            }else{
                Hotel hotel = new Hotel();
                hotel.setId(servicesSupport.idForNextService());
                hotel.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                hotel.setName("Hotel " + names[random.nextInt(5)]);
                hotel.setCountOfFreeRooms(i.intValue() * 3);
                hotel.setDaysToCheckOut(i.intValue());
                hotel.setCountOfFreeBeds(hotel.getCountOfFreeRooms() * 2);
                hotel.setCountOfStars(random.nextInt(5));
                hotel.setBreakfast(false);
                hotel.setBeachfront(false);
                if (i > 15) {
                    hotel.setFreeParkingPlaces(i.intValue() - 14);
                } else {
                    hotel.setFreeParkingPlaces(i.intValue());
                }
                hotel.setPetsAllowed(true);
                hotel.setWiFi(true);
                hotel.setDistanceFromCityCentre(25 - i.intValue());
                hotel.setAirConditioning(false);
                hotel.setLift(true);
                hotels.add(hotel);
                servicesSupport.services.add(hotel);
            }
        }
    }

    private void createPlacesAtHomestays() {
        for (Long i = 0L; i < 25; i++) {
            if (i % 2 == 0) {
                Homestays homestay = new Homestays();
                homestay.setId(servicesSupport.idForNextService());
                homestay.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                homestay.setName("Homestay " + homestay.getCity());
                homestay.setCountOfFreeRooms(i.intValue()+1);
                homestay.setDaysToCheckOut(i.intValue() * 3);
                homestay.setCountOfFreeBeds(homestay.getCountOfFreeRooms() * 2);
                homestay.setCountOfStars(random.nextInt(3));
                homestay.setBreakfast(false);
                if (homestay.getCity().equalsIgnoreCase("Barcelona") || homestay.getCity().equalsIgnoreCase("Ejlat")) {
                    homestay.setBeachfront(true);
                } else {
                    homestay.setBeachfront(false);
                }
                homestay.setFreeParkingPlaces(5);
                homestay.setPetsAllowed(false);
                homestay.setWiFi(true);
                homestay.setDistanceFromCityCentre(random.nextInt(7));
                homestay.setAirConditioning(false);
                homestays.add(homestay);
                servicesSupport.services.add(homestay);
            }else{
                Homestays homestay = new Homestays();
                homestay.setId(servicesSupport.idForNextService());
                homestay.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                homestay.setName("Homestay " + names[random.nextInt(5)]);
                homestay.setCountOfFreeRooms(i.intValue());
                homestay.setDaysToCheckOut(i.intValue());
                homestay.setCountOfFreeBeds(homestay.getCountOfFreeRooms() * 2);
                homestay.setCountOfStars(random.nextInt(3));
                homestay.setBreakfast(false);
                homestay.setBeachfront(false);
                homestay.setFreeParkingPlaces(3);
                homestay.setPetsAllowed(true);
                homestay.setWiFi(true);
                homestay.setDistanceFromCityCentre(25 - i.intValue());
                homestay.setAirConditioning(false);
                homestays.add(homestay);
                servicesSupport.services.add(homestay);
            }
        }
    }

    private void createPlacesAtCamping() {
        for (Long i = 0L; i < 25; i++) {
            if (i % 2 == 0) {
                Camping camping = new Camping();
                camping.setId(servicesSupport.idForNextService());
                camping.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                camping.setName("Homestay " + camping.getCity());
                camping.setCountOfFreePlaces(i.intValue()+25);
                camping.setDaysToCheckOut(i.intValue() * 15);
                camping.setCountOfStars(random.nextInt(4));
                camping.setBreakfast(false);
                if (camping.getCity().equalsIgnoreCase("Barcelona") || camping.getCity().equalsIgnoreCase("Ejlat")) {
                    camping.setBeachfront(true);
                } else {
                    camping.setBeachfront(false);
                }
                camping.setFreeParkingPlaces(65);
                camping.setPetsAllowed(true);
                camping.setWiFi(true);
                camping.setDistanceFromCityCentre(random.nextInt(15));
                campings.add(camping);
                servicesSupport.services.add(camping);
            }else{
                Camping camping = new Camping();
                camping.setId(servicesSupport.idForNextService());
                camping.setCity(cities.get(random.nextInt(cities.size())).toString().trim());
                camping.setName("Homestay " + names[random.nextInt(5)]);
                camping.setDaysToCheckOut(i.intValue()+45);
                camping.setCountOfStars(random.nextInt(3));
                camping.setBreakfast(false);
                camping.setBeachfront(false);
                camping.setFreeParkingPlaces(85);
                camping.setPetsAllowed(false);
                camping.setWiFi(true);
                camping.setDistanceFromCityCentre(25 - i.intValue());
                campings.add(camping);
                servicesSupport.services.add(camping);
            }
        }
    }

    private void hotelReservation() {
//        String yes = "tak";  // fixme zobaczymy jak zadziała bez tego hasNext
//        Scanner scan = new Scanner(yes);
        Hotel hotel = new Hotel();
        hotel.setCity(placeToCheck);
        System.out.println("Podaj liczbę dni");
        hotel.setDaysToCheckOut(scanner.nextInt());
        System.out.println("Podaj liczbę gości");
        hotel.setCountOfFreeBeds(scanner.nextInt());
        System.out.println("Podaj liczbę pokoij");
        hotel.setCountOfFreeRooms(scanner.nextInt());
        System.out.println("Podaj liczbę gwiazdek jaką ma posiadać wybrany hotel");
        hotel.setCountOfStars(scanner.nextInt());
        System.out.println("Czy śniadanie ma być wliczone w cenę? \"Tak\" lub \"Nie\"");
        hotel.setBreakfast(scanner.hasNext("Tak"));
        System.out.println("Lokalizacja nad wodą? \"Tak\" lub \"Nie\"");
        hotel.setBeachfront(scanner.hasNext("tak"));
        System.out.println("Podaj liczbę miejsc parkingowych jakie chcesz zarezerwować");
        hotel.setFreeParkingPlaces(scanner.nextInt());
        System.out.println("Chcesz zabrać swojego pupila? \"Tak\" lub \"Nie\"");
        hotel.setPetsAllowed(scanner.hasNext("Tak"));
        System.out.println("Potrzebujesz WiFi? \"Tak\" lub \"Nie\"");
        hotel.setWiFi(scanner.hasNext("Tak"));
        System.out.println("Czy hotel ma posiadać windę? \"Tak\" lub \"Nie\"");
        hotel.setLift(scanner.hasNext("Tak"));
        System.out.println("Czy Twój pokój ma mieć klimatyzację? \"Tak\" lub \"Nie\"");
        hotel.setAirConditioning(scanner.hasNext("Tak"));
        System.out.println("Jak najdalej od centrum ma znajdować się hotel?");
        hotel.setDistanceFromCityCentre(scanner.nextInt());
    }

    private void homestaysReservation() {
    }

    private void campingReservation() {
    }

//    @Override
//    public String toString() {
//        return "AccommodationSupport{" +
//                "cities=" + cities +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationSupport that = (AccommodationSupport) o;
        return placeSearching == that.placeSearching &&
                Objects.equals(placeToCheck, that.placeToCheck) &&
                Arrays.equals(names, that.names) &&
                Objects.equals(cities, that.cities) &&
                Objects.equals(hotels, that.hotels) &&
                Objects.equals(homestays, that.homestays) &&
                Objects.equals(campings, that.campings) &&
                Objects.equals(servicesSupport, that.servicesSupport) &&
                Objects.equals(scanner, that.scanner);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(placeToCheck, placeSearching, cities, hotels, homestays, campings, servicesSupport, scanner);
        result = 31 * result + Arrays.hashCode(names);
        return result;
    }
}
