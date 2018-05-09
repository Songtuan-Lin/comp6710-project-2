Reviewer: Songtuan Lin (u6162630)
Component: <generateMove(), LegalMoves()>
Author: Jiajia Xu (u6528982)

Review Comments:

1. WarringStatesGame.java: line 641-665 is well structured and use proper capitalization.
2. generateMove() can pass the GenerateMoveTest()
3. These two function first list all the possible move and then use function isMoveLegal() to search for the all legal
   move, store in a ArrayList. After that use random number generator to generate one random move from ArrayList.
4. The ideas used by these two functions were straightforward and easy to understand.
5. The task were split into two part, one is generateMove() and the other is LegalMoves(), which make the program well structured.
   GenerateMove() used to generate a random move from the ArrayList and LegalMoves() used to generate ArrayList.
6. The code use the suitable variable name.