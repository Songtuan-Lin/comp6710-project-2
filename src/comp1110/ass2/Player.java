package comp1110.ass2;
import java.util.*;

public class Player
{
    int playerID;
    List<Kingdom> kindomSupports = new ArrayList<Kingdom>();

    public Player(int playerID)
    {
        this.playerID = playerID;
    }

    public int getID()
    {
        return playerID;
    }

    public void addKingdom(char kingdom)
    {
        kindomSupports.add(new Kingdom(kingdom));
    }

    public boolean hasKingdom(char kingdom)
    {
        for(Kingdom kin : kindomSupports)
        {
            if(kingdom == kin.findKingdom())
                return true;
        }
        return false;
    }

    public void addSupporters(char kingdom, String supporter)
    {
        int index = kingdomIndex(kingdom);
        kindomSupports.get(index).addSupporter(supporter);
    }

    int kingdomIndex(char kingdom)
    {
        for(int i = 0; i < kindomSupports.size(); i++)
        {
            if(kingdom == kindomSupports.get(i).findKingdom())
                return i;
        }
        return 0;
    }

    public void sortKingdom()
    {
        Collections.sort(kindomSupports);
    }

    public void sortSupporters()
    {
        for(Kingdom k : kindomSupports)
        {
            k.sortSupporter();
        }
    }

    public String getSortedSupporters()
    {
        String str = new String();
        for(Kingdom k : kindomSupports)
        {
            List<String> s = k.getSupportersList();
            for(String st : s)
            {
                str += st;
            }
        }
        return str;
    }

    public int getNumSup(char kingdom)
    {
        int index = kingdomIndex(kingdom);
        return kindomSupports.get(index).getNum();
    }

}
