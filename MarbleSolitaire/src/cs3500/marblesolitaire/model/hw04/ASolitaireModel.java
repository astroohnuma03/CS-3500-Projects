package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Slot;

/**
 * The abstract class which represents different versions of a marble solitaire game.
 */
public abstract class ASolitaireModel implements MarbleSolitaireModel {
  protected Slot[][] board;
  protected int boardSize;

  /**
   * Default constructor for an abstract solitaire model with dimensions of length 3 and
   * an empty space at the center.
   */
  public ASolitaireModel() {
    this.board = new Slot[7][7];
    this.boardSize = 7;
  }

  /**
   * Constructor for an abstract solitaire model with dimensions of length 3 and an empty space
   * at the given row and column values.
   * @param sRow The row value for the empty space
   * @param sCol The column value for the empty space
   */
  public ASolitaireModel(int sRow, int sCol) {
    this.board = new Slot[7][7];
    this.boardSize = 7;
  }

  /**
   * Constructor for an abstract solitaire model with dimensions of a given length and an empty
   * space at the center of the board.
   * @param dimensions The length of the dimensions of the board
   */
  public ASolitaireModel(int dimensions) {
    int boardSize = (dimensions * 3) - 2;
    this.board = new Slot[boardSize][boardSize];
    this.boardSize = boardSize;
  }

  /**
   * Constructor for an abstract solitaire model with dimensions of a given length and an empty
   * space at the given row and column values.
   * @param dimensions The length of the dimensions of the board
   * @param sRow The row value for the empty space
   * @param sCol The column value for the empty space
   */
  public ASolitaireModel(int dimensions, int sRow, int sCol) {
    int boardSize = (dimensions * 3) - 2;
    this.board = new Slot[boardSize][boardSize];
    this.boardSize = boardSize;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow > this.boardSize - 1 || fromRow < 0 || fromCol > this.boardSize - 1 || fromCol < 0
            || toRow > this.boardSize - 1 || toRow < 0 || toCol > this.boardSize - 1 || toCol < 0) {
      throw new IllegalArgumentException(
              "Row and column numbers for the from and to slots are off the board");
    }
    Slot fromSlot = this.board[fromRow][fromCol];
    Slot toSlot = this.board[toRow][toCol];
    Slot middleSlot;
    int middleRow;
    int middleCol;

    if (fromRow + 2 == toRow && fromCol == toCol) {
      middleSlot = this.board[fromRow + 1][fromCol];
      middleRow = fromRow + 1;
      middleCol = fromCol;
    } else if (fromRow - 2 == toRow && fromCol == toCol) {
      middleSlot = this.board[fromRow - 1][fromCol];
      middleRow = fromRow - 1;
      middleCol = fromCol;
    } else if (fromRow == toRow && fromCol + 2 == toCol) {
      middleSlot = this.board[fromRow][fromCol + 1];
      middleRow = fromRow;
      middleCol = fromCol + 1;
    } else if (fromRow == toRow && fromCol - 2 == toCol) {
      middleSlot = this.board[fromRow][fromCol - 1];
      middleRow = fromRow;
      middleCol = fromCol - 1;
    } else {
      throw new IllegalArgumentException(
              "From slot is not in the correct position to move to to slot");
    }

    if (fromSlot.getSlot() != SlotState.Marble || toSlot.getSlot() != SlotState.Empty) {
      throw new IllegalArgumentException("From or to slot is not the correct SlotState");
    } else if (middleSlot.getSlot() != SlotState.Marble) {
      throw new IllegalArgumentException("There must be a marble in between the from and to slots");
    } else {
      this.board[fromRow][fromCol] = new Slot(fromRow, fromCol, SlotState.Empty);
      this.board[toRow][toCol] = new Slot(toRow, toCol, SlotState.Marble);
      this.board[middleRow][middleCol] = new Slot(middleRow, middleCol, SlotState.Empty);
    }
  }

  /**
   * Determine if the slot at the given row and column numbers has any valid moves.
   * @param row the row number of the slot to check
   * @param col the column number of the slot to check
   * @return true if the slot does have valid moves, false otherwise
   */
  private boolean anyValidMoves(int row, int col) {
    Slot slot = this.board[row][col];
    if (slot.getSlot() != SlotState.Marble) {
      return false;
    }

    if (row + 2 < this.boardSize && this.board[row + 2][col].getSlot() == SlotState.Empty &&
            this.board[row + 1][col].getSlot() == SlotState.Marble) {
      return true;
    } else if (row - 2 >= 0 && this.board[row - 2][col].getSlot() == SlotState.Empty &&
            this.board[row - 1][col].getSlot() == SlotState.Marble) {
      return true;
    } else if (col + 2 < this.boardSize && this.board[row][col + 2].getSlot() == SlotState.Empty &&
            this.board[row][col + 1].getSlot() == SlotState.Marble) {
      return true;
    } else {
      return col - 2 >= 0 && this.board[row][col - 2].getSlot() == SlotState.Empty &&
              this.board[row][col - 1].getSlot() == SlotState.Marble;
    }
  }

  @Override
  public boolean isGameOver() {
    int validMoves = 0;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (this.anyValidMoves(i, j)) {
          validMoves += 1;
        }
      }
    }
    return validMoves <= 0;
  }

  public int getBoardSize() {
    return this.boardSize;
  }

  @Override
  public SlotState getSlotAt(int row, int col) {
    if (row > this.boardSize - 1 || row < 0 || col > this.boardSize - 1 || col < 0) {
      throw new IllegalArgumentException(
              "Row or column numbers are beyond the dimension of the board");
    }
    return this.board[row][col].getSlot();
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (this.board[i][j].getSlot() == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }
}
