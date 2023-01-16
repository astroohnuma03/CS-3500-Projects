package cs3500.marblesolitaire.model.hw02;


import cs3500.marblesolitaire.model.hw04.ASolitaireModel;

/**
 * This class represents the marble solitaire model and handles building the game as well as
 * any operations handled directly by the model.
 * WHAT CHANGED: All the methods for this model have been abstracted out to the ASolitaireModel as
 * this model is the baseline for those methods, meaning there was no point in repeating them here.
 */
public class EnglishSolitaireModel extends ASolitaireModel {
  /**
   * Default constructor with an arm thickness of 3 and empty slot at center.
   */
  public EnglishSolitaireModel() {
    super();
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if ((i < 2 && (j < 2 || j > 4)) || (i > 4 && (j < 2 || j > 4))) {
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
   * Constructor with an arm thickness of 3 and empty slot at the given row and column number.
   * @param sRow The row number of the empty slot
   * @param sCol The column number of the empty slot
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
    if (sCol < 0 || sCol > 6 || sRow < 0 || sRow > 6 ||
            (sRow < 2 && (sCol > 4 || sCol < 2)) ||
            (sRow > 4 && (sCol > 4 || sCol < 2))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if ((i < 2 && (j < 2 || j > 4)) || (i > 4 && (j < 2 || j > 4))) {
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
   * Constructor which creates a board with the given arm thickness and the empty slot at the
   * center of the board.
   * @param armThickness The desired arm thickness of the board
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    int invalidLeft = (armThickness - 1);
    int invalidRight = (armThickness * 2) - 2;

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if ((i < invalidLeft && (j < invalidLeft || j > invalidRight)) ||
                (i > invalidRight && (j < invalidLeft || j > invalidRight))) {
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
   * Constructor which creates a board with the given arm thickness and the empty slot at the given
   * row and column numbers.
   * @param armThickness The desired arm thickness of the board
   * @param sRow The row number of the empty slot
   * @param sCol The column number of the empty slot
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
    int invalidLeft = (armThickness - 1);
    int invalidRight = (armThickness * 2) - 2;
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }

    if (sCol < 0 || sCol > boardSize - 1 || sRow < 0 || sRow > boardSize - 1 ||
            (sRow < invalidLeft && (sCol > invalidRight || sCol < invalidLeft)) ||
            (sRow > invalidRight && (sCol > invalidRight || sCol < invalidLeft))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if ((i < invalidLeft && (j < invalidLeft || j > invalidRight)) ||
                (i > invalidRight && (j < invalidLeft || j > invalidRight))) {
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