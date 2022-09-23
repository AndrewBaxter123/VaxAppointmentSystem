package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Booth;
import sample.Main;
import sample.VaxCentre;

public class TabOneController {


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller controller;


    @FXML
    public ListView<String> myList, boothList, printListForVaxCentre,viewAppsList;
    @FXML
    private Tab tabVax;
    @FXML
    private Button btnReset,btnEdit,btnAdd, btnDelete, btnBoothAdd,LOAD;
    @FXML
    private TextField vaxNameField, addressField, parkingField, eircodeField, floorLevelField, boothIDField;
    @FXML
    private CheckBox wheelchairCheckVC;


    public VaxCentre selectedVC;
    public Booth selectedBooth;


    /**
     * Adding a new Vax Centre
     *
     * @param v the v
     */
    public void addVaxCentre(VaxCentre v) {
        v.setNextVC(Main.headVC); // set next to current head and set this vc to the passed in v
        Main.headVC = v;

        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

    /**
     * Editing vax centre.
     * goes through the while loop until it gets to your index, and then you set new name,address,parking spaces and eircode via textfields
     * @param index the index
     */
    public void editVaxCentre(int index) {
        VaxCentre current = Main.headVC;

        int count = 0;
        while (current != null && count < index) { // while current is != to null and count is less than index
            current = current.getNextVC();
            count++;

        }
        current.setName(vaxNameField.getText());
        current.setAddress(addressField.getText());
        current.setParkingSpaces(Integer.parseInt(parkingField.getText()));
        current.setEircode(eircodeField.getText());

        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }

    }

    /**
     * Deleting a Vax Centre - find the index of the one before you want to delete with "count" then set current to the next, next VC.
     * eg find index 1, deleting 2 and adding a link from 1 to 3.
     *
     * @param index the index
     */
    public void deleteVaxCentre(int index) {
        VaxCentre current = Main.headVC;
        if (index == 0) {
            Main.headVC = current.getNextVC();
        } else {
            int count = 0;
            while (count < index - 1 && current != null) { // run while count is less than index and not null
                current = current.getNextVC();
                count++;
            }
            current.setNextVC(current.getNextVC().getNextVC()); // stopped at 1 before delete, gets the 1 after the one we want to delete
        }

        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

    /**
     * Print the entire vax centre, going next to next until it gets to null.
     *
     * @return the string
     */
    public String printVaxCentre() {
        VaxCentre currentVaxCentre = Main.headVC;
        String vaxCentreList = "";
        while (currentVaxCentre != null) {
            vaxCentreList = vaxCentreList + currentVaxCentre.toString() + "\n";
            currentVaxCentre = currentVaxCentre.getNextVC();
        }
        return vaxCentreList;
    }

    /**
     * Gets vax centre by name.
     *
     * @param name the name
     * @return the vax centre by name
     */
    public VaxCentre getVaxCentreByName(String name) {
        VaxCentre currentVaxCentre = Main.headVC;
        boolean flag = false;

        while (currentVaxCentre != null) {
            if (!name.equals(currentVaxCentre.getName()))
                return currentVaxCentre; // if it is return, otherwise keeps going on and on.
            currentVaxCentre = currentVaxCentre.getNextVC();
        }
        return currentVaxCentre;

    }


    /**
     * List booths by vax centre string.
     *
     * @param name the name
     * @return the string
     */
    public String listBoothsByVaxCentre(String name){
        VaxCentre current = Main.headVC;

        while (current != null){
            if(name.equals(current.getName())){
                current.printBooth();
            }
        }

        return current.printBooth();
    }

    /**
     * Get vax centre by index vax centre.
     *
     * @param index the index
     * @return the vax centre
     */
    public VaxCentre getVaxCentreByIndex(int index){
        VaxCentre current = Main.headVC;

        int count = 0;
        while (current != null && count < index) { // while current is != to null and count is less than index
            current = current.getNextVC();
            count++;

        }
        return current;
    }


    /**
     * Add booth to vax centre.
     *
     * @param name the name
     * @param b    the b
     */
    public void addBoothToVaxCentre(VaxCentre name, Booth b){
        name.addBooth(b); // if statement, find the vax centre using above method, if not returned null, add the booth to that.
        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

//    public void deleteBoothFromVaxCentre(VaxCentre name){ //come back to this
//        name.deleteBooth(boothList.getSelectionModel().getSelectedIndex());
//
//    }

    /**
     * Add booth button.
     */
    public void addBoothButton() {
        if (myList.getSelectionModel().getSelectedIndex() >= 0) {
            VaxCentre found = getVaxCentreByIndex(myList.getSelectionModel().getSelectedIndex());

            Booth v = new Booth((boothIDField.getText()), Integer.parseInt(floorLevelField.getText()),wheelchairCheckVC.isSelected());

            addBoothToVaxCentre(found,v);
            refreshBoothList(found); //

        }
    }

//    public void deleteBooth(Booth index) {
//        Booth current = Main.headVC.getHeadBooth();
//        if (index == 0) {
//            Main.index.getHeadBooth() = current.getNextBooth();
//        } else {
//            int count = 0;
//            while (count < index - 1 && current != null) { // run while count is less than index and not null
//                current = current.getNextBooth();
//                count++;
//            }
//            current.setNextBooth(current.getNextBooth().getNextBooth()); // stopped at 1 before delete, gets the 1 after the one we want to delete
//        }
//    }

    /**
     * Add Vax button.
     */
    public void addButton() {

        VaxCentre v = new VaxCentre(vaxNameField.getText(), addressField.getText(), Integer.parseInt(parkingField.getText()), eircodeField.getText());
        addVaxCentre(v);
        refreshList();

        System.out.println(printVaxCentre());

    }


    /**
     * Edit button.
     */
    public void editButton() {

        if (myList.getSelectionModel().getSelectedIndex() >= 0) {
            editVaxCentre(myList.getSelectionModel().getSelectedIndex());
            refreshList();
        }
    }

    /**
     * Delete button.
     */
//deletes the index that is currently selected
    public void deleteButton() {
        if (myList.getSelectionModel().getSelectedIndex() >= 0) {
            deleteVaxCentre(myList.getSelectionModel().getSelectedIndex());
            refreshList();
        }
    }

    /**
     * Refresh VC list.
     */

    public void refreshList() {
        myList.getItems().clear();
        VaxCentre currentVaxCentre = Main.headVC;
        String vaxCentreList = "";
        while (currentVaxCentre != null) {
            myList.getItems().add(currentVaxCentre.toString()); //adds currentVaxCentre
            currentVaxCentre = currentVaxCentre.getNextVC(); // keeps going to the next one until it hits nulll
        }
        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

    /**
     * Refresh booth list.
     *
     * @param vc the vc
     */
    public void refreshBoothList(VaxCentre vc) {
        boothList.getItems().clear();
        Booth currentBooth = vc.getHeadBooth(); //Main.headVC.getHeadBooth(); changed from this as it wasn't working
        String BoothList = "";
        while (currentBooth != null) {
            boothList.getItems().add(currentBooth.toString());
            currentBooth = currentBooth.getNextBooth();
        }
        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

    /**
     * Refresh booth list when clicking restart system
     */
    public void refreshBoothList() { //for the refresh system
        boothList.getItems().clear();
        Booth currentBooth = Main.headVC.getHeadBooth();
        String BoothList = "";
        while (currentBooth != null) {
            boothList.getItems().add(currentBooth.toString());
            currentBooth = currentBooth.getNextBooth();
        }
        try {
            Main.save();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
    }

    /**
     * Loads the entire xml of VC and Patient, which also should have Booths, Vax Records etc attached to them.
     * @throws Exception the exception
     */
    public void Load() throws Exception {
        Main.load();
        refreshList();
        Main.loadPatient();
        controller.tabTwoController.refreshPatientList();
        controller.tabTwoController.refreshPatientVaxRecordList(Main.headP);
        controller.tabThreeController.refreshPatientAppList();
        controller.tabThreeController.refreshAppListShowAll();

    }
    /**
     * Find's the selected VC and updates the Booth List to only show Booths from that selected VC.
     */
    public void findVC(){
        selectedVC = getVaxCentreByIndex((myList.getSelectionModel().getSelectedIndex())); //can use this when needing to select

        refreshBoothList(selectedVC);
    }


    /**
     * my attempt at "resetting the whole system", it works perfectly on VC(the first tab).
     * but on tab 2 and 3 you have to enter in new patients to see the list actually update, so presume it's a updating data
     * between tabs issue more than anything else.
     * so it "works" behind the scenes as i want it to, but a small issue with the updating lists for some reason
     */
    public void resetButton() { //add more refreshlists
        Main.headVC = null;
        Main.headP = null;
        refreshList();
        refreshBoothList();
        controller.tabTwoController.refreshPatientList();
        controller.tabThreeController.refreshAppListShowAll();
        controller.tabThreeController.refreshPatientAppList();

    }

}
