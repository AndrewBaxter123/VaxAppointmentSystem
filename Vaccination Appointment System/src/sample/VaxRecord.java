package sample;

public class VaxRecord {
    private VaxRecord nextVaxRecord;
    private Appointment app;
    private String date, time;
    private String vaxType,vaccinatorDetails;
    private String patientPPSN;

    @Override
    public String toString() {
        return "VaxRecord{" +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vaxType='" + vaxType + '\'' +
                ", vaccinatorDetails='" + vaccinatorDetails + '\'' +
                ", patientPPSN='" + patientPPSN + '\'' +
                '}';
    }

    public VaxRecord(String date, String time, String vaxType, String vaccinatorDetails, String patientPPSN) {
        this.date = date;
        this.time = time;
        this.vaxType = vaxType;
        this.vaccinatorDetails = vaccinatorDetails;
        this.patientPPSN = patientPPSN;
    }


    public VaxRecord getNextVaxRecord() {
        return nextVaxRecord;
    }

    public void setNextVaxRecord(VaxRecord nextVaxRecord) {
        this.nextVaxRecord = nextVaxRecord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVaxType() {
        return vaxType;
    }

    public void setVaxType(String vaxType) {
        this.vaxType = vaxType;
    }

    public String getVaccinatorDetails() {
        return vaccinatorDetails;
    }

    public void setVaccinatorDetails(String vaccinatorDetails) {
        this.vaccinatorDetails = vaccinatorDetails;
    }

    public String getPatientPPSN() {
        return patientPPSN;
    }

    public void setPatientPPSN(String patientPPSN) {
        this.patientPPSN = patientPPSN;
    }

}
