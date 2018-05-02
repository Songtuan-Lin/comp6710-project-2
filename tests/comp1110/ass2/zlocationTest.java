package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class zlocationTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testOne(){
        String placement="z9K";
        assertEquals('K',WarringStatesGame.zLocation(placement));
    }
    @Test
    public void testSeveral(){
        String placement="a6Nb5Cc4Gd4Pz9De3Q";
        assertEquals('D',WarringStatesGame.zLocation(placement));
    }



}
