Reviewer: Rufus Raja (u6275198)
Component: Class: WarringStatsGame, Methods: isMoveLegal(), isFarestCard, getKingdom(), isSameKingdom()
Author: Songtuan Lin (u6162630)

Review Comments:

1. The best feature of the code is the modularity, a neat separation between the different processes using various methods.
2. The code is well-documented, with proper explanations, which makes readability easy. Main description of the function could be defined before the 
   function definition.
3. The class and method structure is good. 
4. Follows through with the Java conventions and has meaningful method names.
5. No error noticeable, however, noticed that some user-created funtions that have now been defined within the class can be used to enhance the solution:
   For example, there is now a substr function to break up the placement string and return an array of strings, this can be used in place of redundant
   logic for breaking up placement strings. 