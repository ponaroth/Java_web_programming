package tictactoe;

public class GameBean {
	private String[] board;
	private int count;
	private String currentPlayer;
	private int gameStatus; // no conclusion = 0, draw = 1, won = 2
	private String gameMessage;

	public GameBean() {
		this.currentPlayer = "O";
		this.gameStatus = 0;
		this.count = 0;
		this.board = new String[9];
		this.gameMessage = currentPlayer + " player's turn";
		;
		initializeBoard();

	}

	// Functional //////////////////////////////////////////////////////////

	// set each value in the array to "-"
	public void initializeBoard() {
		for (int i = 0; i < 9; i++) {
			this.board[i] = "-";

		}

	}

	public void updateBoardAndCount(int placement) {

		if (board[placement] == "-") {
			board[placement] = currentPlayer;

			this.count++;

		} else {

		}

	}

	public void updatePlayer() {
		if (count % 2 != 0) {
			this.currentPlayer = "X";
		} else {
			this.currentPlayer = "O";

		}

	}

	public void updateGameStatus(int placement) {

		if (gameStatus != 2) {

			updateBoardAndCount(placement);

			updatePlayer();

			this.gameMessage = currentPlayer + " player's turn";

			// won
			if (count > 2) {
				if (board[0] != "-" && board[0] == board[1] && board[1] == board[2]
						|| board[3] != "-" && board[3] == board[4] && board[4] == board[5]
						|| board[6] != "-" && board[6] == board[7] && board[7] == board[8]
						|| board[0] != "-" && board[0] == board[3] && board[3] == board[6]
						|| board[1] != "-" && board[1] == board[4] && board[4] == board[7]
						|| board[2] != "-" && board[2] == board[5] && board[5] == board[8]
						|| board[0] != "-" && board[0] == board[4] && board[4] == board[8]
						|| board[6] != "-" && board[6] == board[4] && board[4] == board[2]) {

					this.gameStatus = 2;
					this.gameMessage = "<font color=\"green\">Game Over: player " + otherPlayer() + " won!</font>";

				}
			}

			// draw
			if (this.gameStatus != 2 && count == 9) {
				this.gameStatus = 1;

				this.gameMessage = "Game Over: It's a draw";

			}
		}
	}

	public String getMessage() {
		String message = "Player's turn: " + currentPlayer;

		// draw
		if (getWinConditon() == 0) {
			message = "It's a draw";
		}

		// won
		if (getWinConditon() == 1) {
			message = "player " + currentPlayer + " won";
		}

		return message;
	}

	public String otherPlayer() {

		if (currentPlayer == "X")
			return "O";

		return "X";
	}

	public void newGame() {

		initializeBoard();
		this.count = 0;
		this.currentPlayer = "O";
		this.gameStatus = -1;
		this.gameMessage = currentPlayer + " player's turn";

	}

	// Getters and
	// setters///////////////////////////////////////////////////////////////////

	public String[] getBoard() {
		return board;
	}

	public void setBoard(String[] board) {
		this.board = board;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getWinConditon() {
		return gameStatus;
	}

	public void setWinConditon(int winConditon) {
		this.gameStatus = winConditon;
	}

	public String getGameMessage() {
		return gameMessage;
	}

	public void setGameMessage(String gameMessage) {
		this.gameMessage = gameMessage;
	}

	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			String c = board[i];
			if (i % 3 == 0) {
				System.out.println("");
			}

			System.out.print("[" + i + "]" + c + ", ");

		}
	}

}
