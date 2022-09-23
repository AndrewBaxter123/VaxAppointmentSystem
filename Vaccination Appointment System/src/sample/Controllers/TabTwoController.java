package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.Patient;
import sample.VaxRecord;

/**
 * The type Tab two controller.
 */
public class TabTwoController {
     //Patients Tab
    public Patient selectedPatient;
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public Controller controller;

    @FXML
    private ListView<String> PatientList, PatientVaxRecordList;
    @FXML
    private Tab tabPatient;
    @FXML
    private Button btnAddPatient, btnEditPatient, btnDeletePatient;
    @FXML
    private TextField
            patientNameField, ppsNumField, emailField, eircodePatientField, phoneNumField;
    @FXML
    private DatePicker DOBPicker;
    @FXML
    private CheckBox checkBoxWheelChairUser;


    /**
     * Get patient by index patient.
     *
     * @param index the index
     * @return the patient
     */
    public Patient getPatientByIndex(int index){
        Patient current = Main.headP;

        int count = 0;
        while (current != null && count < index) { // while current is != to null and count is less than index
            current = current.getNextPatient();
            count++;

        }
        return current;
    }


    /**
     * Add patient.
     *
     * @param p the p
     */
    public void addPatient(Patient p) {
        p.setNextPatient(Main.headP); // set next to current head and set this vc to the passed in v
        Main.headP = p;

        try {
            Main.savePatient();
        } catch(Exception e){
            System.out.println("Patient Problem!" + e);
        }
    }

    /**
     * Edit patient.
     *
     * @param index the index
     */
    public void editPatient(int index) {
        Patient current = Main.headP;

        int count = 0;
        while (current != null && count < index) { // while current is not null and count is less than index
            current = current.getNextPatient();
            count++;

        }
        current.setName(patientNameField.getText());
        current.setPpsNumber(ppsNumField.getText());
        current.setEmail(emailField.getText());
        current.setEircode(eircodePatientField.getText());
        current.setPhoneNumber(phoneNumField.getText());
        current.setDateOfBirth(DOBPicker.getValue().toString());
        current.setWheelchairUser(checkBoxWheelChairUser.isSelected());

        try {
            Main.savePatient();
        } catch(Exception e){
            System.out.println("Patient Problem!" + e);
        }
    }

    /**
     * Delete patient.
     *
     * @param index the index
     */
    public void deletePatient(int index) {
        Patient current = Main.headP;
        if (index == 0) {
            Main.headP = current.getNextPatient();
        } else {
            int count = 0;
            while (count < index - 1 && current != null) { // run while count is less than index and current is not null
                current = current.getNextPatient();
                count++;
            }
            current.setNextPatient(current.getNextPatient().getNextPatient()); // stopped at 1 before delete, gets the 1 after the one we want to delete

        }
        try {
            Main.savePatient();
        } catch(Exception e){
            System.out.println("Patient Problem!" + e);
        }
    }

    /**
     * Print patient list.
     *
     * @return the string
     */
    public String printPatientList() {
        Patient current = Main.headP;
        String patientList = "";
        while (current != null) {
            patientList = patientList + current.toString() + "\n";
            current = current.getNextPatient();
        }
        return patientList;
    }


    /**
     * Add patient button and refreshes lists aswell
     */
    public void addPatientButton() {

        Patient p = new Patient(patientNameField.getText(),
                ppsNumField.getText(),
                emailField.getText(),
                eircodePatientField.getText(),
                phoneNumField.getText(),
                DOBPicker.getValue().toString(),
                checkBoxWheelChairUser.isSelected());
        addPatient(p);
        controller.tabThreeController.refreshPatientAppList();
        refreshPatientList();

    }

    /**
     * Edit patient button and refreshes lists aswell
     */
    public void editPatientButton() {

        if (PatientList.getSelectionModel().getSelectedIndex() >= 0) {
            editPatient(PatientList.getSelectionModel().getSelectedIndex());
            controller.tabThreeController.refreshPatientAppList();
            refreshPatientList();

        }
    }

    /**
     * Delete patient button and refreshes lists as well
     */
    public void deletePatientButton() {
        if (PatientList.getSelectionModel().getSelectedIndex() >= 0) {
            deletePatient(PatientList.getSelectionModel().getSelectedIndex());
            controller.tabThreeController.refreshPatientAppList();
            refreshPatientList();

        }
    }

    /**
     * Refresh patient list.
     */
    public void refreshPatientList() { //might need to call this in tab three when completing
        PatientList.getItems().clear();
        Patient current = Main.headP;
        String currentPatientList = "";
        while (current != null) {
            PatientList.getItems().add(current.toString());
            current = current.getNextPatient(); // going from next to next
        }
    }

    /**
     * Refresh patient vax record list.
     *
     * @param p the p
     */
    public void refreshPatientVaxRecordList(Patient p) {
        PatientVaxRecordList.getItems().clear();
        VaxRecord currentRecord = p.getHeadVaxRecord();
        String RecordList = "";
        while (currentRecord != null) {
            currentRecord = currentRecord.getNextVaxRecord(); // going from next to next until null.
            PatientVaxRecordList.getItems().add(p.getHeadVaxRecord().toString());
        }
    }

    /**
     * Find's the selected Patient and updates the list to show that Patients Vax Record.
     */
    public void findPatientRecord(){
        selectedPatient = getPatientByIndex(PatientList.getSelectionModel().getSelectedIndex());
        refreshPatientVaxRecordList(selectedPatient);
    }
}


