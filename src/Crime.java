import java.util.Date;

public class Crime {
    public static final int CRIME_TYPE_MOTOR_ROBBERY = 1;
    public static final int CRIME_TYPE_ROBBERY_FROM_HOUSE = 2;
    public static final int CRIME_TYPE_MURDER = 3;
    public static  final int CRIME_TYPE_OTHER_VIOLATIONS = 4;

    private Point coordinates;
    private int crimeType;
    private int id; /*** id of person whom reported this crime ***/
    private Date timeOfCrime;

    /*** maybe pictureOfCrime and other properties ***/


    public Crime(Point coordinates, int crimeType, int id, Date timeOfCrime) {
        this.coordinates = coordinates;
        this.crimeType = crimeType;
        this.id = id;
        this.timeOfCrime = timeOfCrime;
    }


    @Override
    public String toString() {
        return "Crime{" +
                "coordinates=" + coordinates +
                ", CrimeType=" + crimeType +
                ", id=" + id +
                ", timeOfCrime=" + timeOfCrime +
                '}';
    }


    public String toClientString(){
        return coordinates.toClientString() + "c" + String.valueOf(this.crimeType);
    }


    public static int getCrimeTypeMotorRobbery() {
        return CRIME_TYPE_MOTOR_ROBBERY;
    }

    public static int getCrimeTypeRobberyFromHouse() {
        return CRIME_TYPE_ROBBERY_FROM_HOUSE;
    }

    public static int getCrimeTypeMurder() {
        return CRIME_TYPE_MURDER;
    }

    public static int getCrimeTypeOtherViolations() {
        return CRIME_TYPE_OTHER_VIOLATIONS;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public int getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(int crimeType) {
        this.crimeType = crimeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeOfCrime() {
        return timeOfCrime;
    }

    public void setTimeOfCrime(Date timeOfCrime) {
        this.timeOfCrime = timeOfCrime;
    }
}
