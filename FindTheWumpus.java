
//Devin Callahan and Ryan Walter
import java.util.*;

public class FindTheWumpus {

	private boolean foundWeapon = false;
	private int torchesFound = 0;
	private boolean compassFound = false;
	private gameTile[][] board;
	private Random rng = new Random();
	private int dimension;
	private gameTile wumpus = new gameTile(false, false, false, false, false, false, 0, 0);
	private gameTile player = new gameTile(false, false, false, false, false, false, 0, 0);
	private gameTile weapon = new gameTile(false, false, false, false, false, false, 0, 0);
	private gameTile torch1 = new gameTile(false, false, false, false, false, false, 0, 0);
	private gameTile torch2 = new gameTile(false, false, false, false, false, false, 0, 0);
	private gameTile compass = new gameTile(false, false, false, false, false, false, 0, 0);
	boolean wumpusAlive;
	Scanner userInput = new Scanner(System.in);
	int turn = 0;

	public static void main(String[] args) { // runs the game
		FindTheWumpus game = new FindTheWumpus();
		game.boardSize();
		game.makeBoard();
		game.menu();
	}// static void main

	public FindTheWumpus() {
	}// default constructor

	public int boardSize() { //gets the size of the board from the player
		System.out.println("Enter how many tiles long the board will be:");
		dimension = userInput.nextInt();
		return dimension;
	}// boardSize

	public void makeBoard() { //creates a board
		board = new gameTile[dimension][dimension];
		for (int row = 0; row < dimension; row++) { // creates empty board with blank tiles
			for (int col = 0; col < dimension; col++) {
				board[row][col] = new gameTile(false, false, false, false, false, false, row, col);
			} // col for-loop
		} // row for-loop
		for (int i = 0; i < 6; i++) { // fills board with special tiles
			if (i == 0) {//places player tile
				int row = rng.nextInt(dimension);
				int col = rng.nextInt(dimension);
				board[row][col].setPlayerHere(true);
				player.setRow(row);//sets coordinates of where player player is placed
				player.setCol(col);
			} else if (i == 1) {//places wumpus tile
				int row = rng.nextInt(dimension);
				int col = rng.nextInt(dimension);
				board[row][col].setWumpusHere(true);
				wumpus.setRow(row);//sets coordinates of where wumpus is placed
				wumpus.setCol(col);
				wumpusAlive = true;//sets indicator that wumpus is alive, important to end the game correctly
			} else if (i == 2) {
				int row = rng.nextInt(dimension);//places compass tile
				int col = rng.nextInt(dimension);
				board[row][col].setCompassHere(true);
				compass.setRow(row);//sets coordinates of where compass is placed
				compass.setCol(col);
			} else if (i == 3) {//places weapon tile
				int row = rng.nextInt(dimension);
				int col = rng.nextInt(dimension);
				board[row][col].setWeaponHere(true);
				weapon.setRow(row);//sets coordinates of where weapon is placed
				weapon.setCol(col);
			} else if (i == 4) {//places a torch tile "torch1"
				int row = rng.nextInt(dimension);
				int col = rng.nextInt(dimension);
				board[row][col].setTorchHere(true);
				torch1.setRow(row);//sets coordinates of where torch1 is placed
				torch1.setCol(col);
			} else if (i == 5) {//places a torch tile "torch2"
				int row = rng.nextInt(dimension);
				int col = rng.nextInt(dimension);
				board[row][col].setTorchHere(true);
				torch2.setRow(row);//sets coordinates of where torch2 is placed
				torch2.setCol(col);
			} // if-statement
		} // for-loop
	}// makeBoard

	public void displayBoard() {//displaces the board in String
		for (int row = 0; row < dimension; row++) {
			System.out.println("");//creates new line for every new row
			for (int col = 0; col < dimension; col++) {
				System.out.print("  "); //gaps in between tiles, visually appealing
				if (board[row][col] == board[player.getRow()][player.getCol()]) { //if current tile is player tile
					System.out.print("P");
				} else if (board[row][col].getExplored() == true) {//if current tile is an explored tile
					System.out.print("O");
				} else if ((Math.abs(player.getRow() - row) <= torchesFound)
						&& (Math.abs(player.getCol() - col) <= torchesFound)) { //displays more info equal to the amount of torches found
					if (board[row][col] == board[player.getRow()][player.getCol()]) {//if current tile is player tile
						System.out.print("P");
					} else if (board[row][col] == board[wumpus.getRow()][wumpus.getCol()]) {//if current tile is the wumpus tile
						System.out.print("W");
					} else if (board[row][col].getTorchHere() == true) {//if current tile is a torch tile
						System.out.print("T");
					} else if (board[row][col].getWeaponHere() == true) {//if current tile is the weapon tile
						System.out.print("A");
					} else if (board[row][col].getCompassHere() == true) {//if current tile is the compass tile
						System.out.print("C");
					} else if (board[row][col].getExplored() == true) {//if current tile is an explored tile
						System.out.print("O");
					} else {//if current tile is an unexplored tile
						System.out.print("X");
					} // if-statement
				} else {//if current tile is unexplored tile
					System.out.print("X");
				} // if-statement
			} // col for-loop
		} // row for-loop
		System.out.println("\n");
	}// dispayBoard

	public void move() { //moves the player 1 space
		int y;
		System.out.println("Enter which direction to move: \n1. North \n2. South \n3. East \n4. West");
		y = userInput.nextInt(); // stores direction chosen by player
		if (y == 1) {//if player selected 1 for NORTH
			if (player.getRow() > 0) {//checks to make sure player is not on corresponding border to the direction they are going
				player = board[player.getRow() - 1][player.getCol()];//moves player one space
				board[player.getRow() + 1][player.getCol()].setExplored(true);//sets explored to true on old space
				board[player.getRow() + 1][player.getCol()].setPlayerHere(false);//sets playerHere to false on old space
				board[player.getRow()][player.getCol()].setPlayerHere(true);//sets playerHere to true on new space
			} else {//if player was on border, stops player from moving
				System.out.println("You cannot go up any farther.");
			} // if statement
		} // if statement
		if (y == 2) {
			if (!(player.getRow() == dimension - 1)) {//checks to make sure player is not on corresponding border to the direction they are going
				player = board[player.getRow() + 1][player.getCol()];//moves player one space
				board[player.getRow() - 1][player.getCol()].setExplored(true);//sets explored to true on old space
				board[player.getRow() - 1][player.getCol()].setPlayerHere(false);//sets playerHere to false on old space
				board[player.getRow()][player.getCol()].setPlayerHere(true);//sets playerHere to true on new space
			} else {//if player was on border, stops player from moving
				System.out.println("You cannot go down any farther.");
			} // if statement
		} // if statement
		if (y == 3) {
			if (!(player.getCol() == dimension - 1)) {//checks to make sure player is not on corresponding border to the direction they are going
				player = board[player.getRow()][player.getCol() + 1];//moves player one space
				board[player.getRow()][player.getCol() - 1].setExplored(true);//sets explored to true on old space
				board[player.getRow()][player.getCol() - 1].setPlayerHere(false);//sets playerHere to false on old space
				board[player.getRow()][player.getCol()].setPlayerHere(true);//sets playerHere to true on new space
			} else {//if player was on border, stops player from moving
				System.out.println("You cannot go right any farther.");
			} // if statement
		} // if statement
		if (y == 4) {
			if (player.getCol() > 0) {//checks to make sure player is not on corresponding border to the direction they are going
				player = board[player.getRow()][player.getCol() - 1];//moves player one space
				board[player.getRow()][player.getCol() + 1].setExplored(true);//sets explored to true on old space
				board[player.getRow()][player.getCol() + 1].setPlayerHere(false);//sets playerHere to false on old space
				board[player.getRow()][player.getCol()].setPlayerHere(true);//sets playerHere to true on new space
			} else {//if player was on border, stops player from moving
				System.out.println("You cannot go left any farther.");
			} // if statement
		} // if statement
		this.giveItem();//checks if player landed on an item tile, gives corresponding item
		if ((int) (Math.sqrt((Math.pow((wumpus.getRow() - player.getRow()), 2)
				+ (Math.pow((wumpus.getCol() - player.getCol()), 2))))) == 1) { //if wumpus is one space away
			System.out.println("You find traces of wumpus fur. The wumpus is near.");
		} else if ((int) (Math.sqrt((Math.pow((wumpus.getRow() - player.getRow()), 2)
				+ (Math.pow((wumpus.getCol() - player.getCol()), 2))))) == 0) {//if player landed on wumpus space
			this.collision(0);//forced attack, sends "0" for player initiated battle	
		} // if-statement
	}// move

	public void attackWumpus() {
		int chance = rng.nextInt(100) + 1;
		int distance = (int) (Math.sqrt((Math.pow((wumpus.getRow() - player.getRow()), 2)
				+ (Math.pow((wumpus.getCol() - player.getCol()), 2)))));
		if (foundWeapon == true) {
			if ((torchesFound == 2) && (distance == 2)) {
				if (chance > 25) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} else if ((torchesFound >= 1) && (distance == 1)) {
				if (chance > 10) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} else {
				System.out.println("You swing your sword into the darkness. Nothing happens.");
			} // if-statement
		} else {
			if ((torchesFound == 2) && (distance == 2)) {
				if (chance > 85) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} else if ((torchesFound >= 1) && (distance == 1)) {
				if (chance > 70) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} else {
				System.out.println("In paranoia, you swing a fist into the darkness. Nothing happens.");
			} // if-statement
		} // if-statement
	}// attackWumpus

	public void giveItem() {
		if ((player.getRow() == weapon.getRow()) && (player.getCol() == weapon.getCol())) {
			System.out.println("You've obtained \"Weapon\"");
			foundWeapon = true;
			board[player.getRow()][player.getCol()].setWeaponHere(false);
			weapon.setRow(-2);
			weapon.setCol(-2);
		}
		if ((player.getRow() == compass.getRow()) && (player.getCol() == compass.getCol())) {
			System.out.println("You've obtained \"Compass\"");
			compassFound = true;
			board[player.getRow()][player.getCol()].setCompassHere(false);
			compass.setRow(-2);
			compass.setCol(-2);
		}
		if ((player.getRow() == torch1.getRow()) && (player.getCol() == torch1.getCol())) {
			System.out.println("You've obtained \"Torch\"");
			torchesFound++;
			board[player.getRow()][player.getCol()].setTorchHere(false);
			torch1.setRow(-2);
			torch1.setCol(-2);
		}
		if ((player.getRow() == torch2.getRow()) && (player.getCol() == torch2.getCol())) {
			System.out.println("You've obtained \"Torch\"");
			torchesFound++;
			board[player.getRow()][player.getCol()].setTorchHere(false);
			torch2.setRow(-2);
			torch2.setCol(-2);
		}
	}// giveItem

	public void menu() {
		if (wumpusAlive == false) {
		} else {
			System.out.println("\nTurn: " + turn);
			if ((torchesFound > 0) && (compassFound == true)) {
				System.out.println("Choose: \n 1. Move \n 2. Use Compass \n 3. Display Board \n 4. Attack Wumpus");
				int reply = userInput.nextInt();

				if (reply == 1) {
					this.move();
				} else if (reply == 2) {
					this.useCompass();
				} else if (reply == 3) {
					this.displayBoard();
				} else if (reply == 4) {
					attackWumpus();
				} else {
					System.out.println("Invalid response.");
					this.menu();
				} // if-statement
			} else if ((compassFound == false) && (torchesFound > 0)) {
				System.out.println("Choose: \n 1. Move \n 2. Display Board \n 3. Attack Wumpus");
				int reply = userInput.nextInt();
				if (reply == 1) {
					this.move();
				} else if (reply == 2) {
					this.displayBoard();
				} else if (reply == 3) {
					this.attackWumpus();
				} else {
					System.out.println("Invalid response.");
					this.menu();
				} // if-statement
			} else if ((torchesFound == 0) && (compassFound == true)) {
				System.out.println("Choose: \n 1. Move \n 2. Use Compass \n 3. Display Board");
				int reply = userInput.nextInt();
				if (reply == 1) {
					this.move();
				} else if (reply == 2) {
					this.useCompass();
				} else if (reply == 3) {
					this.displayBoard();
				} else {
					System.out.println("Invalid response.");
					this.menu();
				} // if-statement
			} else {
				System.out.println("Choose: \n 1. Move \n 2. Display Board");
				int reply = userInput.nextInt();
				if (reply == 1) {
					this.move();
				} else if (reply == 2) {
					this.displayBoard();
				} else {
					System.out.println("Invalid response.");
					this.menu();
				} // if-statement
			} // if-statement
			if (wumpusAlive == true) {
				this.endTurn();
				turn++;
				this.menu();
			} // if-statement
		} // if-statement
	}// menu

	public void useCompass() {

		if (compassFound = true) {
			System.out.println("Choose: \n1. Torch \n2. Weapon \n3. Wumpus");
			int reply = userInput.nextInt();

			if (reply == 1) {
				if (torchesFound == 0) {
					int distance1 = (int) (Math.sqrt((Math.pow((player.getRow() - torch1.getRow()), 2)
							+ (Math.pow((player.getCol() - torch1.getCol()), 2)))));
					int distance2 = (int) (Math.sqrt((Math.pow((player.getRow() - torch2.getRow()), 2)
							+ (Math.pow((player.getCol() - torch2.getCol()), 2)))));
					if ((distance1 > distance2) || (distance1 == distance2)) {
						this.getDirection(torch1);
					} else if (distance2 > distance1) {
						this.getDirection(torch2);
					} // if-statement
				} else if (torchesFound == 1) {
					for (int row = 0; row < dimension; row++) {
						for (int col = 0; col < dimension; col++) {
							if ((board[row][col].getTorchHere() == true)
									&& ((row == torch1.getRow()) && (col == torch1.getCol()))) {
								this.getDirection(torch1);
							} else if ((board[row][col].getTorchHere() == true)
									&& ((row == torch2.getRow()) && (col == torch2.getCol()))) {
								this.getDirection(torch2);
							} // if-statement
						} // col for-loop
					} // row for-loop
				} else if (torchesFound == 2) {
					System.out.println("The compass points to the only two torches which you already possess.");
				} // if-statement

			} else if (reply == 2) {
				if (foundWeapon == false) {
					this.getDirection(weapon);
				} else {
					System.out.println("The compass points to the weapon in your other hand.");
				} // if-statement
			} else if (reply == 3) {
				this.getDirection(wumpus);
			} // if-statement
		} else {
			System.out.println("You spend your turn looking for a compass you never had.");
		} // if-statement
	}// useCompass

	public void getDirection(gameTile request) {
		System.out.print("The compass points: ");
		if ((player.getRow() > request.getRow()) && (player.getCol() < request.getCol())) {
			System.out.println("North-East");
		} else if ((player.getRow() > request.getRow()) && (player.getCol() > request.getCol())) {
			System.out.println("North-West");
		} else if ((player.getCol() < request.getCol()) && (player.getCol() < request.getCol())) {
			System.out.println("South-East");
		} else if ((player.getCol() < request.getCol()) && (player.getCol() > request.getCol())) {
			System.out.println("South-West");
		}else if (player.getRow() > request.getRow()) {
			System.out.println("North");
		} else if (player.getRow() < request.getRow()) {
			System.out.println("South");
		} else if (player.getCol() < request.getCol()) {
			System.out.println("East");
		} else if (player.getCol() > request.getCol()) {
			System.out.println("West");
		} // if-statement
	}// getDirection

	public void endTurn() {
		int y = rng.nextInt(4) + 1;
		final gameTile temp = wumpus;
		if (y == 1) {
			if (wumpus.getRow() > 0) {
				wumpus = board[wumpus.getRow() - 1][wumpus.getCol()];
				board[wumpus.getRow() + 1][wumpus.getCol()].setWumpusHere(false);
				board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(true);
			} else {
			} // if statement
		} // if statement
		if (y == 2) {
			if (!(wumpus.getRow() == dimension - 1)) {
				wumpus = board[wumpus.getRow() + 1][wumpus.getCol()];
				board[wumpus.getRow() - 1][wumpus.getCol()].setWumpusHere(false);
				board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(true);
			} else {
			} // if statement
		} // if statement
		if (y == 3) {
			if (!(wumpus.getCol() == dimension - 1)) {
				wumpus = board[wumpus.getRow()][wumpus.getCol() + 1];
				board[wumpus.getRow()][wumpus.getCol() - 1].setPlayerHere(false);
				board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(true);
			} else {
			} // if statement
		} // if statement
		if (y == 4) {
			if (wumpus.getCol() > 0) {
				wumpus = board[wumpus.getRow()][wumpus.getCol() - 1];
				board[wumpus.getRow()][wumpus.getCol() + 1].setWumpusHere(false);
				board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(true);
			} else {
			} // if statement
		} // if statement
		if ((int) (Math.sqrt((Math.pow((wumpus.getRow() - player.getRow()), 2)
				+ (Math.pow((wumpus.getCol() - player.getCol()), 2))))) == 0) {
			this.collision(1);// assuming collision() is made and takes a "1"
								// for wumpus favored attack
		} // if-statement
	}// endTurn

	public void collision(int init) {
		int chance = rng.nextInt(100) + 1;
		// IF PLAYER INITIATES
		if (init == 0) {
			if (foundWeapon == true) {
				if (chance > 20) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} else if (foundWeapon == false) {
				if (chance > 80) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // if-statement
			} // if-statement
		} // player advantage if-statement

		// IF WUMPUS INITIATES
		if (init == 1) {
			if (foundWeapon == true) {
				if (chance > 35) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // else
			} else if (foundWeapon == false) {
				if (chance > 95) {
					System.out.println("You have slain the wumpus. \n**********\nYou win!\n**********");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} else {
					System.out.println(
							"The wumpus has eaten your fingers, and evidently, your entire body.\n************\nYou lose...\n************");
					wumpusAlive = false;
					board[wumpus.getRow()][wumpus.getCol()].setWumpusHere(false);
				} // else
			} // no weapon
		}
	}// collision
}// END