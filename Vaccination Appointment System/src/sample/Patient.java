package sample;

import java.util.Objects;

/**
 * The type Patient.
 */
public class Patient {
    private Patient nextPatient;
    private VaxRecord headVaxRecord;
    private String name;
    private String ppsNumber;
    private String email;
    private String eircode;
    private String dateOfBirth;
    private String phoneNumber;
    private boolean wheelchairUser;


    /**
     * Instantiates a new Patient.
     *
     * @param name           the name
     * @param ppsNumber      the pps number
     * @param email          the email
     * @param eircode        the eircode
     * @param phoneNumber    the phone number
     * @param dateOfBirth    the date of birth
     * @param wheelchairUser the wheelchair user
     */
    public Patient(String name, String ppsNumber, String email, String eircode, String phoneNumber,String dateOfBirth, boolean wheelchairUser) {
            this.name = name;
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        } else {
            this.ppsNumber = "invalid PPS, 7 digits then 2 chars needed";
        }
        this.email = email;
        if (Utilities.validEircode(eircode)) {
            this.eircode = eircode;
        } else {
            this.eircode = "invalid";
        }
        this.dateOfBirth = dateOfBirth;
        if (Utilities.onlyContainsNumbers(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "invalid number, needs to be 10 digits";
        }

        this.wheelchairUser = wheelchairUser;
    }

    /**
     * Add patient vax record.
     *
     * @param p      the p
     * @param record the record
     */
    public void addPatientVaxRecord(Patient p, VaxRecord record){
        record.setNextVaxRecord(p.headVaxRecord);
        p.headVaxRecord = record;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return wheelchairUser == patient.wheelchairUser && Objects.equals(nextPatient, patient.nextPatient) && Objects.equals(headVaxRecord, patient.headVaxRecord) && Objects.equals(ppsNumber, patient.ppsNumber) && Objects.equals(email, patient.email) && Objects.equals(name, patient.name) && Objects.equals(eircode, patient.eircode) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(phoneNumber, patient.phoneNumber);
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
            this.name = name;
    }

    /**
     * Gets pps number.
     *
     * @return the pps number
     */
    public String getPpsNumber() {
        return ppsNumber;
    }

    /**
     * Sets pps number.
     *
     * @param ppsNumber the pps number
     */
    public void setPpsNumber(String ppsNumber) {
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        }
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets eircode.
     *
     * @return the eircode
     */
    public String getEircode() {
        return eircode;
    }

    /**
     * Sets eircode.
     *
     * @param eircode the eircode
     */
    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        if (Utilities.onlyContainsNumbers(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Is wheelchair user boolean.
     *
     * @return the boolean
     */
    public boolean isWheelchairUser() {
        return wheelchairUser;
    }

    /**
     * Sets wheelchair user.
     *
     * @param wheelchairUser the wheelchair user
     */
    public void setWheelchairUser(boolean wheelchairUser) {
        this.wheelchairUser = wheelchairUser;
    }

    /**
     * Gets next patient.
     *
     * @return the next patient
     */
    public Patient getNextPatient() {
        return nextPatient;
    }

    /**
     * Sets next patient.
     *
     * @param nextPatient the next patient
     */
    public void setNextPatient(Patient nextPatient) {
        this.nextPatient = nextPatient;
    }

    /**
     * Gets head vax record.
     *
     * @return the head vax record
     */
    public VaxRecord getHeadVaxRecord() {
        return headVaxRecord;
    }

    /**
     * Sets head vax record.
     *
     * @param headVaxRecord the head vax record
     */
    public void setHeadVaxRecord(VaxRecord headVaxRecord) {
        this.headVaxRecord = headVaxRecord;
    }

    @Override
    public String toString() {
        return "name=" + name + '\'' +
                ", ppsNumber='" + ppsNumber + '\'' +
                ", email='" + email + '\'' +
                ", eircode='" + eircode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", wheelchairUser='" + wheelchairUser + '\'' +
                '}';
    }
}
