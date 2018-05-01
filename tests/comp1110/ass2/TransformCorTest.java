package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransformCorTest {
    private String testSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int correctCor[][] = {{0, 5}, {1, 5}, {2, 5}, {3, 5}, {4, 5}, {5, 5},
            {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}, {5, 4},
            {0, 3}, {1, 3}, {2, 3}, {3, 3}, {4, 3}, {5, 3},
            {0, 2}, {1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2},
            {0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1},
            {0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};

    @Test
    public void transformCorTest() {
        for (int i = 0; i < testSequence.length(); i++) {
            assertTrue("The coordinate for " + testSequence.charAt(i) + " should be (" + correctCor[i][0] + ", " + correctCor[i][1] + ")" + " but got (" + WarringStatesGame.transformCor(testSequence.charAt(i))[0] + ", " + WarringStatesGame.transformCor(testSequence.charAt(i))[1] + ")",
                    (WarringStatesGame.transformCor(testSequence.charAt(i))[0] == correctCor[i][0]) &&
                            (WarringStatesGame.transformCor(testSequence.charAt(i))[1] == correctCor[i][1]));
        }
    }
}
