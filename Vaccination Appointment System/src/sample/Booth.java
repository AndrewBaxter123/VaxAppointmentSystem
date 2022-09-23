package sample;

/**
 * The type Booth.
 */
public class Booth {
    private Booth nextBooth; // this implements B from VC in your diagram in notes
    public Appointment headAppointment; // first/head for Apt for booth
    public String boothId; // add validation
    public int floorLevel;
    public boolean wheelchairAccess;


    /**
     * Instantiates a new Booth.
     *
     * @param boothId          the booth id
     * @param floorLevel       the floor level
     * @param wheelchairAccess the wheelchair access
     */
    public Booth(String boothId, int floorLevel, boolean wheelchairAccess) {
        this.nextBooth = null;
        this.headAppointment = null;

        if (Utilities.oneLetterOneNumber(boothId)) {
            this.boothId = boothId;
        } else {
            this.boothId = "invalid name";
        }

        this.floorLevel = floorLevel;
        this.wheelchairAccess = wheelchairAccess;

    }


    /**
     * Add appointment.
     *
     * @param a the a
     */
    public void addAppointment(Appointment a) {
        a.setNextAppointment(headAppointment);
        headAppointment = a;
    }

    /**
     * Find app head appointment.
     *
     * @param appToBeFound the app to be found
     * @return the appointment
     */
    public Appointment findAppHead(Appointment appToBeFound){ // if we find the app in here it returns the head.
        Appointment currentApp = headAppointment;
        while (currentApp != null){
            if (currentApp.equals(appToBeFound)){
                return headAppointment;
            }
            currentApp = currentApp.getNextAppointment();
        }
        return null;
    }


    /**
     * Print the list of appointments.
     *
     * @return the string
     */
    public String printAppointment() {
        Appointment currentAppointment = headAppointment;
        String appointmentList = "";
        while (currentAppointment != null) {
            appointmentList = appointmentList + currentAppointment.toString();
            currentAppointment = currentAppointment.getNextAppointment();
        }
        return appointmentList;
    }


    /**
     * Gets next booth.
     *
     * @return the next booth
     */
    public Booth getNextBooth() {
        return nextBooth;
    }

    /**
     * Sets next booth.
     *
     * @param nextBooth the next booth
     */
    public void setNextBooth(Booth nextBooth) {
        this.nextBooth = nextBooth;
    }

    /**
     * Gets head appointment.
     *
     * @return the head appointment
     */
    public Appointment getHeadAppointment() {
        return headAppointment;
    }

    /**
     * Sets head appointment.
     *
     * @param headAppointment the head appointment
     */
    public void setHeadAppointment(Appointment headAppointment) {
        this.headAppointment = headAppointment;
    }

    /**
     * Gets booth id.
     *
     * @return the booth id
     */
    public String getBoothId() {
        return boothId;
    }

    /**
     * Sets booth id.
     *
     * @param boothId the booth id
     */
    public void setBoothId(String boothId) {
        if (Utilities.oneLetterOneNumber(boothId)) {
            this.boothId = boothId;
        }
    }

    /**
     * Gets floor level.
     *
     * @return the floor level
     */
    public int getFloorLevel() {
        return floorLevel;
    }

    /**
     * Sets floor level.
     *
     * @param floorLevel the floor level
     */
    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    /**
     * Is wheelchair access boolean.
     *
     * @return the boolean
     */
    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    /**
     * Sets wheelchair access.
     *
     * @param wheelchairAccess the wheelchair access
     */
    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booth)) return false;
        Booth booth = (Booth) o;
        return boothId == booth.boothId && floorLevel == booth.floorLevel;
    }


    @Override
    public String toString() {
        return "Booth Id = " + String.valueOf(boothId) +
                ", It's on floor " + String.valueOf(floorLevel) +
                ", " + "Wheelchair Access= " +wheelchairAccess;
        }
    }



