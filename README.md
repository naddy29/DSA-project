# DSA-project
# Tic-Tac-Toe Game in Java
This project is an implementation of the classic Tic-Tac-Toe game in Java. It offers both single-player and two-player modes, with a unique feature of linked-list-based history for move tracking, allowing users to undo their moves.

# Features:
Single-Player Mode: Play against a medium-difficulty AI (computer). The AI randomly selects moves to compete with the player.
Two-Player Mode: Two human players can play against each other, alternating between 'X' and 'O'.
Undo Functionality: Players can undo their last move, adding strategic depth to the game.
Linked-List Based State Management: The game board's history is stored as a linked list, providing a dynamic way to track changes and revert states.
Winner Detection & Tie Handling: The game automatically detects win conditions and ties, announcing the winner or declaring a draw.
Reset Option: Players can reset the game board at any time to start fresh.

# How to Play:
Run the program and select the number of players (1 for Player vs AI, 2 for Player vs Player).
Input a number (1-9) to place your piece (X or O) on the board.
Players can undo their previous move by choosing 'Y' when prompted.
The game will announce the winner or declare a tie at the end.

# Future Improvements:
Implement difficulty levels for the AI.
Add a graphical interface for better user experience.
Save and load game state for continued gameplay.
