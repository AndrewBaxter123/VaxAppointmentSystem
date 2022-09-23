package sample.TEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.*;

import static org.junit.jupiter.api.Assertions.*;

class BoothTest {

    VaxCentre vc;
    Booth b,b2;
    Appointment app, app2;
    Patient patient,patient2;

    @BeforeEach
    void setUp() {
        vc = new VaxCentre("Cork","Douglas",100,"C910001");
        b = new Booth("C1",1,false);
        b2 = new Booth("C2",2,true);
        app = new Appointment("23/12/2021", "1pm", "Moderna", "paul", "1234567AA", false);
        app2 = new Appointment("23/12/2021", "2pm", "Moderna", "sarah", "9999999BB", false);
        patient = new Patient("andrew","1234567AA","a@gmail.com","C913333","0971231232","11-11-2021",false);
        patient2 = new Patient("paul","9999999BB","p@gmail.com","C914444","0556785761","12-12-2021",true);

        vc.addBooth(b);
        vc.addBooth(b2);
        b.setNextBooth(b2);

        b.addAppointment(app);
        b2.addAppointment(app2);

        app.addPatient(patient);
        app.addPatient(patient2);
    }

    public int size(VaxCentre vc, Booth b) {
        int count = 0;
        Appointment app = b.getHeadAppointment();
        while (app != null) {
            app = app.getNextAppointment();
            count++;
        }
        return count;
    }



    @Test
    void addAppointment() {
        assertEquals(size(vc,b),1); //already added one in setup
        b.addAppointment(new Appointment("7-7-2022","6pm","Moderna","paul","5555555HH",false));
        assertTrue(b.printAppointment().contains("5555555HH"));
        assertEquals(size(vc,b),2);
    }

    @Test
    void printAppointment() {
        assertTrue(b.printAppointment().contains(app.getPatientPPSN()));
        assertFalse(b2.printAppointment().contains(app.getPatientPPSN()));

        assertTrue(b2.printAppointment().contains(app2.getPatientPPSN()));
        assertFalse(b2.printAppointment().contains(app.getPatientPPSN()));

    }

    @Test
    void getNextBooth() {
        assertTrue(b.getNextBooth().equals(b2));

    }

    @Test
    void getHeadAppointment() {
        assertEquals(b.getHeadAppointment().getPatientPPSN(), "1234567AA");
        assertEquals(b2.getHeadAppointment().getPatientPPSN(), "9999999BB");
    }


    @Test
    void getBoothId() {
        assertEquals(b.boothId,"C1");

        assertEquals(b2.boothId,"C2");

    }

    @Test
    void setBoothId() {
        b.setBoothId("W5");
        assertEquals(b.boothId,"W5");

    }

    @Test
    void getFloorLevel() {
        assertEquals(b.floorLevel,1);

        assertEquals(b2.floorLevel,2);
    }

    @Test
    void setFloorLevel() {
        b2.setFloorLevel(5);

        assertEquals(b2.floorLevel, 5);
    }

    @Test
    void isWheelchairAccess() {
        assertTrue(b2.wheelchairAccess);
        assertFalse(b.wheelchairAccess);
    }

    @Test
    void setWheelchairAccess() {
        b.setWheelchairAccess(true);
        b2.setWheelchairAccess(false);

        assertTrue(b.wheelchairAccess);
        assertFalse(b2.wheelchairAccess);
    }
}