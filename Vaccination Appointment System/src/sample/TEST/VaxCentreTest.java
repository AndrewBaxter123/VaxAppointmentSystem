package sample.TEST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Booth;
import sample.VaxCentre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VaxCentreTest {
    VaxCentre Cork,Waterford,Dublin;

    @BeforeEach
    void setUp() {
        Cork = new VaxCentre("Cork","Douglas",100,"C910000");
        Waterford = new VaxCentre("Waterford","Dunmore",200, "X910000");
        Dublin = new VaxCentre("Dublin","Dundrum",300, "D501111");
        Cork.setNextVC(Waterford);
        Waterford.setNextVC(Dublin);
//        Cork.addBooth(new Booth(1,1,false));
    }

    public int size(VaxCentre vc) {
        int count = 0;
        Booth b = vc.getHeadBooth();
        while (b != null) {
            b = b.getNextBooth();
            count++;
        }
        return count;
    }

    @Test
    void addBooth() {
        assertEquals(size(Cork), 0);
        Booth b = new Booth("C1",1,false);
        Cork.addBooth(b);
        assertEquals(size(Cork), 1);
        Booth bb = new Booth("C2",1,false);
        Cork.addBooth(bb);
        assertEquals(size(Cork), 2);

        assertEquals(Cork.getHeadBooth(), bb);
    }



    @Test
    void getNextVC() {
        assertEquals(Cork.getNextVC(), Waterford);
        assertFalse(Cork.getNextVC().equals(Dublin));
    }

    @Test
    void setNextVC() {
        Cork.setNextVC(Dublin);
        assertEquals(Cork.getNextVC().getName(), "Dublin");
    }

    @Test
    void getHeadBooth() {

    }

    @Test
    void setHeadBooth() {
    }

    @Test
    void getName() {
        assertEquals(Cork.getName(),"Cork");
    }

    @Test
    void setName() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void setAddress() {
    }

    @Test
    void getParkingSpaces() {
    }

    @Test
    void setParkingSpaces() {
    }

    @Test
    void getEircode() {
    }

    @Test
    void setEircode() {
    }

    @Test
    void testToString() {
    }
}