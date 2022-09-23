package sample.TEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.*;

import static org.junit.jupiter.api.Assertions.*;

class VaxRecordTest {
    Patient andrew;
    VaxRecord vr1,vr2;

    @BeforeEach
    void setUp() {
        andrew = new Patient("andrew","1234567AA","a@gmail.com","C913333","0971231232","11-11-2021",false);
        vr1 = new VaxRecord("12-12-2021","4pm","Moderna","Paul", "1234567AA");
        vr2 = new VaxRecord("12-05-2022","5pm", "Johnson", "James", "1234567AA");

        andrew.addPatientVaxRecord(andrew,vr1);
        andrew.addPatientVaxRecord(andrew,vr2);

        //vr1.setNextVaxRecord(vr2);
    }

    public int size(Patient p) {
        int count = 0;
        VaxRecord record = p.getHeadVaxRecord();
        while (record != null) {
            record = record.getNextVaxRecord();
            count++;
        }
        return count;
    }

    @Test
    void getNextVaxRecord() {
        assertEquals(andrew.getHeadVaxRecord().getTime(), "5pm");
        assertEquals(andrew.getHeadVaxRecord().getNextVaxRecord().getTime(), "4pm");
    }

    @Test
    void setNextVaxRecord() {
        andrew.getHeadVaxRecord().setNextVaxRecord(new VaxRecord("6-6-2022","7pm","JJ", "Gerry","1234567AA"));
    }

    @Test
    void getDate() {
        assertEquals(vr1.getDate(), "12-12-2021");
    }

    @Test
    void setDate() {
        vr1.setDate("7-7-2050");
        assertEquals(vr1.getDate(), "7-7-2050");
    }

    @Test
    void getTime() {
        assertEquals(vr1.getTime(),"4pm");
    }

    @Test
    void setTime() {
        vr1.setTime("6pm");
        assertEquals(vr1.getTime(),"6pm");
    }

    @Test
    void getVaxType() {
        assertEquals(vr1.getVaxType(), "Moderna");
    }

    @Test
    void setVaxType() {
        vr1.setVaxType("JJ");
        assertEquals(vr1.getVaxType(), "JJ");
    }

    @Test
    void getVaccinatorDetails() {
        assertEquals(vr1.getVaccinatorDetails(),"Paul");
    }

    @Test
    void setVaccinatorDetails() {
        vr1.setVaccinatorDetails("Pat");
        assertEquals(vr1.getVaccinatorDetails(),"Pat");
    }

    @Test
    void getPatientPPSN() {
        assertEquals(vr1.getPatientPPSN(),"1234567AA");
    }

    @Test
    void setPatientPPSN() {
        vr1.setPatientPPSN("1010101AA");
        assertEquals(vr1.getPatientPPSN(),"1010101AA");
    }
    @Test
    void addVaxRecord() {
        assertEquals(size(andrew),2);
        VaxRecord vr3 = new VaxRecord("12-06-2022","5pm", "Johnson", "James", "1234567AA");

        andrew.addPatientVaxRecord(andrew,vr3);
        assertEquals(size(andrew),3);

        assertTrue(andrew.getHeadVaxRecord().equals(vr3));
    }
}