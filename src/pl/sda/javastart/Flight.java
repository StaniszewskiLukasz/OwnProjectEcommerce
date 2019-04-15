package pl.sda.javastart;

import java.util.Objects;

public class Flight extends Services{
    private String airport;

    public String view(){
        return name +" " + price;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return airport ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(airport, flight.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport);
    }
}
