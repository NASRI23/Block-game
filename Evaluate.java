
public class Evaluate {
	// declare variables. 
	private char [][] gameBoard;
	private int tTW;

	//Constructor that has the variables size, tiles to win and the maximum level. 
	public Evaluate(int size, int tilesToWin, int MaxLevels) {
		tTW=tilesToWin;
		// Initialize the size of the game board to size*size
		gameBoard=new char[size][size];
		// for loop that assigns everything in the board to empty. 
		for (int i=0;i<gameBoard.length;i++) {
			for (int j=0;j<gameBoard[i].length;j++) {
				gameBoard[i][j]='e';
			}
		}
	}

	// a method that creates the dictionary with a unique size that I chose. 
	public Dictionary createDictionary() {
		// my size is 7057
		Dictionary dic=new Dictionary(7057);
		return dic;}

	// a method that returns the repeated game boards. 
	public Record repeatedState(Dictionary dict) {
		// creates a new string 
		String gameBoardState="";

		// for loop assigning the game board values to the string.
		for (int i=0;i<gameBoard.length;i++) {
			for (int j=0;j<gameBoard[i].length;j++) {
				gameBoardState+=gameBoard[i][j];
			}}

		System.out.println(gameBoardState);
		// if the get method returns null, it means that the record was never in dict.
		if (dict.get(gameBoardState)==null)
			return null;
		// else if it returns any value. then it's the record value.
		else 
			return dict.get(gameBoardState);
	}

	// a method that puts the values; score and level in the dict using the string created from the game board. 
	public void insertState(Dictionary dict, int score, int level) {
		String gameBoardState="";

		// for loop assigning the game board values to the string.
		for (int i=0;i<gameBoard.length;i++) {
			for (int j=0;j<gameBoard[i].length;j++) {
				gameBoardState+=gameBoard[i][j];
			}}
		System.out.println(gameBoardState);

		// creates a new record and assign all the values to it.
		Record newRecord=new Record(gameBoardState,score,level);

		//then puts it in dict
		dict.put(newRecord);
	}

	// store the symbol in the proper location in game board.
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col]=symbol;
	}

	// method that checks if a certain square is empty at a location in the 2d array. 
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col]=='e')
			return true;
		else return false;
	}

	//method that checks if a certain square is computer's at a location in the 2d array. 
	public boolean tileOfComputer (int row, int col) {
		if (gameBoard[row][col]=='c')
			return true;
		else return false;
	}

	// method that checks if a certain square is human's at a location in the 2d array. 
	public boolean tileOfHuman(int row, int col) {
		if (gameBoard[row][col]=='h')
			return true;
		else return false;
	}

	// a method that returns win if someone won the game.
	// it takes a symbol, either a human or computer, and checks if anyone won at that state. 
	public boolean wins (char symbol) {
		// count int, to count how many tiles are the same in a row. 
		// whenever it's equal to the number of tiles it should return a true value. 
		int count=0;
		// for loop that goes top to bottom, the one step right; checking if there's any wins. 
		for (int i=0;i<gameBoard.length;i++) {
			count=0;
			for (int j=0;j<gameBoard[i].length;j++) {
				if (gameBoard[i][j]==symbol)
					count++;
				if (gameBoard[i][j]!=symbol)
					count=0;
				if (count==tTW)
					return true;
			}
		}

		// for loop that goes right to left, the one step down; checking if there's any wins. 
		for (int i=0;i<gameBoard.length;i++) {
			count=0;
			for (int j=0;j<gameBoard[i].length;j++) {
				if (gameBoard[j][i]==symbol)
					count++;
				if (gameBoard[j][i]!=symbol)
					count=0;
				if (count==tTW)
					return true;
			}
		}

		// while loop that goes top left corner, to bottom right corner and checks for any diagonal wins. 
		int j;
		int x;
		int i=-1;
		while (i!=gameBoard.length-1) {
			i++;
			j=0;
			x=i;
			count=0;
			while (x!=gameBoard.length) {
				if (gameBoard[x][j]==symbol)
					count++;
				if (gameBoard[x][j]!=symbol)
					count=0;
				if (count==tTW)
					return true;
				x++;
				j++;
			}
		}
		i=-1;
		count=0;

		// while loop that goes top left corner, to bottom left corner and checks for any diagonal wins. 
		while (i!=gameBoard.length-1) {
			i++;
			j=0;
			x=i;
			count=0;
			while (x!=gameBoard.length) {
				if (gameBoard[j][x]==symbol)
					count++;
				if (gameBoard[j][x]!=symbol)
					count=0;
				if (count==tTW)
					return true;
				x++;
				j++;
			}
		}
		i=gameBoard.length;
		count=0;

		// while loop that goes top right corner, to bottom right corner and checks for any diagonal wins. 
		while (i!=0) {
			i--;
			j=0;
			x=i;
			count=0;
			while (x!=-1) {
				if (gameBoard[x][j]==symbol)
					count++;
				if (gameBoard[x][j]!=symbol)
					count=0;
				if (count==tTW)
					return true;
				x--;
				j++;
			}
		}
		i=-1;
		count=0;

		// while loop that goes top right corner, to top left corner and checks for any diagonal wins. 
		while (i!=gameBoard.length) {
			i++;
			j=gameBoard.length-1;
			x=i;
			count=0;
			while (x!=gameBoard.length) {
				if (gameBoard[x][j]==symbol)
					count++;
				if (gameBoard[x][j]!=symbol)
					count=0;
				if (count==tTW)
					return true;
				x++;
				j--;
			}
		}

		// if there is no wins, then returns false. 
		return false;
	}

	// a method that checks if there is any empty squares. if there's none it returns true for draw. 
	public boolean isDraw() {

		// it does that using a for loop that goes through the 2d array. 
		for (int i=0;i<gameBoard.length;i++) {
			for (int j=0;j<gameBoard[i].length;j++) {
				// if it finds an empty square then it returns false. 
				if (gameBoard[i][j]=='e')
					return false;
			}}
		return true;
	}

	// method that distributes the points depending on the state of the game. 
	public int evalBoard() {

		if (wins('h'))
			return 0;
		else if (wins('c'))
			return 3;
		else if (isDraw())
			return 2;
		else 
			return 1;
	}
}
