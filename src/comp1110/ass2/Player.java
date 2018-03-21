package comp1110.ass2;
import java.util.*;

//this class provide the ArrayList which include all the card that one player have collected, player is identified by playerID
public class Player
{
    int playerID;
    List<Kingdom> kingdomSupports = new ArrayList<Kingdom>();

    public Player(int playerID)
    {

    }

    //get player ID
    public int getID()
    {
        return playerID;
    }

    //add one kingdom
    public void addKingdom(char kingdom)
    {

    }

    //check whether the player have this kingdom
    public boolean hasKingdom(char kingdom)
    {
        return false;
    }

    //add one supporter in this kingdom
    public void addSupporters(char kingdom, String supporter)
    {

    }

}
