package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class isSameKingdomTest {
    private String placement = "g0Aa0Bf1Ca1Dc5Ee1Fa4Ge3He2Ia2Jc2Kd0Lf0Mb4Nd4Oa6Pc3Qe0Ra5Sc1Td1Uc4Vb5Wb0Xa7Yf2Zb10a31z92b33b64d35g16b27d28c09";
    private String testSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String testKingdom = "abcdefg";

    @Test
    public void testSameKingdom() {
        int offset = 3;
        for (int i = 0, k = 0; i < testSequence.length(); i++, k = k + offset) {
            for (int j = 0; j < testKingdom.length(); j++) {
                assertTrue("Wrong judgement", WarringStatesGame.isSameKingdom(testKingdom.charAt(j), placement, testSequence.charAt(i)) == (testKingdom.charAt(j) == placement.charAt(k)));
            }
        }
    }
}
