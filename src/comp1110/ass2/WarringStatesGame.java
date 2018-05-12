package comp1110.ass2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import comp1110.ass2.gui.Viewer;


/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {
//    public static Viewer v = new Viewer();
    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
     * author:Jiajia Xu
     */
      static boolean isCardPlacementWellFormed(String cardPlacement) {
        // FIXME Task 2: determine whether a card placement is well-formed
        if(cardPlacement.length()!=3){
            return false;
        }
        if( ! ( ( cardPlacement.charAt(0)>='a'&& cardPlacement.charAt(0)<='g') || ( cardPlacement.charAt(0)=='z') )  ){
            return false;
        }

        switch(cardPlacement.charAt(0)){
                case 'a':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'7'){
                        return false;
                        }
                    break;
                case 'b':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'6'){
                        return false;
                    }
                    break;
                case 'c':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'5'){
                        return false;
                    }
                    break;
                case 'd':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'4'){
                        return false;
                    }
                    break;
                case 'e':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'3'){
                        return false;
                    }
                    break;
                case 'f':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'2'){
                        return false;
                    }
                    break;
                case 'g':
                    if(cardPlacement.charAt(1)< '0' ||cardPlacement.charAt(1)>'1'){
                        return false;
                    }
                    break;
                case 'z':
                    if(cardPlacement.charAt(1)!='9'){
                        return false;
                    }
                    break;
            }

        if(! ( (cardPlacement.charAt(2)>='A'&& cardPlacement.charAt(2)<='Z') ||(cardPlacement.charAt(2)>='0'&& cardPlacement.charAt(2)<='9') )  ){
                return false;
            }
        else
            {return true;}





    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N three-character card placements (where N = 1 .. 36);
     * - each card placement is well-formed
     * - no card appears more than once in the placement
     * - no location contains more than one card
     *
     * @param placement A string describing a placement of one or more cards
     * @return true if the placement is well-formed
     * Author: Jiajia Xu
     */
    public static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        if(placement==null||placement.equals("")||placement.length()%3!=0||placement.length()/3<1 || placement.length()/3>36){
            return false;
        }

        for(int i=0;i<placement.length();i+=3) {
            if (!isCardPlacementWellFormed(placement.substring(i,i+3))){
                return false;
                }

            for(int j=0;j<placement.length();j+=3){
                if(i!=j){
                    if(placement.substring(i,i+2).equals(placement.substring(j,j+2))){
                        return false;
                    }
                    if(placement.charAt(i+2)==placement.charAt(j+2)){
                        return false;
                    }
                }
            }
        }

        return true;
    }



    /*public static void main(String[] args) {
        isPlacementWellFormed("f0Mc4M");

    }*/


    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     *   there are no other cards along the line from the same kingdom as the chosen card
     *   that are further away from Zhang Yi.
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        int locationCor[] = new int[2];
        int zhangLoc[] = new int[2];
        locationCor = transformCor(locationChar);//transform the destination location from char to a 2D index
        zhangLoc = transformCor(zLocation(placement));//transform ZhangYi's location to 2D index
        if(!((locationChar >= 'A' && locationChar <= 'Z') || (locationChar >= '0' && locationChar <= '9')))
            //check whether the destination is a leagal location
            return false;
        else if(zhangLoc[0] != locationCor[0] && zhangLoc[1] != locationCor[1])
            //check whether destination is in the line with ZhangYi's location by checking
            //whether the row index or column index is equal
            return false;
        else if(isEmptyLoc(placement, locationChar))
            //check whether the destination is empty with card
            return false;
        else if(!isFarestCard(placement, zhangLoc, locationCor))
            //check whether the destination is the farest card with it's kingdom
            return false;
        else if(locationChar == zLocation(placement))
            return false;
        return true;
    }

    static boolean isFarestCard(String placement, int[] zhangLoc, int[] locationCor)
    {
        char charLoc;
        char kingdom = getKingdom(corTochar(locationCor[1], locationCor[0]), placement);//get the kingdom in destination location
        if(zhangLoc[0] == locationCor[0])
        {
            //if destination is in the same row with ZhangYi
            if(zhangLoc[1] > locationCor[1])
            {
                //start from destination location to boundary location
                for(int i = locationCor[1] - 1; i >= 0; i--)
                {
                    //transform each 2D index back into char location
                    //check whether the kingdom in this location is same as kingdom in destination location
                    charLoc = corTochar(i, locationCor[0]);
                    if(isSameKingdom(kingdom, placement, charLoc))
                        return false;
                }
            }
            else if(zhangLoc[1] < locationCor[1])
            {
                for(int i = locationCor[1] + 1; i < 6; i++)
                {
                    charLoc = corTochar(i, locationCor[0]);
                    if(isSameKingdom(kingdom, placement, charLoc))
                        return false;
                }
            }
        }
        else if(zhangLoc[1] == locationCor[1])
        {
            if(zhangLoc[0] > locationCor[0])
            {
                for(int i = locationCor[0] - 1; i >= 0; i--)
                {
                    charLoc = corTochar(locationCor[1], i);
                    if(isSameKingdom(kingdom, placement, charLoc))
                        return false;
                }
            }
            else if(zhangLoc[0] < locationCor[0])
            {
                for(int i = locationCor[0] + 1; i < 6; i++)
                {
                    charLoc = corTochar(locationCor[1], i);
                    if(isSameKingdom(kingdom, placement, charLoc))
                        return false;
                }
            }
        }
        return true;
    }

    public static char getKingdom(char location, String placement)
    {
        int offset = 3;
        String sub = new String();
        char kingdom = 'a';
        for(int i = 0; i <= placement.length() - 3; i = i + offset)
        {
            sub = placement.substring(i, i + offset);
            if(location == sub.charAt(2))
                kingdom = sub.charAt(0);
        }
        return kingdom;
    }

    public static boolean isSameKingdom(char kingdom, String placement, char charLoc)
    {
        //given the traget location, target kingdom and placement string
        //check whether the kingdom in target location is same as target kingdom
        int offset = 3;
        String sub = new String();
        for(int i = 0; i <= placement.length() - 3; i = i + offset)
        {
            //search for the whole placement string, split each card placement string(3 characters)
            //compare each card's location with target location//
            //if equal, check whether the target kingdom is equal to this kingdom
            sub = placement.substring(i, i + offset);
            if(charLoc == sub.charAt(2))
                if(kingdom == sub.charAt(0))
                    return true;
        }
        return false;
    }

    static boolean isEmptyLoc(String placement, char locationChar)
    {
        //check whether target location is an empty location by checking
        //whether target char location is in placement string
        int offset = 3;
        for(int i = 2; i < placement.length(); i = i + offset)
        {
            if(locationChar == placement.charAt(i))
                return false;
        }
        return true;
    }

    public static int[] transformCor(char locationChar)
    {
        //transform char location to 2D index
        int locationCor[] = {0, 0};
        if(locationChar >= 'A' && locationChar <= 'Z')
        {
            int index = locationChar - 65;
            //locationCor[1] = (index / 6);
            locationCor[1] = 5 - (index / 6);
            locationCor[0] = index % 6;
        }
        else if(locationChar >= '0' && locationChar <= '9')
        {
            int index = 26 + (locationChar - 48);
            //locationCor[1] = (index / 6);
            locationCor[1] = 5 - (index / 6);
            locationCor[0] = index % 6;
        }
        return locationCor;
    }

    static char corTochar(int column, int row)
    {
        //transform 2D index back into char location
        column = 5 - column;
        int index = column * 6 + row;
        char ch = 'A';
        if(index <= 25)
            ch = (char)(65 + index);
        else if(index > 25 && index <= 35)
            ch = (char)(index - 1 - 25 + 48);
        return ch;
    }

    static char zLocation(String placement)
    {
        //find ZhangYi's location
        int offset = 3;
        char location = 'A';
        for(int i = 0; i <= placement.length() - offset; i = i + offset)
        {
            if(placement.charAt(i) == 'z')
                location = placement.charAt(i + 2);
        }
        return location;
    }

    /**
     * Determine whether a move sequence is valid.
     * To be valid, the move sequence must be comprised of 1..N location characters
     * showing the location to move for Zhang Yi, and each move must be valid
     * given the placement that would have resulted from the preceding sequence
     * of moves.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @return True if the placement sequence is valid
     */
    public static boolean isMoveSequenceValid(String setup, String moveSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        String boardMatrix[][] = new String[6][6];
        String placement = setup;
        boardMatrix = createMatrix(placement);
        char moveSequenceChar[] = moveSequence.toCharArray();
        for(int i = 0; i < moveSequence.length(); i++)
        {
            if(isMoveLegal(placement, moveSequenceChar[i]))
            {
                boardMatrix = oneMove(moveSequenceChar[i], placement, boardMatrix);
                placement = matrixToString(boardMatrix);
                int k = 0;
            }
            else
                return false;
        }
        return true;
    }

    public static String[][] createMatrix(String placement)
    {
        //create a 6x6 matrix represent board, each element in this matrix is a
        //character card
        String boardMatrix[][] = new String[6][6];
        int offset = 3;
        int cor[] = new int[2];
        for(int i = 0; i <= placement.length() - 3; i = i + offset)
        {
            cor = transformCor(placement.substring(i, i + 3).charAt(2));
            boardMatrix[cor[0]][cor[1]] = placement.substring(i, i + 3).substring(0, 2);
        }
        return boardMatrix;
    }

    public static String matrixToString(String boardMatrix[][])
    {
        //convert matrix to placement string
        int column, row;
        char charLocation;
        String placement = new String();
        for(column = 5; column >= 0; column--)
        {
            for(row = 0; row <= 5; row++)
            {
                if(boardMatrix[row][column] != "")
                {
                    //if elemtn in matrix is "", which means
                    //card has already been collected by player
                    //do not add this place to placement string
                    charLocation = corTochar(column, row);
                    placement = placement + boardMatrix[row][column] + charLocation;
                }
            }
        }
        return placement;
    }

    public static String[][] oneMove(char charLocation, String placement, String[][] boardMatrix)
    {
        //change the board state according to current move char
        //the parameter are follow:
        //@charLocation: the destination location
        //@placement: placement string
        //boardMatrix: matrix represent current board state
        //return a matrix that represent new board state after current move
        int cor[] = transformCor(charLocation);
        char zhangLocChar = zLocation(placement);
        int zhangLocCor[] = transformCor(zhangLocChar);
        char kingdom = getKingdom(charLocation, placement);
        if(zhangLocCor[0] == cor[0])
        {
            //if destination location is in the same row as ZhangYi's location
            if(zhangLocCor[1] > cor[1])
            {
                for(int col = zhangLocCor[1] - 1; col >= cor[1]; col--)
                {
                    //start from ZhangYi's location to destination location
                    //determine whether this location has the same kingdom as destination location
                    if(isSameKingdom(kingdom, placement, corTochar(col, zhangLocCor[0])))
                        boardMatrix[zhangLocCor[0]][col] = "";
                }
            }
            else if(zhangLocCor[1] < cor[1])
            {
                for(int col = zhangLocCor[1] + 1; col <= cor[1]; col++)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(col, zhangLocCor[0])))
                        //if the kindome is same, set the element in this location to ""
                        boardMatrix[zhangLocCor[0]][col] = "";
                }
            }
        }
        else if(zhangLocCor[1] == cor[1])
        {
            //if destination location is in the same column as ZhangYi's location
            if(zhangLocCor[0] > cor[0])
            {
                for(int row = zhangLocCor[0] - 1; row >= cor[0]; row--)
                {
                    //start from ZhangYi's location to destination location
                    //determine whether this location has the same kingdom as destination location
                    if(isSameKingdom(kingdom, placement, corTochar(zhangLocCor[1], row)))
                        //if the kindome is same, set the element in this location to ""
                        boardMatrix[row][zhangLocCor[1]] = "";
                }
            }
            else if(zhangLocCor[0] < cor[0])
            {
                for(int row = zhangLocCor[0] + 1; row <= cor[0]; row++)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(zhangLocCor[1], row)))
                        boardMatrix[row][zhangLocCor[1]] = "";
                }
            }
        }
        boardMatrix[zhangLocCor[0]][zhangLocCor[1]] = "";
        boardMatrix[cor[0]][cor[1]] = "z9";
        return boardMatrix;
    }

    /**
     * Get the list of supporters for the chosen player, given the provided
     * setup and move sequence.
     * The list of supporters is a sequence of two-character card IDs, representing
     * the cards that the chosen player collected by moving Zhang Yi.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @param playerId     the player number for which to get the list of supporters, [0..(numPlayers-1)]
     * @return the list of supporters for the given player
     */
    public static String getSupporters(String setup, String moveSequence, int numPlayers, int playerId) {
        // FIXME Task 7: get the list of supporters for a given player after a sequence of moves
        Player player = new Player(playerId);
        int offset = numPlayers;
        String placement = setup;
        //transfer placment into 6*6 board matrix.
        String boardMatrix[][] = new String[6][6];
        boardMatrix = createMatrix(placement);
        String str = new String();

        for(int i = 0; i < moveSequence.length(); i++)
        {
            //assign i with corresponding location char
            //for(int i=playerId; i<moveSequence.length();i+=numPlayers)
            if(i % numPlayers == playerId)

                player = oneMove(moveSequence.charAt(i),placement, boardMatrix, player);
            //"oneMove" method from task06
            boardMatrix = oneMove(moveSequence.charAt(i), placement, boardMatrix);
            placement = matrixToString(boardMatrix);
        }
        player.sortKingdom();
        player.sortSupporters();
        return player.getSortedSupporters();
    }

    static Player oneMove(char charLocation, String placement, String[][] boardMatrix, Player player)
    {
        //finding zhang yi's next location and original location on board matrix
        int cor[] = transformCor(charLocation);
        char zhangLocChar = zLocation(placement);
        int zhangLocCor[] = transformCor(zhangLocChar);

        char kingdom = getKingdom(charLocation, placement);
        if(!player.hasKingdom(kingdom))
            player.addKingdom(kingdom);
        if(zhangLocCor[0] == cor[0])
        {
            if(zhangLocCor[1] > cor[1])
            {
                for(int col = zhangLocCor[1] - 1; col >= cor[1]; col--)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(col, zhangLocCor[0])))
                        player.addSupporters(kingdom, boardMatrix[zhangLocCor[0]][col]);
                        //boardMatrix[zhangLocCor[0]][col] = "";
                }
            }
            else if(zhangLocCor[1] < cor[1])
            {
                for(int col = zhangLocCor[1] + 1; col <= cor[1]; col++)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(col, zhangLocCor[0])))
                        player.addSupporters(kingdom, boardMatrix[zhangLocCor[0]][col]);
                        //boardMatrix[zhangLocCor[0]][col] = "";
                }
            }
        }
        else if(zhangLocCor[1] == cor[1])
        {
            if(zhangLocCor[0] > cor[0])
            {
                for(int row = zhangLocCor[0] - 1; row >= cor[0]; row--)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(zhangLocCor[1], row)))
                        player.addSupporters(kingdom, boardMatrix[row][zhangLocCor[1]]);
                        //boardMatrix[row][zhangLocCor[1]] = "";
                }
            }
            else if(zhangLocCor[0] < cor[0])
            {
                for(int row = zhangLocCor[0] + 1; row <= cor[0]; row++)
                {
                    if(isSameKingdom(kingdom, placement, corTochar(zhangLocCor[1], row)))
                        player.addSupporters(kingdom, boardMatrix[row][zhangLocCor[1]]);
                        //boardMatrix[row][zhangLocCor[1]] = "";
                }
            }
        }
        //boardMatrix[zhangLocCor[0]][zhangLocCor[1]] = "";
        //boardMatrix[cor[0]][cor[1]] = "z9";
        return player;
    }


    /**
     * Given a setup and move sequence, determine which player controls the flag of each kingdom
     * after all the moves in the sequence have been played.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing a sequence of moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @return an array containing the player ID who controls each kingdom, where
     * - element 0 contains the player ID of the player who controls the flag of Qin
     * - element 1 contains the player ID of the player who controls the flag of Qi
     * - element 2 contains the player ID of the player who controls the flag of Chu
     * - element 3 contains the player ID of the player who controls the flag of Zhao
     * - element 4 contains the player ID of the player who controls the flag of Han
     * - element 5 contains the player ID of the player who controls the flag of Wei
     * - element 6 contains the player ID of the player who controls the flag of Yan
     * If no player controls a particular house, the element for that house will have the value -1.
     */
    public static int[] getFlags(String setup, String moveSequence, int numPlayers) {
        // FIXME Task 8: determine which player controls the flag of each kingdom after a given sequence of moves
        int flag[] = new int[7];
        Player player[] = new Player[numPlayers];
        char kingdoms[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        char deKingdom;
        String boardMatrix[][] = new String[6][6];
        boardMatrix = createMatrix(setup);
        String placement = setup;
        for(int i = 0; i < numPlayers; i++)
            player[i] = new Player(i);
        for(int i = 0; i < 7; i++)
            flag[i] = -1;
        for (int i = 0; i < moveSequence.length(); i++) {
            //for each element in moveSequence, call oneMove method to let corresponding player who should
            //carry out this move to collect card
            //deKingdom is the kingdom in destination location
            deKingdom = getKingdom(moveSequence.charAt(i), placement);
            player[i % numPlayers] = oneMove(moveSequence.charAt(i), placement, boardMatrix, player[i % numPlayers]);
            //change the boardMatrix according to this move
            boardMatrix = oneMove(moveSequence.charAt(i), placement, boardMatrix);
            placement = matrixToString(boardMatrix);
            //(deKingdom - 97) corresponding to the slot in flag[] which hold the winner for this kingdom
            //if this slot is empty, then, this player hold the flag after this move.
            //if the the number of supporters for this kingdom this player hold is greater than the orignal holder
            //for this kingdom, replace it
            if (flag[(int) (deKingdom - 97)] == -1)
                flag[(int) (deKingdom - 97)] = i % numPlayers;
            else if (player[i % numPlayers].getNumSup(deKingdom) >= player[flag[(int) (deKingdom - 97)]].getNumSup(deKingdom))
                flag[(int) (deKingdom - 97)] = i % numPlayers;
        }
        return flag;
    }

    public static String[] substr(String placement)
    {
        int count =0;
        String sub[] = new String[placement.length()/3];
        for (int x = 0; x < placement.length(); x += 3) {
            sub[count] = placement.substring(x, x + 3);
            count++;
        }
        return sub;
    }


    static Player getSupportersList(String setup, String moveSequence, int numPlayers, int playerId) {
        Player player = new Player(playerId);
        int offset = numPlayers;
        String placement = setup;
        String boardMatrix[][] = new String[6][6];
        boardMatrix = createMatrix(placement);
        String str = new String();
        for(int i = 0; i < moveSequence.length(); i++)
        {
            if(i % numPlayers == playerId)
                player = oneMove(moveSequence.charAt(i),placement, boardMatrix, player);
            boardMatrix = oneMove(moveSequence.charAt(i), placement, boardMatrix);
            placement = matrixToString(boardMatrix);
        }
        player.sortKingdom();
        player.sortSupporters();
        return player;
    }

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        Random ran = new Random();
        if(LegalMoves(placement).isEmpty()) {
            return '\0';
        }
        else{
            //if legal moves are not empty, genrate a random location char from legal moves
            int index = ran.nextInt(LegalMoves(placement).size());
            return LegalMoves(placement).get(index);
            }
    }

    //Legal moves for zhang yi
    public static List<Character> LegalMoves(String placement) {
        List<Character> result = new ArrayList<Character>();

        for(int i = 2; i < placement.length(); i += 3){
            if(isMoveLegal(placement,placement.charAt(i))){
                result.add(placement.charAt(i));
            }
        }

        return result;
    }




       /* String str[];
        int count=0;
        str = substr(placement);
        int loccor[] = new int[2];
        int zloc[] = new int[2];

        zloc = transformCor(zLocation(placement));//transform ZhangYi's location to 2D index
        int check = 0,sum=0;
        char a[] = new char[str.length];
        for(int x = 0 ; x<str.length; x++) {
            if(str[x].charAt(0) != 'z' && check == 0)
            {
                sum++;
                continue;
            }
            else
                check = 1;
            for(int j=sum;j<str.length;j++)
            a[j] = str[j].charAt(2);
        }
        for(int x = 0 ;x<a.length;x++)
        {
            loccor = transformCor(a[x]);
            if(!((a[x] >= 'A' && a[x] <= 'Z') || (a[x] >= '0' && a[x] <= '9')))
                break;
            else if(zloc[0] != loccor[0] && zloc[1] != loccor[1])
                break;
            else if(isEmptyLoc(placement, a[x]))
                 break;
            else
                count++;

        }
        System.out.println(count);
        if(count <= 1)
        {
            return '\0';
        }else {
            char r = str[count-1].charAt(2);
            return r;
        }
    }

    public static void main(String[] args) {
        String s = "a03b04z91a02b6U";
        System.out.println(generateMove(s));

    }*/


}
