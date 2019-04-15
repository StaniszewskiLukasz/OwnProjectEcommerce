package pl.sda.javastart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.sda.javastart.Main.userSupport;

public class UserSupport {
    private List<User> registeredUsers = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    boolean signIn(String login, String password) {
        User user = registeredUsers.stream()
                .filter(e -> e.getLogin().equals(login) && e.getPassword().equals(password))
                .findAny()
                .orElse(null);
        Main.user = user;
        return user != null;
    }

    String loginVerification(String loginToVerification) {
        String answer;
        boolean result = registeredUsers.stream()
                .anyMatch(e -> e.getLogin().equals(loginToVerification));
        if (result) {
            answer = "dobry";
        } else {
            answer = "zły";
        }
        return answer;
    }

    String passwordVerification(String passwordToVerification) {
        String answer;
        boolean result = registeredUsers.stream()
                .anyMatch(e -> e.getPassword().equals(passwordToVerification));
        if (result) {
            answer = "dobre";
        } else {
            answer = "złe";
        }
        return answer;
    }

    public boolean registration(String login, String password) {
        boolean ifExist = registeredUsers.stream()
                .map(User::getLogin)
                .noneMatch(login::equals);
        if (ifExist) {
            User user = new User();
            user.setPassword(password);
            user.setLogin(login);
            user.setId(idForNextUser());
            System.out.println("Login i hasło zostało zapisane.");
            System.out.print("Wpisz swój adres email: ");
            String email = SCANNER.next(); // tutaj stworzę metodę dodawania maila do usera, gdy próbowałem to zrobić na userze wyskakiwał nullpointer bo chyba chciałem operować na steMail a tam był null do tej pory
            user.setEmail(email);
            System.out.print("Wpisz imie: ");
            String name = SCANNER.next();
            user.setFirstName(name);
            System.out.print("Wpisz nazwisko: ");
            String surname = SCANNER.next();
            user.setSurname(surname);
            registeredUsers.add(user);
            System.out.println("Rejestracja zakończyła się sukcesem");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
        }else{
            boolean loginVerification = userSupport.loginIfExist(login);
            boolean passwordVerification = userSupport.passwordIfExist(password);
            if (loginVerification) {
                System.out.println("Ten login jest zajęty.");
            } else if (passwordVerification) {
                System.out.println("To hasło jest zajęte");
            }
        }
        return ifExist;
    }

    private Long idForNextUser() {
        Long id = registeredUsers.stream()
                .map(User::getId)
                .max(Long::compareTo)
                .orElse(0L);
        return ++id;
    }

    public boolean loginIfExist(String loginToVerification) {
        return registeredUsers.stream()
                .anyMatch(e -> e.getLogin().equals(loginToVerification));
    }

    public boolean passwordIfExist(String passwordToVerification) {
        return registeredUsers.stream()
                .anyMatch(e -> e.getLogin().equals(passwordToVerification));
    }
}
