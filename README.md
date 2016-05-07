# ChessGame
I specifically created this chess game so I could test out algorithms for a chess AI. So far the AI has a very rudimentary level of skill, and it can already beat me. Then again, I'm a terrible chess player.

This game was made primarily by me, so that I could test out my ideas for a chess AI.  So far it mostly works, but the AI isn't very smart.  I have some ideas for upgrading it though, so we'll see how that goes.  While I wrote most of the code, I did get some of it from a tutorial on a youtube channel, so I'll give credit at the end of this document, along with features and known bugs.

Features not implemented: <br />
1. Zerg rush in the opening menu.  A joke mode in which one side is swamped by an uneding swarm of pawns. <br />
2. There should be a pause between taking a move, and the AI taking a move.  If possible, a bit of animation too. <br />
3. The AI is still pretty stupid.  Make it look into the future a bit. <br />
4. Make the game understand when a side has won, so that it can declare the game over. <br />
5. The AI needs to learn to make a pawn turn into something else when it reaches the far side of the board. <br />
6. The AI currently doesn't add any score for threatening positions around the enemy king, making it weak in the late game. <br />

Bugs: <br />
1. The game only recognizes when the AI has been beaten.  Lines need to be added for when the AI wins, or when two humans are playing against eachother. <br />

Credits: <br />
1. I, Benjamin Tyler Becker, wrote most of the code.  I'm quite proud of that. <br />
2. Some of the code I got by watching a youtube tutorial called "Java 2D platformer" by pj6444 on Youtube.  Find it here: 
https://www.youtube.com/playlist?list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W
