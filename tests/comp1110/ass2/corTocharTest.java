package comp1110.ass2;

import b.b.P;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Rufus Raja (u6275198) to test the corTochar() function.
 */
public class corTocharTest extends WarringStatesGame{
    char testcors[][] =
            {
                    {'4','Y','S','M','G','A'},
                    {'5','Z','T','N','H','B'},
                    {'6','0','U','O','I','C'},
                    {'7','1','V','P','J','D'},
                    {'8','2','W','Q','K','E'},
                    {'9','3','X','R','L','F'}
            };

    @Test
    public void TestOne()
    {
       char c = corTochar(0,1);
       assertEquals("The expected result is "+testcors[1][0]+" but got "+c,c,testcors[1][0]);
    }

    @Test
    public void TestTwo()
    {
        char c = corTochar(0,1);
        assertEquals("The expected result is "+testcors[1][0]+" but got "+c,c,testcors[1][0]);
        char c1 = corTochar(5,4);
        assertEquals("The expected result is "+testcors[4][5]+" but got "+c1,c1,testcors[4][5]);
    }


    @Test
    public void TestComplete()
    {
        for(int x=0;x<6;x++)
        {
            for(int y=0;y<6;y++)
            {
                char c = corTochar(x,y);
                assertEquals("The expected result is "+testcors[y][x]+" but got "+c,c,testcors[y][x]);
            }
        }
    }


}
