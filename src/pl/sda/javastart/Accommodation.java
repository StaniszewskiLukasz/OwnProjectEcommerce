package pl.sda.javastart;

public class Accommodation extends Services {
    private String city;
    private Integer daysToCheckOut;
    private Integer countOfStars;
    private boolean breakfast;
    private boolean beachfront;
    private Integer freeParkingPlaces;
    private boolean petsAllowed;
    private boolean wiFi;
    private Integer distanceFromCityCentre;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDaysToCheckOut() {
        return daysToCheckOut;
    }

    public void setDaysToCheckOut(Integer daysToCheckOut) {
        this.daysToCheckOut = daysToCheckOut;
    }

    public Integer getCountOfStars() {
        return countOfStars;
    }

    public void setCountOfStars(Integer countOfStars) {
        this.countOfStars = countOfStars;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isBeachfront() {
        return beachfront;
    }

    public void setBeachfront(boolean beachfront) {
        this.beachfront = beachfront;
    }

    public Integer getFreeParkingPlaces() {
        return freeParkingPlaces;
    }

    public void setFreeParkingPlaces(Integer freeParkingPlaces) {
        this.freeParkingPlaces = freeParkingPlaces;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isWiFi() {
        return wiFi;
    }

    public void setWiFi(boolean wiFi) {
        this.wiFi = wiFi;
    }

    public Integer getDistanceFromCityCentre() {
        return distanceFromCityCentre;
    }

    public void setDistanceFromCityCentre(Integer distanceFromCityCentre) {
        this.distanceFromCityCentre = distanceFromCityCentre;
    }

    @Override
    public String toString() {
        return city;
    }
}
