package comp1110.ass2;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Rufus Raja (u6275198) to test the Substr() function.
 */
public class SubstrTest extends WarringStatesGame {

    String testValues[] = { "c03","b34","z09","d10","e27","d0C",
                            "c13","b38","z0F","d1Z","a2R","d1Q",
                            "a72","a2A","a3G","c4H","b5E","d0S",
                            "g04","g19","e02","a7U","z0V","g1G"};

    String placement = "c03b34z09d10e27d0Cc13b38z0Fd1Za2Rd1Qa72a2Aa3Gc4Hb5Ed0Sg04g19e02a7Uz0Vg1G"; //declaring a placement string for testing

    //Check for nullpointerexception
    @Test(expected=NullPointerException.class)
    public void SubstrNullTest()
    {
        String testStr[];
        String testString = null;
        testStr=substr(testString);
        assertEquals(testStr[0], null);
    }

    //Test with a simple placement string
    @Test
    public void SubstrSimpleTest()
    {
        String testStr[];
        String testString = "a02b0Ac03";
        testStr=substr(testString);
        assertEquals("Expected result is a02 but got "+testStr[0],testStr[0], "a02");
        assertEquals("Expected result is b0A but got "+testStr[1],testStr[1], "b0A");
        assertEquals("Expected result is c03 but got "+testStr[2],testStr[2], "c03");

        String testString2 = "z0E";
        testStr=substr(testString2);
        assertEquals("Expected result is z0E but got "+testStr[0],testStr[0], "z0E");
    }

    //Test with a longer placement string for accuracy
    @Test
    public void SubstrRandomTest()
    {
        String testStr[];
        testStr=substr(placement);
        for(int x=0;x<testStr.length;x++)
        {
            assertEquals("Expected result is "+testValues[x]+" but got "+testStr[x],testStr[x],testValues[x]);
        }

    }

}
