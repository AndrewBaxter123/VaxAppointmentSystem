package sample;

/**
 * The type Vax centre.
 */
public class VaxCentre {
    private VaxCentre nextVC; // next VC
    private Booth headBooth;  // first or head for booth for vc

    private String name;
    private String address;
    private int parkingSpaces;
    private String eircode;


    /**
     * Instantiates a new Vax centre.
     *
     * @param name          the name
     * @param address       the address
     * @param parkingSpaces the parking spaces
     * @param eircode       the eircode
     */
    public VaxCentre(String name, String address, int parkingSpaces, String eircode) {
        this.nextVC = null;
        this.headBooth = null;
        this.name = name;
        this.address = address;
        this.parkingSpaces = parkingSpaces;
        if (Utilities.validEircode(eircode)) {
            this.eircode = eircode;
        } else {
            this.eircode = "invalid eircode, needs to be 7 digits/chars";
        }
    }
//public VaxCentre searchVaxCentre(String name){
//
//    VaxCentre current = Main.headVC;
//        while(current.getName() != name){
//            if(current.nextVC == null){ // got to end without match
//                return null;
//            } else {
//                current = current.nextVC;
//            }
//        }
//    return current;
//}
//    public int searchVaxCentre(String name) {
//        boolean foundVC = false;
//        VaxCentre current;
//        for (VaxCentre v : myList) {
//            if (v.getName().toUpperCase().contains(name.toUpperCase())) {
//                foundVC = true;
//                return current.indexOf(v);
//            }
//        }
//        return -1;
//    }
 //set up so as each booth is unique and assigned rather than user inputting.

    /**
     * Add booth.
     *
     * @param b the b
     */
    public void addBooth(Booth b){
        b.setNextBooth(headBooth); // new b sets next to headbooth
        headBooth = b;
    }


    /**
     * Prints list of booths.
     *
     * @return the string
     */
    public String printBooth(){
        Booth currentBooth = headBooth;
        String boothList = "";
        while (currentBooth != null){
            boothList = boothList + currentBooth.toString();
            currentBooth = currentBooth.getNextBooth();
        }
        return boothList;
    }

//    /**
//     * Delete booth.
//     *
//     * @param index the index
//     */
//    public void deleteBooth(int index){
//        {
//            Booth current = Main.headVC.getHeadBooth();
//            if(index == 0){
//                current = current.getNextBooth();
//            }
//            else{
//                int count = 0;
//                while(count < index - 1 && current != null){ // run while count is less than index and not null
//                    current = current.getNextBooth();
//                    count++;
//                }
//                current.setNextBooth(current.getNextBooth().getNextBooth()); // stopped at 1 before delete, gets the 1 after the one we want to delete
//            }
//        }
//    }


    /**
     * Get app head appointment.
     *
     * @param appToBeFound the app to be found
     * @return the appointment
     */
    public Appointment getAppHead(Appointment appToBeFound){ // goes through each booth, when it gets to a match it returns the required.
        Booth current = headBooth;

        while(current != null){
            Appointment app = current.findAppHead(appToBeFound);
            if(app != null){
                return app;
            }
            current = current.getNextBooth();
        }
        return null;
    }


    /**
     * Gets next vc.
     *
     * @return the next vc
     */
    public VaxCentre getNextVC() {
        return nextVC;
    }

    /**
     * Sets next vc.
     *
     * @param nextVC the next vc
     */
    public void setNextVC(VaxCentre nextVC) {
        this.nextVC = nextVC;
    }

    /**
     * Gets head booth.
     *
     * @return the head booth
     */
    public Booth getHeadBooth() {
        return headBooth;
    }

    /**
     * Sets head booth.
     *
     * @param headBooth the head booth
     */
    public void setHeadBooth(Booth headBooth) {
        this.headBooth = headBooth;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets parking spaces.
     *
     * @return the parking spaces
     */
    public int getParkingSpaces() {
        return parkingSpaces;
    }

    /**
     * Sets parking spaces.
     *
     * @param parkingSpaces the parking spaces
     */
    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
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
        if (Utilities.validEircode(eircode)) {
            this.eircode = eircode;
        }
    }

    @Override
    public String toString() {
        return
                "name= '" + name + '\'' +
                ", address= '" + address + '\'' +
                ", parkingSpaces= " + parkingSpaces +
                ", eircode= " + eircode ;
    }


}

