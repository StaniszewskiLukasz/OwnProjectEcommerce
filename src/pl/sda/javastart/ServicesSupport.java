package pl.sda.javastart;

import java.util.ArrayList;
import java.util.List;

public class ServicesSupport {
    public List<Services> services = new ArrayList<>();

    public Long idForNextService() {
        Long id = services.stream()
                .map(Services::getId)
                .max(Long::compareTo)
                .orElse(0L);
        return ++id;
    }
}
