package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.ASolitaireModel;

/**
 * This class represents a mock model for the EnglishSolitaireModel to be used for testing.
 */
public class EnglishSolitaireMockModel extends ASolitaireModel {
  final StringBuilder log;

  /**
   * Constructor which takes in a StringBuilder to keep track of inputs for methods.
   *
   * @param log The StringBuilder that keeps track of the input values for methods
   */
  public EnglishSolitaireMockModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    this.log.append("fromRow = " + fromRow + ", fromCol = " + fromCol +
            ", toRow = " + toRow + ", toCol = " + toCol + "\n");
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) {
    return SlotState.Invalid;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
