package pl.sda.javastart;

import java.util.Scanner;

public class Main {
    public static User user;
    private static final Scanner SCANNER = new Scanner(System.in);
    public static UserSupport userSupport = new UserSupport();
    public static FlightSupport flightSupport = new FlightSupport();
    public static AccommodationSupport accommodationSupport = new AccommodationSupport();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        if (user == null) {
            initialMenu();
        } else {
            afterSignInMenu();
        }
    }

    private static void initialMenu() {
        System.out.print("Wybierz opcję: ");
        System.out.println("1. Zaloguj się");
        System.out.print("               ");
        System.out.println("2. Zarejestruj się");
        System.out.print("               ");
        System.out.println("3. Wyjście");
        Integer choice = SCANNER.nextInt();
        switch (choice) {
            case 1:
                signIn();
                break;
            case 2:
                registration();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Wybrałeś nieprawidłową opcję.");
        }
        start();
    }

    private static void signIn() {
        System.out.println("LOGOWANIE");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Podaj login:");
        String login = SCANNER.next();
        System.out.println("Podaj hasło:");
        String password = SCANNER.next();
        boolean ifExist = userSupport.signIn(login, password);
        if (ifExist) {
            System.out.println("ZALOGOWAŁES SIĘ");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("Witaj: " + user.getFirstName());
        } else {
            String loginVerification = userSupport.loginVerification(login);
            String passwordVerification = userSupport.passwordVerification(password);
            System.out.println("Podałeś " + loginVerification + " login oraz " + passwordVerification + " hasło. Spróbuj ponownie.");
        }
    }

    private static void registration() {
        System.out.println("REJESTRACJA");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Wpisz login:");
        String login = SCANNER.next();
        System.out.println("Wpisz hasło:");
        String password = SCANNER.next();
        boolean ifExist = userSupport.registration(login, password);//poniżej dużo szarzyzny, ten kod wyrzuciłem do metody w UserSupport, ale czy tak jest GIT???
//        if (ifExist) { // todo zapytać o poprawność
//            System.out.println("Login i hasło zostało zapisane.");
//            System.out.print("Wpisz swój adres email: ");
//            String email = SCANNER.next(); // tutaj stworzę metodę dodawania maila do usera, gdy próbowałem to zrobić na userze wyskakiwał nullpointer bo chyba chciałem operować na steMail a tam był null do tej pory
//            Long lastId = user.getId();
//            userSupport.addingEmail(lastId, email);
//            System.out.print("Wpisz imie: ");
//            String name = SCANNER.next();
//            user.setFirstName(name);
//            System.out.print("Wpisz nazwisko: ");
//            String surname = SCANNER.next();
//            user.setSurname(surname);
//        } else {
//            boolean loginVerification = userSupport.loginIfExist(login);
//            boolean passwordVerification = userSupport.passwordIfExist(password);
//            if (loginVerification) {
//                System.out.println("Ten login jest zajęty.");
//            } else if (passwordVerification) {
//                System.out.println("To hasło jest zajęte");
//            }
//        }
    }

    public static void afterSignInMenu() {
        System.out.print("1.LOTY");
        System.out.print("        ");
        System.out.print("2.NOCLEGI");
        System.out.print("        ");
        System.out.print("3.WYNAJEM");
        System.out.print("        ");
        System.out.print("4.ATRAKCJE");
        System.out.print("        ");
        System.out.print("5.DODAJ");
        System.out.print("        ");
        System.out.print("6.DODAJ");
        System.out.print("        ");
        System.out.println("7.WYLOGUJ");
        System.out.print("                               SAMOCHODU");
        System.out.print("        NA MIEJSCU");
        System.out.print("        OGŁOSZENIE");
        System.out.println("     OPINIĘ"); //fixme dodać wyloguj i do każdej metody powrót do afterSignMenu by program sam się nie kończył
        Integer choice = SCANNER.nextInt();
        switch (choice) {
            case 1:
                flightSupport.generalMethodFlights();
                break;
            case 2:
                accommodationSupport.generalMethodAccommodation();
                break;
        }


    }

//    private static void


}
