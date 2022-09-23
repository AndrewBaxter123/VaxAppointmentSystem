package sample.TEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Appointment;
import sample.Patient;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    Appointment app;
    Patient andrew;

    @BeforeEach
    void setUp() {
        andrew = new Patient("andrew", "1234567AA", "a@gmail.com", "X910000", "0861610876", "31-12-2021", false);
        app = new Appointment("23/12/2021", "1pm", "Moderna", "paul", "1234567AA", false);

    }


    @Test
    void addPatient() {
        app.addPatient(andrew);
        assertEquals(app.getPatientPPSN(), "1234567AA");
        assertFalse(app.isComplete());
    }

    @Test
    void getDate() {
        assertTrue(app.getDate().contains("23/12/2021"));
        assertFalse(app.getDate().contains("1/1/2021"));
    }

    @Test
    void setDate() {
        app.setDate("5/12/2021");
        assertFalse(app.getDate().contains("23/12/2021"));
        assertTrue(app.getDate().contains("5/12/2021"));
    }

    @Test
    void getTime() {
        assertEquals(app.getTime(),"1pm");
        assertFalse(app.getTime().contains("2pm"));
    }

    @Test
    void setTime() {
        app.setTime("5pm");
        assertFalse(app.getTime().contains("1pm"));
        assertTrue(app.getTime().contains("5pm"));
    }

    @Test
    void getVaxType() {
        assertTrue(app.getVaxType().contains("Moderna"));
        assertFalse(app.getVaxType().contains("Johnson"));
    }

    @Test
    void setVaxType() {
        app.setVaxType("Astra");
        assertFalse(app.getVaxType().contains("Moderna"));
        assertTrue(app.getVaxType().contains("Astra"));
    }

    @Test
    void getVaccinatorDetails() {
        assertFalse(app.getVaccinatorDetails().contains("sd"));
        assertTrue(app.getVaccinatorDetails().contains("paul"));
    }

    @Test
    void setVaccinatorDetails() {
        app.setVaccinatorDetails("james");
        assertFalse(app.getVaccinatorDetails().contains("paul"));
        assertTrue(app.getVaccinatorDetails().contains("james"));
    }

    @Test
    void getPatientPPSN() {
        assertTrue(app.getPatientPPSN().contains("1234567AA"));
        assertFalse(app.getPatientPPSN().contains("9871231BB"));
    }

    @Test
    void isComplete() {
        assertFalse(app.isComplete());
        app.setComplete(true);
        assertTrue(app.isComplete());
    }
}
