package comp1110.ass2;
import java.util.*;

public class Kingdom implements Comparable<Kingdom>
{
    char kingdom;
    List<String> supporters = new ArrayList<String>();

    public Kingdom(char kingdom)
    {
        this.kingdom = kingdom;
    }

    public void addSupporter(String supporter)
    {
        supporters.add(supporter);
    }

    public char findKingdom()
    {
        return this.kingdom;
    }

    @Override
    public int compareTo(Kingdom o) {
        if(this.kingdom < o.kingdom)
            return -1;
        else if(this.kingdom == o.kingdom)
            return 0;
        return 1;
    }

    public void sortSupporter()
    {
        Collections.sort(supporters);
    }

    public List<String> getSupportersList()
    {
        return supporters;
    }

    public int getNum()
    {
        return supporters.size();
    }
}
