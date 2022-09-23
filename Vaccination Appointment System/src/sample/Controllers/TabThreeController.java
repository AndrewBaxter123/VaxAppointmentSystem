package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.*;

/**
 * The type Tab three controller.
 */
public class TabThreeController { //Appointments
    public Patient selectedPatient;


    @FXML
    private ListView<Appointment> appList;
    @FXML
    private ListView<Patient> listPatientsApp;
    @FXML
    private Button appAdd, appDelete,btnCompleteApp,btnAppSearch,btnAppSearchCompleted;;
    @FXML
    private TextField  appTime, appVaxType, appVaccinator, appPatientPPSN, VCAppSearch, VaxTypeSearch;
    @FXML
    private DatePicker appDatePicker;
    @FXML
    private CheckBox appCompleteAppointment,appCompleteAppointmentSEARCH;

//    /**
//     * Add appointment.
//     *
//     * @param app the app
//     */
//    public void addAppointment(Appointment app) {
//        app.setNextAppointment(Main.headVC.getHeadBooth().headAppointment); // set next to current head and set this app to the passed in app
//        Main.headVC.getHeadBooth().headAppointment = app;
//    }

    /**
     * Get patient by index patient, needed for another method.
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
     * Match patient to a vax centre, by eircode.
     *
     * @param p the p
     * @return the vax centre
     */
    public VaxCentre matchPatientToVC(Patient p){
//boolean eircodeMatch = false;
//p = getPatientByIndex(listPatientsApp.getSelectionModel().getSelectedIndex());

        VaxCentre vc = Main.headVC;
        while (vc != null && !p.getEircode().toUpperCase().substring(0, 2).equals(vc.getEircode().toUpperCase().substring(0, 2))) //
            vc=vc.getNextVC();

//            if (vc!=null) { //p.getEircode().toUpperCase().substring(0, 2).equals(vc.getEircode().toUpperCase().substring(0, 2))) {
//                eircodeMatch = true;
//            }
//        return eircodeMatch;

        return vc;
    }

    /**
     * Match patient that is a wheelchair user to a booth that has wheelchair access
     *
     * @param p  the p
     * @param vc the vc
     * @return the booth
     */
    public Booth matchPatientToBooth(Patient p, VaxCentre vc){
//boolean boothMatch = false;
//p = getPatientByIndex(listPatientsApp.getSelectionModel().getSelectedIndex());
        Booth b = vc.getHeadBooth();

        while (b != null && b.wheelchairAccess != p.isWheelchairUser())
            b=b.getNextBooth();

//if (b.wheelchairAccess == p.isWheelchairUser()) {
//boothMatch = true;
//
//            }

            return b;
        }

    /**
     * Add app button.
     * if it passes the eircode match and wheelchair match methods, it gets successfully added.
     * or else ask the user to adjust it's eircode to a location that could also suit.
     */
    public void addAppButton() {
            Patient p = getPatientByIndex(listPatientsApp.getSelectionModel().getSelectedIndex());
            VaxCentre vc = matchPatientToVC(p);
            Booth b;
            if (vc != null) {
                b = matchPatientToBooth(p, vc);
                if (b != null) {
                    Appointment app = new Appointment(appDatePicker.getValue().toString(),
                            appTime.getText(),
                            appVaxType.getText(),
                            appVaccinator.getText(),
                            appPatientPPSN.getText(),
                            appCompleteAppointment.isSelected());
                    b.addAppointment(app);
                    app.setPatientPPSN(p.getPpsNumber());
                    refreshAppListShowAll();
                }
            }
        }

    /**
     * Complete app button.
     * finds the app patients ppsn, set it to complete and when it updates to complete, a new Vax record is added to that patient.
     */
    public void completeAppButton(){
            //Appointment app = getAppointmentByIndex(appList.getSelectionModel().getSelectedIndex());
            Appointment app = appList.getSelectionModel().getSelectedItem();

            Patient p = Main.findP(app.getPatientPPSN()); // finds the PPSN from the app

            app.setComplete(true);

            if(app.isComplete()){

                p.addPatientVaxRecord(p,new VaxRecord(app.getDate(),
                        app.getTime(),
                        app.getVaxType(),
                        app.getVaccinatorDetails(),
                        app.getPatientPPSN()));

                refreshAppListShowAll();
                System.out.println(p.getHeadVaxRecord().getPatientPPSN());
                controller.tabTwoController.refreshPatientList();
                controller.tabTwoController.refreshPatientVaxRecordList(p);
            }
        }


    /**
     * Refresh patient app list.
     */
    public void refreshPatientAppList() {
        listPatientsApp.getItems().clear();
        Patient current = Main.headP;
        String currentVCList = "";
        while (current != null) {
            listPatientsApp.getItems().add(current);
            current = current.getNextPatient();
        }
    }

    /**
     * Refreshes the appointment list as apps are added.
     */
    public void refreshAppListShowAll() {
        appList.getItems().clear();
        VaxCentre vc = Main.headVC;
        while (vc != null) {
            Booth b = vc.getHeadBooth();
            while (b != null) {
                Appointment current = b.headAppointment;
                while (current != null) {
                    String currentAppList = "";

                    appList.getItems().add(current);

                    current = current.getNextAppointment();
                }
                b = b.getNextBooth();
            }
            vc = vc.getNextVC();
        }
    }


    /**
     * Delete appointment.
     *
     * @param index the index
     * @param app   the app
     */
    public void deleteAppointment(int index, Appointment app) {
        VaxCentre vc = Main.headVC;
        Appointment found = null;
        boolean flag = false;
        while (vc != null && !flag){
            found = vc.getAppHead(app);
            if (found!=null){
                flag = true; //comes out as soon as match found.
            }
            vc = vc.getNextVC();
        }
        Appointment current = found;

        System.out.println("flag value: " + flag +"   found value: " + found);

        if (current == app) {
            current = current.getNextAppointment();
        } else {
            boolean flag2 = false;
            while (current.getNextAppointment() != null && !flag2) {
                if (current.getNextAppointment()==app)
                    flag2=true;
                else
                current = current.getNextAppointment();
            }
            current.setNextAppointment(current.getNextAppointment().getNextAppointment());
            refreshAppListShowAll();
        }
    }


    /**
     * Delete appointment button.
     * cast the selected item to App
     */
    public void deleteAppointmentButton() {
        if (appList.getSelectionModel().getSelectedIndex() >= 0) {
            deleteAppointment(appList.getSelectionModel().getSelectedIndex(),(Appointment) appList.getSelectionModel().getSelectedItem());

            refreshAppListShowAll();

        }
    }

    /**
     * Search appointment Vax type. if the text field contains "moderna" for example, all vax types with that will be returned
     */
    public void searchAppointmentType(){
        appList.getItems().clear();
        VaxCentre vc = Main.headVC;

        while (vc != null) {
            Booth b = vc.getHeadBooth();
            while (b != null) {
                Appointment current = b.headAppointment;
                while (current != null) {
                    String currentAppList = "";
                    if (current.getVaxType().toUpperCase().contains(VaxTypeSearch.getText().toUpperCase())) {
                        appList.getItems().add(current);
                    }
                        current = current.getNextAppointment();
                }
                    b = b.getNextBooth();
            }
                vc = vc.getNextVC();
        }
    }

    /**
     * Search completion status of appointments.
     */
    public void searchCompletionStatus(){
        appList.getItems().clear();
        VaxCentre vc = Main.headVC;

        while (vc != null) {
            Booth b = vc.getHeadBooth();
            while (b != null) {
                Appointment current = b.headAppointment;
                while (current != null) {
                    String currentAppList = "";
                    if (appCompleteAppointmentSEARCH.isSelected() == current.isComplete()){
                        appList.getItems().add(current);
                    }
                    current = current.getNextAppointment();
                }
                b = b.getNextBooth();
            }
            vc = vc.getNextVC();
        }
    }

    /**
     * Search apps by vax centre name.
     */
    public void searchAppsByVaxCentre(){
        appList.getItems().clear();
        VaxCentre vc = Main.headVC;

        while (vc != null) {
            Booth b = vc.getHeadBooth();
            while (b != null) {
                Appointment current = b.headAppointment;
                while (current != null) {
                    String currentAppList = "";
                    if (vc.getName().toUpperCase().equals(VCAppSearch.getText().toUpperCase())){
                        appList.getItems().add(current);
                    }
                    current = current.getNextAppointment();
                }
                b = b.getNextBooth();
            }
            vc = vc.getNextVC();
        }
    }


    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * The Controller.
     */
    public Controller controller;


}


