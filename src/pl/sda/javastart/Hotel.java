package pl.sda.javastart;

public class Hotel extends Accommodation {
    private Integer countOfFreeRooms;
    private Integer countOfFreeBeds;
    private boolean airConditioning;
    private boolean lift;

    public Integer getCountOfFreeRooms() {
        return countOfFreeRooms;
    }

    public void setCountOfFreeRooms(Integer countOfFreeRooms) {
        this.countOfFreeRooms = countOfFreeRooms;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }

    public Integer getCountOfFreeBeds() {
        return countOfFreeBeds;
    }

    public void setCountOfFreeBeds(Integer countOfFreeBeds) {
        this.countOfFreeBeds = countOfFreeBeds;
    }
}
