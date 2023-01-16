package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Slot;

/**
 * This class represents the european version of a marble solitaire game.
 */
public class EuropeanSolitaireModel extends ASolitaireModel {

  /**
   * Default constructor with a side length of 3 and empty slot at the center.
   */
  public EuropeanSolitaireModel() {
    super();

    int cornerNum = -1;

    for (int i = 0; i < 7; i++) {
      if (i < 2) {
        cornerNum += 1;
      } else if (i == 4 + 1) {
        cornerNum = 3 - 2;
      } else if (i > 4 + 1) {
        cornerNum -= 1;
      }
      for (int j = 0; j < 7; j++) {
        if ((i < 2 && (j < 2 - cornerNum || j > 4 + cornerNum)) ||
                (i > 4 && (j < 2 - cornerNum || j > 4 + cornerNum))) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == 3 && j == 3) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  /**
   * Constructor with side length 3 and empty slot at the given row and column values.
   * @param sRow The row number for the empty slot
   * @param sCol The column number for the empty slot
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
    int cornerNum = -1;
    for (int i = 0; i < 7; i++) {
      if (i < 2) {
        cornerNum += 1;
      } else if (i == 4 + 1) {
        cornerNum = 3 - 2;
      } else if (i > 4 + 1) {
        cornerNum -= 1;
      }
      if (sCol < 0 || sCol > 6 || sRow < 0 || sRow > 6 ||
              (i == sRow && sRow < 2 && (sCol > 4 + cornerNum || sCol < 2 - cornerNum)) ||
              (i == sRow && sRow > 4 && (sCol > 4 + cornerNum || sCol < 2 - cornerNum))) {
        throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol +
                ")");
      }
    }

    cornerNum = -1;
    for (int i = 0; i < 7; i++) {
      if (i < 2) {
        cornerNum += 1;
      } else if (i == 4 + 1) {
        cornerNum = 3 - 2;
      } else if (i > 4 + 1) {
        cornerNum -= 1;
      }
      for (int j = 0; j < 7; j++) {
        if ((i < 2 && (j < 2 - cornerNum || j > 4 + cornerNum)) ||
                (i > 4 && (j < 2 - cornerNum || j > 4 + cornerNum))) {
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
   * Constructor with a given, odd side length with the empty slot in the center.
   * @param sideLength The side length for the board (must be odd)
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
    if (sideLength < 0 || sideLength % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    int invalidLeft = (sideLength - 1);
    int invalidRight = (sideLength * 2) - 2;
    int cornerNum = -1;

    for (int i = 0; i < boardSize; i++) {
      if (i < invalidLeft) {
        cornerNum += 1;
      } else if (i == invalidRight + 1) {
        cornerNum = sideLength - 2;
      } else if (i > invalidRight + 1) {
        cornerNum -= 1;
      }
      for (int j = 0; j < boardSize; j++) {
        boolean corners = j < invalidLeft - cornerNum || j > invalidRight + cornerNum;
        if ((i < invalidLeft && corners) || (i > invalidRight && corners)) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == (boardSize - 1) / 2 && j == (boardSize - 1) / 2) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }

  /**
   * Constructor with a given odd side length and empty slot at the given row and column numbers.
   * @param sideLength The side length for the board (must be odd)
   * @param sRow The row number for the empty slot
   * @param sCol The column number for the empty slot
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol) {
    super(sideLength, sRow, sCol);
    if (sideLength < 0 || sideLength % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    int invalidLeft = (sideLength - 1);
    int invalidRight = (sideLength * 2) - 2;
    int cornerNum = -1;

    for (int i = 0; i < boardSize; i++) {
      if (i < invalidLeft) {
        cornerNum += 1;
      } else if (i == invalidRight + 1) {
        cornerNum = sideLength - 2;
      } else if (i > invalidRight + 1) {
        cornerNum -= 1;
      }
      if (sCol < 0 || sCol > boardSize - 1 || sRow < 0 || sRow > boardSize - 1 ||
              i == sRow && sRow < invalidLeft && (sCol < invalidLeft - cornerNum ||
                      sCol > invalidRight + cornerNum) || i == sRow && sRow > invalidRight &&
              (sCol < invalidLeft - cornerNum || sCol > invalidRight + cornerNum)) {
        throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol +
                ")");
      }
    }

    cornerNum = -1;
    for (int i = 0; i < boardSize; i++) {
      if (i < invalidLeft) {
        cornerNum += 1;
      } else if (i == invalidRight + 1) {
        cornerNum = sideLength - 2;
      } else if (i > invalidRight + 1) {
        cornerNum -= 1;
      }
      for (int j = 0; j < boardSize; j++) {
        boolean cornersLoop = j < invalidLeft - cornerNum || j > invalidRight + cornerNum;
        if ((i < invalidLeft && cornersLoop) || (i > invalidRight && cornersLoop)) {
          this.board[i][j] = new Slot(i, j, SlotState.Invalid);
        } else if (i == sRow && j == sCol) {
          this.board[i][j] = new Slot(i, j, SlotState.Empty);
        } else {
          this.board[i][j] = new Slot(i, j, SlotState.Marble);
        }
      }
    }
  }
}
