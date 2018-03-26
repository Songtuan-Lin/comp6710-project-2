package comp1110.ass2;

import java.util.List;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {

    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
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
     */
    static boolean isPlacementWellFormed(String placement) {
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
        return false;
    }

    /* pick only the card that occurs at the end of the line else not legal */
    public static boolean isFarthestCard(String placement, int zhangLoc, int LocationCoor)
    {
        return true;
    }

    /* Get the kingdom of the card based on it's location and the string placement*/
    public static char getKingdom(char location, String placement)
    {
        return 'a';
    }

    /* check if other the same kingdom occurs based on the selection*/
    public static boolean isSameKingdom()
    {
        return true;
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
    static boolean isMoveSequenceValid(String setup, String moveSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        return false;
    }
    static void createMatrix(String placement){
        //create  matrix for the game board with input placement string

    }
    static String matrixtoString(){
        //covert the board matrix back to placement string
    return "adw";
    }
    static void oneMove(char charLocation, String placement, String[][] boardMatrix)
    {
        // update the boardMatrix with input of move and current placement

    }

    //transform the char location to index in 2-D matrix
    static int[] transformCor(char locationChar)
    {
        int cor[] = new int[2];
        return cor;
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
        return null;
    }

    //finish one move and collect the corresponding card
    static Player oneMove(char charLOcation, String placement, String[][] boardMatrix, Player player)
    {
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
        return null;
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
        return '\0';
    }
}
