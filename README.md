# ChessGame
I specifically created this chess game so I could test out algorithms for a chess AI. So far the AI has a very rudimentary level of skill, and it can already beat me. Then again, I'm a terrible chess player.

This game was made primarily by me, so that I could test out my ideas for a chess AI.  So far it mostly works, but the AI isn't very smart.  I have some ideas for upgrading it though, so we'll see how that goes.  While I wrote most of the code, I did get some of it from a tutorial on a youtube channel, so I'll give credit at the end of this document, along with features and known bugs.

Features not implemented:
1. Zerg rush in the openig menu.  A joke mode in which one side is swamped by an uneding swarm of pawns.
2. There should be a pause between taking a move, and the AI taking a move.  If possible, a bit of animation too.
3. The AI is still pretty stupid.  Make it look into the future a bit.
4. Make the game understand when a side has won, so that it can declare the game over.
5. The AI needs to learn to make a pawn turn into something else when it reaches the far side of the board.

Bugs:
1. The king can move into a spot that is threatened, provided that spot isn't threatened before it moved there.
2. Provided the king does not move, moves can be made which leave a king in check.
3. When the AI is out of moves, it throws a bunch of nullpointerexceptions and freezes the game.

Credits:
1. I, Benjamin Tyler Becker, wrote most of the code.  I'm quite proud of that.
2. Some of the code I got by watching a youtube tutorial called "Java 2D platformer" by pj6444 on Youtube.  Find it here: 
https://www.youtube.com/playlist?list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W
