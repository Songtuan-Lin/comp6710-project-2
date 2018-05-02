package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isEmptyLocTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);


    @Test
    public void testEmpty(){
        String placement="a6Nb5Cc4Gd4Pz9De3Q";
        char locationChar='J';

        //assertEquals(true,WarringStatesGame.isEmptyLoc(placement,locationChar));
        assertTrue("Placement "+placement+" has location "+locationChar+", but it returns false",
                WarringStatesGame.isEmptyLoc(placement,locationChar));
    }


    @Test
    public void testNonempty(){
        String placement="a6Nb5Cc4Gd4Pz9De3Q";
        char locationChar='Q';
        //assertEquals(false,WarringStatesGame.isEmptyLoc(placement,locationChar));
        assertFalse("Placement "+placement+" has no location "+locationChar+", but it returns true",
                WarringStatesGame.isEmptyLoc(placement,locationChar));
    }
}
