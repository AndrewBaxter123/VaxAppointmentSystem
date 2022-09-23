package sample.TEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Main;
import sample.Patient;
import sample.VaxRecord;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    Patient paul,andrew,john;

    @BeforeEach
    void setUp() {
        Main.headP = paul;
        paul = new Patient("paul","1111111AA","paul@gmail.com","X910001","0861610876","10-10-2021",false);
        andrew = new Patient("andrew","2222222BB","andrew@gmail.com","C910001","0861234567","1-1-2021",false);
        john = new Patient("john","3333333CC","john@gmail.com","D910001","0869999999","5-5-2021",true);
        paul.setNextPatient(andrew);
        andrew.setNextPatient(john);
        andrew.addPatientVaxRecord(andrew, new VaxRecord("20/12/2021","1pm","Moderna","Paul","2222222BB"));
    }


    @Test
    void testToString() {
        assertTrue(paul.toString().contains("paul"));
        assertTrue(andrew.toString().contains("andrew"));
        assertTrue(john.toString().contains("john"));
    }

    @Test
    void addPatientVaxRecord() {
        john.addPatientVaxRecord(john, new VaxRecord("20-11-2022","5pm","Moderna","Tom","3333333CC"));
        assertEquals(john.getHeadVaxRecord().getVaccinatorDetails(), "Tom" );

    }

    @Test
    void getName() {
        assertEquals(paul.getName(), "paul");
    }

    @Test
    void setName() {
        paul.setName("james");
        assertEquals(paul.getName(), "james");

    }

    @Test
    void getPpsNumber() {
        assertEquals(andrew.getPpsNumber(), "2222222BB");
    }

    @Test
    void setPpsNumber() {
        andrew.setPpsNumber("9988776BB");
        assertEquals(andrew.getPpsNumber(), "9988776BB");
    }

    @Test
    void getEmail() {
        assertEquals(john.getEmail(), "john@gmail.com");
    }

    @Test
    void setEmail() {
        john.setEmail("test@gmail.com");
        assertEquals(john.getEmail(), "test@gmail.com");

    }

    @Test
    void getEircode() {
        assertEquals(john.getEircode(), "D910001");
        assertFalse(Boolean.parseBoolean(john.getEircode()), "W910000");

    }

    @Test
    void setEircode() {
        andrew.setEircode("P910000");
        assertEquals(andrew.getEircode(), "P910000");
    }

    @Test
    void getPhoneNumber() {
        assertEquals(andrew.getPhoneNumber(), "0861234567");
    }

    @Test
    void setPhoneNumber() {
        andrew.setPhoneNumber("0871234567");
        assertEquals(andrew.getPhoneNumber(), "0871234567");
    }

    @Test
    void getDateOfBirth() {
        assertEquals(paul.getDateOfBirth(), "10-10-2021");
    }

    @Test
    void setDateOfBirth() {
        paul.setDateOfBirth("5-5-1990");
        assertEquals(paul.getDateOfBirth(), "5-5-1990");
    }

    @Test
    void isWheelchairUser() {
        assertTrue(john.isWheelchairUser());
        assertFalse(andrew.isWheelchairUser());
    }

    @Test
    void setWheelchairUser() {
        andrew.setWheelchairUser(true);
        assertTrue(andrew.isWheelchairUser());
    }

    @Test
    void getNextPatient() {
        assertEquals(andrew.getNextPatient(), john);
        assertEquals(john.getNextPatient(), null);

    }

    @Test
    void setNextPatient() {
        john.setNextPatient(new Patient("TEST","7676768GG","test@gmail.com","P918476","0987654321","7-9-2021",true));
        assertEquals(john.getNextPatient().getName(), "TEST");
    }

    @Test
    void getHeadVaxRecord() {
        assertEquals(andrew.getHeadVaxRecord().getVaxType(), "Moderna");
    }

    @Test
    void setHeadVaxRecord() {
        andrew.getHeadVaxRecord().setVaxType("Johnson");
        assertEquals(andrew.getHeadVaxRecord().getVaxType(), "Johnson");
        assertFalse(Boolean.parseBoolean(andrew.getHeadVaxRecord().getVaxType()), "Moderna");
    }
}