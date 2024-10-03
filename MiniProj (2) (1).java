//MINI PROJECT
//564,566,567,568
package dsa;
import java.util.Random;//Imported for generating Computer's Moves
import java.util.Scanner;

class Box {// node repersenting a single unit of the tic tac toe board
	char data;
	Box link;

}

class Human {// class for accepting information about the player in 2-player game

	String name;
	char symbol;
	Human(String name) {
		this.name = name;
	}

}

class TicTacToe {

	Scanner scanner = new Scanner(System.in);
	Box[][] board;
	int xyz = 1;
	int undo_count = 0;
	char disp = 49;

	TicTacToe() {

		board = new Box[3][3];// tictactoe board as a 2-D array of linked list for storing previous state

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				board[i][j] = new Box();
				board[i][j].data = disp;
				disp++;
			}
		}
	}

	void change_state(char ch, int x, int y) {//To update the state of Board after every move

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == x && j == y) {
					Box temp = new Box();
					temp.data = ch;
					temp.link = board[x][y];
					board[x][y] = temp;

				} else {
					Box temp = new Box();
					temp.data = board[i][j].data;
					temp.link = board[i][j];
					board[i][j] = temp;

				}
			}
		}
		xyz++;

		if (xyz == 4) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {

					board[i][j].link.link.link = null; // list is only of three nodes out of which one is the head
				}
			}

		}

	}

	void display() {// method for display the current state of the board

		System.out.println("===========");

		System.out.println("|" + board[0][0].data + " | " + board[0][1].data + " | " + board[0][2].data + "|");

		System.out.println("|==|===|==|");

		System.out.println("|" + board[1][0].data + " | " + board[1][1].data + " | " + board[1][2].data + "|");

		System.out.println("|==|===|==|");

		System.out.println("|" + board[2][0].data + " | " + board[2][1].data + " | " + board[2][2].data + "|");

		System.out.println("===========");

	}

	void Reset() {
		disp = 49;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				board[i][j].data = disp;
				disp++;
				board[i][j].link = null;

			}

		}

	}

	void Input(int players) {

		// Reset the board to its initial empty state
		Reset();

		if (players == 1) {// Player vs Computer

			System.out.println("You are 'X', and the computer is 'O'.");

			int pos = 0;

			String currentPlayer = "player1";

			do {

				if (currentPlayer.equals("player1")) {
					int flag = 1;
					while (flag != 0) {
						System.out.println("Enter from 1-9 where you want to put 'X': ");

						pos = scanner.nextInt();
						scanner.nextLine();// to clear buffer

						if (isValidMove(board, pos)) {

							placePiece(board, pos, "player1");

							display();
							System.out.println("Do you want to undo?Y/N");
							char Y_N = scanner.nextLine().charAt(0);
							if (Y_N == 'Y') {
								undo();
								display();
							} else {
								flag = 0;
							}
						} else {

							System.out.println("Invalid move. Try again.");

						}
					}

					currentPlayer = "player2";

					// Check for a win after each move

					if (checkForWin('X')) {

						display();

						System.out.println("--------------!!!!!PLAYER 1 WINS!!!!!--------------");

						break;

					}

					else if (checkForWin('O')) {

						display();

						System.out.println("--------------COMPUTER WINS:(--------------");

						break;

					}

				} else {

					int aiMove = getMediumLevelMove(board);

					placePiece(board, aiMove, "player2");

					System.out.println("AI (Player 2) chooses position " + aiMove);

					currentPlayer = "player1";

				}

				display();

			} while (pos != 10);

		}

		else if (players == 2) {// Player1 vs Player2

			Reset();
			String n1, n2;

			int opn;

			do {

				Reset();
				System.out.println("Enter the name of First player (X)");
				n1 = scanner.next();

				System.out.println();
				System.out.println("Enter the name of Second player (0)");
				n2 = scanner.next();

				System.out.println();
				System.out.println("Starting the Game :)");
				display();
				do {
					Reset();
					Human p1 = new Human(n1);
					Human p2 = new Human(n2);
					Human cp; // creating a reference of class human not an object
					cp = p1;
					while (true) {
						int p;
						System.out.println("Player " + cp.name + "'s turn ");
						System.out.println();

						if (cp == p1) {
							int flag = 1;
							while (flag != 0) {
								System.out.println("Enter from 1-9 where you want to put 'X': ");

								p = scanner.nextInt();
								scanner.nextLine();// to clear buffer

								if (isValidMove(board, p)) {

									placePiece(board, p, "player1");

									display();
									System.out.println("Do you want to undo?Y/N");
									char Y_N = scanner.nextLine().charAt(0);
									if (Y_N == 'Y') {
										undo();
										display();
									} else {
										flag = 0;
									}
								} else {

									System.out.println("Invalid move. Try again.");

								}
							}
						} else if (cp == p2) {
							int flag = 1;
							while (flag != 0) {
								System.out.println("Enter from 1-9 where you want to put 'O': ");

								p = scanner.nextInt();
								scanner.nextLine();// to clear buffer

								if (isValidMove(board, p)) {

									placePiece(board, p, "player2");

									display();
									System.out.println("Do you want to undo?Y/N");
									char Y_N = scanner.nextLine().charAt(0);
									if (Y_N == 'Y') {
										undo();
										display();
									} else {
										flag = 0;
									}
								} else {

									System.out.println("Invalid move. Try again.");

								}
							}
						}

						if (checkForWin('X') || checkForWin('O')) {
							System.out.println("************:) CONGRATULATIONS :)***************");

							System.out.println("     +----------------------------------+");
							System.out.println("     |:) Player " + cp.name + " has Won the Game :) |");

							System.out.println("     +----------------------------------+");

							break;
						}

						else if (isBoardFull()) {
							System.out.println("Its a Tie");
							System.out.println("Better Luck Next Time :)");
							System.out.println();

							break;
						} else {
							if (cp == p1) {
								cp = p2;
							} else {
								cp = p1;
							}
						}
					}
					System.out.println("---------------------------------------------------------");
					System.out.println("Enter \n 1. Play Again \n 2.Start a New Game>>> \n 3.Exit");
					opn = scanner.nextInt();// Choice to start a new game or exit
				}

				while (opn == 1);
			} while (opn == 2);

		}

	}

	boolean isBoardFull() {//Checks if the Board is completely filled (Condition for Tie)

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (board[i][j].data != 'X' && board[i][j].data != 'O') {

					return false;

				}
			}
		}

		return true;
	}

	void undo() {//To undo the current move
		int flag = 0;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (board[i][j].link != null) {

					board[i][j].data = board[i][j].link.data;
					board[i][j].link = board[i][j].link.link;
					xyz--;
					flag = 1;
				}
			}
		}
		if (flag == 0) {
			System.out.println("Undo not possible!!");
		}

	}

	void placePiece(Box[][] board, int pos, String user) {//To arrange moves on Game Board

		char symbol = ' ';

		if (user.equals("player1")) {

			symbol = 'X';

		} else if (user.equals("player2")) {

			symbol = 'O';

		}

		Box temp = new Box();

		switch (pos) {

		case 1:
			change_state(symbol, 0, 0);

			break;

		case 2:
			change_state(symbol, 0, 1);

			break;

		case 3:
			change_state(symbol, 0, 2);

			break;

		case 4:
			change_state(symbol, 1, 0);

			break;

		case 5:
			change_state(symbol, 1, 1);

			break;

		case 6:
			change_state(symbol, 1, 2);

			break;

		case 7:

			change_state(symbol, 2, 0);

			break;

		case 8:

			change_state(symbol, 2, 1);

			break;

		case 9:

			change_state(symbol, 2, 2);

			break;

		}

	}

	int getMediumLevelMove(Box[][] board) {// To generate computer move in 1 player game

		Random random = new Random();

		int aiMove;

		do {

			aiMove = random.nextInt(9) + 1; // Generate a random move between 1 and 9 (+1 because random generates a
											// number between 0 to 8 and we want between 1 to 9.

		} while (!isValidMove(board, aiMove));

		return aiMove;

	}

	boolean isValidMove(Box[][] board, int pos) {// Checks if the place is taken already

		switch (pos) {

		case 1:

			return board[0][0].data != 'X' && board[0][0].data != 'O';

		case 2:

			return board[0][1].data != 'X' && board[0][1].data != 'O';

		case 3:

			return board[0][2].data != 'X' && board[0][2].data != 'O';

		case 4:

			return board[1][0].data != 'X' && board[1][0].data != 'O';

		case 5:

			return board[1][1].data != 'X' && board[1][1].data != 'O';

		case 6:

			return board[1][2].data != 'X' && board[1][2].data != 'O';

		case 7:

			return board[2][0].data != 'X' && board[2][0].data != 'O';

		case 8:

			return board[2][1].data != 'X' && board[2][1].data != 'O';

		case 9:

			return board[2][2].data != 'X' && board[2][2].data != 'O';

		default:

			return false;

		}

	}

	boolean checkForWin(char symbol) {// Checks for winning condition

		// Check rows, columns, and diagonals for a win

		for (int i = 0; i < 3; i++) {

			// Check rows

			if (board[i][0].data == symbol && board[i][1].data == symbol && board[i][2].data == symbol) {

				return true;

			}

			// Check columns

			if (board[0][i].data == symbol && board[1][i].data == symbol && board[2][i].data == symbol) {

				return true;

			}

		}

		// Check diagonals

		if ((board[0][0].data == symbol && board[1][1].data == symbol && board[2][2].data == symbol) ||

				(board[0][2].data == symbol && board[1][1].data == symbol && board[2][0].data == symbol)) {
			return true;
		}

		return false;

	}

}

public class MiniProj {// MAIN METHOD

	public static void main(String[] args) {

		int choice;
		TicTacToe obj = new TicTacToe();

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"_____________________________________________________________________________________________");
		System.out.println(
				"\n:::::::::::::::::::::::WELCOME TO THE AMAZING WORLD OF TIC-TAC-TOE:::::::::::::::::::::::::");

		System.out.println(
				"Embark on a thrilling journey to test your wits and strategic ability in this classic game!\n");
		System.out.println(
				"_____________________________________________________________________________________________");
		do {

			System.out.println("Would you like to:\n1. PLAY :)\n\n2. EXIT :(");

			choice = sc.nextInt();

			switch (choice) {

			case 1:// Play

				System.out.println("How many of you are playing?\n1. Only 1 player\n\n2. Two players");
				int players = sc.nextInt();
				obj.Input(players);
				break;

			case 2:// Exit

				System.out.println("********************Thanks for playing!!Goodbye!********************");
				break;

			default:

				System.out.println("Invalid choice. Exiting.");

			}

		} while (choice != 2);

	}

}
/*
_____________________________________________________________________________________________

:::::::::::::::::::::::WELCOME TO THE AMAZING WORLD OF TIC-TAC-TOE:::::::::::::::::::::::::
Embark on a thrilling journey to test your wits and strategic ability in this classic game!

_____________________________________________________________________________________________
Would you like to:
1. PLAY :)

2. EXIT :(
1
How many of you are playing?
1. Only 1 player

2. Two players
1
You are 'X', and the computer is 'O'.
Enter from 1-9 where you want to put 'X': 
2
===========
|1 | X | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | 8 | 9|
===========
Do you want to undo?Y/N
n
===========
|1 | X | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | 8 | 9|
===========
AI (Player 2) chooses position 7
===========
|1 | X | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|O | 8 | 9|
===========
Enter from 1-9 where you want to put 'X': 
1
===========
|X | X | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|O | 8 | 9|
===========
Do you want to undo?Y/N
n
===========
|X | X | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|O | 8 | 9|
===========
AI (Player 2) chooses position 6
===========
|X | X | 3|
|==|===|==|
|4 | 5 | O|
|==|===|==|
|O | 8 | 9|
===========
Enter from 1-9 where you want to put 'X': 
3
===========
|X | X | X|
|==|===|==|
|4 | 5 | O|
|==|===|==|
|O | 8 | 9|
===========
Do you want to undo?Y/N
Y
===========
|X | X | 3|
|==|===|==|
|4 | 5 | O|
|==|===|==|
|O | 8 | 9|
===========
Enter from 1-9 where you want to put 'X': 
3
===========
|X | X | X|
|==|===|==|
|4 | 5 | O|
|==|===|==|
|O | 8 | 9|
===========
Do you want to undo?Y/N
n
===========
|X | X | X|
|==|===|==|
|4 | 5 | O|
|==|===|==|
|O | 8 | 9|
===========
--------------!!!!!PLAYER 1 WINS!!!!!--------------
Would you like to:
1. PLAY :)

2. EXIT :(
1
How many of you are playing?
1. Only 1 player

2. Two players
2
Enter the name of First player (X)
nandini

Enter the name of Second player (0)
aastha

Starting the Game :)
===========
|1 | 2 | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | 8 | 9|
===========
Player nandini's turn 

Enter from 1-9 where you want to put 'X': 
8
===========
|1 | 2 | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | X | 9|
===========
Do you want to undo?Y/N
n
Player aastha's turn 

Enter from 1-9 where you want to put 'O': 
2
===========
|1 | O | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | X | 9|
===========
Do you want to undo?Y/N
n
Player nandini's turn 

Enter from 1-9 where you want to put 'X': 
9
===========
|1 | O | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | X | X|
===========
Do you want to undo?Y/N
Y
===========
|1 | O | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|7 | X | 9|
===========
Enter from 1-9 where you want to put 'X': 
7
===========
|1 | O | 3|
|==|===|==|
|4 | 5 | 6|
|==|===|==|
|X | X | 9|
===========
Do you want to undo?Y/N
n
Player aastha's turn 

Enter from 1-9 where you want to put 'O': 
5
===========
|1 | O | 3|
|==|===|==|
|4 | O | 6|
|==|===|==|
|X | X | 9|
===========
Do you want to undo?Y/N
n
Player nandini's turn 

Enter from 1-9 where you want to put 'X': 
3
===========
|1 | O | X|
|==|===|==|
|4 | O | 6|
|==|===|==|
|X | X | 9|
===========
Do you want to undo?Y/N
n
Player aastha's turn 

Enter from 1-9 where you want to put 'O': 
9
===========
|1 | O | X|
|==|===|==|
|4 | O | 6|
|==|===|==|
|X | X | O|
===========
Do you want to undo?Y/N
n
Player nandini's turn 

Enter from 1-9 where you want to put 'X': 
1
===========
|X | O | X|
|==|===|==|
|4 | O | 6|
|==|===|==|
|X | X | O|
===========
Do you want to undo?Y/N
n
Player aastha's turn 

Enter from 1-9 where you want to put 'O': 
4
===========
|X | O | X|
|==|===|==|
|O | O | 6|
|==|===|==|
|X | X | O|
===========
Do you want to undo?Y/N
n
Player nandini's turn 

Enter from 1-9 where you want to put 'X': 
6
===========
|X | O | X|
|==|===|==|
|O | O | X|
|==|===|==|
|X | X | O|
===========
Do you want to undo?Y/N
n
Its a Tie
Better Luck Next Time :)

---------------------------------------------------------
Enter 
 1. Play Again 
 2.Start a New Game>>> 
 3.Exit
3
Would you like to:
1. PLAY :)

2. EXIT :(
2
********************Thanks for playing!!Goodbye!********************

*/