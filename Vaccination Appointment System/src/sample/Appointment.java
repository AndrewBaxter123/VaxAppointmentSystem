package sample;

import java.util.Objects;

/**
 * The type Appointment.
 */
public class Appointment {
    private Appointment nextAppointment;
    private String date, time;
    private String vaxType,vaccinatorDetails;
    private String patientPPSN;
    private boolean isComplete = false;


    /**
     * Instantiates a new Appointment.
     *
     * @param date              the date
     * @param time              the time
     * @param vaxType           the vax type
     * @param vaccinatorDetails the vaccinator details
     * @param patientPPSN       the patient ppsn
     * @param isComplete        the is complete
     */
    public Appointment(String date, String time, String vaxType, String vaccinatorDetails, String patientPPSN, boolean isComplete) {
        this.date = date;
        this.time = time;
        this.vaxType = vaxType;
        this.vaccinatorDetails = vaccinatorDetails;
        if (Utilities.validPPS(patientPPSN)) {
            this.patientPPSN = patientPPSN;
        } else {
            this.patientPPSN = "invalid PPSN, 7 digits then 2 characters needed";
        }
        this.isComplete = isComplete;
    }

    /**
     * Add patient.
     *
     * @param p the p
     */
    public void addPatient(Patient p){
        p.setNextPatient(Main.headP); //
        Main.headP = p;
    }

    /**
     * Search patients patient.
     *
     * @param name the name
     * @return the patient
     */
    public Patient searchPatients(String name) {
        Patient current = Main.headP;
        while (current.getName() != name) {
            if (current.getNextPatient() == null) { // got to end without match
                return null;
            } else {
                current = current.getNextPatient();
            }
        }
        return current;
    }


    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets vax type.
     *
     * @return the vax type
     */
    public String getVaxType() {
        return vaxType;
    }

    /**
     * Sets vax type.
     *
     * @param vaxType the vax type
     */
    public void setVaxType(String vaxType) {
        this.vaxType = vaxType;
    }

    /**
     * Gets vaccinator details.
     *
     * @return the vaccinator details
     */
    public String getVaccinatorDetails() {
        return vaccinatorDetails;
    }

    /**
     * Sets vaccinator details.
     *
     * @param vaccinatorDetails the vaccinator details
     */
    public void setVaccinatorDetails(String vaccinatorDetails) {
        this.vaccinatorDetails = vaccinatorDetails;
    }

    /**
     * Gets patient ppsn.
     *
     * @return the patient ppsn
     */
    public String getPatientPPSN() {
        return patientPPSN;
    }

    /**
     * Sets patient ppsn.
     *
     * @param patientPPSN the patient ppsn
     */
    public void setPatientPPSN(String patientPPSN) {
        if (Utilities.validPPS(patientPPSN)) {
            this.patientPPSN = patientPPSN;
        }
    }

    /**
     * Is complete boolean.
     *
     * @return the boolean
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets complete.
     *
     * @param complete the complete
     */
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return date == that.date && time == that.time && isComplete == that.isComplete && Objects.equals(nextAppointment, that.nextAppointment) && Objects.equals(vaxType, that.vaxType) && Objects.equals(vaccinatorDetails, that.vaccinatorDetails) && Objects.equals(patientPPSN, that.patientPPSN);
    }


    @Override
    public String toString() {
        return "Appointment for -" +
                " patientPPSN='" + patientPPSN + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", vaxType='" + vaxType + '\'' +
                ", vaccinatorDetails='" + vaccinatorDetails + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }

    /**
     * Gets next appointment.
     *
     * @return the next appointment
     */
    public Appointment getNextAppointment() {
        return nextAppointment;
    }

    /**
     * Sets next appointment.
     *
     * @param nextAppointment the next appointment
     */
    public void setNextAppointment(Appointment nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

}
