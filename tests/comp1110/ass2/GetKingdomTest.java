package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GetKingdomTest {
    private String placement = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
    private String testSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Test
    public void testIsSameKingdom() {
        int offset = 3;
        for (int i = 0, j = 0; i < testSequence.length(); i++, j = j + offset) {
            assertTrue("Kingdom in location '" + testSequence.charAt(i) + "' should be '" +
                            placement.charAt(j) + "' but got '" + WarringStatesGame.getKingdom(testSequence.charAt(i), placement) + "'",
                    WarringStatesGame.getKingdom(testSequence.charAt(i), placement) == placement.charAt(j));
        }
    }
}
