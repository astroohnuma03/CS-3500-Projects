package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Slot;

/**
 * This class represents the triangle-shaped version of a marble solitaire game.
 */
public class TriangleSolitaireModel extends ASolitaireModel {

  /**
   * Default constructor with board dimensions of 5 (5 slots in the bottom-most row) and empty slot
   * at (0,0) (top of the triangle).
   */
  public TriangleSolitaireModel() {
    this.board = new Slot[5][5];
    this.boardSize = 5;
    int invalidSpace = -1;

    for (int i = 0; i < 5; i++) {
      invalidSpace += 1;
      for (int j = 0; j < 5; j++) {
        if (j > invalidSpace) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == 0 && j == 0) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  /**
   * Constructor with board dimensions of 5 and empty slot at the given row and column numbers.
   * @param sRow The row number for the empty slot
   * @param sCol The column number for the empty slot
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this.board = new Slot[5][5];
    this.boardSize = 5;
    int invalidSpace = -1;

    for (int i = 0; i < 5; i++) {
      invalidSpace += 1;
      if (sCol < 0 || sCol > 4 || sRow < 0 || sRow > 4 || (sRow == i && sCol > invalidSpace)) {
        throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol +
                ")");
      }
    }

    invalidSpace = -1;
    for (int i = 0; i < 5; i++) {
      invalidSpace += 1;
      for (int j = 0; j < 5; j++) {
        if (j > invalidSpace) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == sRow && j == sCol) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  /**
   * Constructor with given board dimensions and empty slot at (0,0) (top of the triangle).
   * @param dimensions The number of slots in the bottom-most row
   */
  public TriangleSolitaireModel(int dimensions) {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Invalid board dimensions");
    }
    this.board = new Slot[dimensions][dimensions];
    this.boardSize = dimensions;
    int invalidSpace = -1;

    for (int i = 0; i < this.boardSize; i++) {
      invalidSpace += 1;
      for (int j = 0; j < this.boardSize; j++) {
        if (j > invalidSpace) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == 0 && j == 0) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  /**
   * Constructor with given board dimensions and empty slot at the given row and column numbers.
   * @param dimensions The number of slots in the bottom-most row
   * @param sRow The row number for the empty slot
   * @param sCol The column number for the empty slot
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Invalid board dimensions");
    }
    this.board = new Slot[dimensions][dimensions];
    this.boardSize = dimensions;
    int invalidSpace = -1;

    for (int i = 0; i < this.boardSize; i++) {
      invalidSpace += 1;
      if (sCol < 0 || sCol > this.boardSize - 1 || sRow < 0 || sRow > this.boardSize - 1 ||
              (sRow == i && sCol > invalidSpace)) {
        throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol +
                ")");
      }
    }

    invalidSpace = -1;
    for (int i = 0; i < this.boardSize; i++) {
      invalidSpace += 1;
      for (int j = 0; j < this.boardSize; j++) {
        if (j > invalidSpace) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == sRow && j == sCol) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  // Overriding move to account for the additional/differing moves possible in a triangle solitaire
  // board.
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow > this.boardSize - 1 || fromRow < 0 || fromCol > this.boardSize - 1 || fromCol < 0
            || toRow > this.boardSize - 1 || toRow < 0 || toCol > this.boardSize - 1 ||
            toCol < 0) {
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
    } else if (fromRow + 2 == toRow && fromCol + 2 == toCol) {
      middleSlot = this.board[fromRow + 1][fromCol + 1];
      middleRow = fromRow + 1;
      middleCol = fromCol + 1;
    } else if (fromRow - 2 == toRow && fromCol - 2 == toCol) {
      middleSlot = this.board[fromRow - 1][fromCol - 1];
      middleRow = fromRow - 1;
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
   * Same as anyValidMoves from ASolitaireModel, but checks if a slot in a TriangleSolitaireModel
   * has any valid moves.
   * NOTE: This is being rewritten here to account for the additional/differing moves possible
   * in a triangle solitaire board.
   * @param row The row number of the slot to check for valid moves
   * @param col The column number of the slot to check for valid moves
   * @return True if the given slot has any valid moves it can make, false otherwise
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
    } else if (col - 2 >= 0 && this.board[row][col - 2].getSlot() == SlotState.Empty &&
            this.board[row][col - 1].getSlot() == SlotState.Marble) {
      return true;
    } else if (row + 2 < this.boardSize && col + 2 < this.boardSize &&
            this.board[row + 2][col + 2].getSlot() == SlotState.Empty &&
            this.board[row + 1][col + 1].getSlot() == SlotState.Marble) {
      return true;
    } else {
      return row - 2 >= 0 && col - 2 >= 0 && this.board[row - 2][col - 2].getSlot() ==
              SlotState.Empty && this.board[row - 1][col - 1].getSlot() == SlotState.Marble;
    }
  }

  // Ensure that isGameOver() for triangle solitaire uses the correct anyValidMoves
  @Override
  public boolean isGameOver() {
    int validMoves = 0;
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (this.anyValidMoves(i, j)) {
          validMoves += 1;
        }
      }
    }
    return validMoves <= 0;
  }
}
