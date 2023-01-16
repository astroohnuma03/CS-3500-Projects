package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class which represents the viewable form of a TriangleSolitaireModel.
 */
public class TriangleSolitaireTextView extends ASolitaireTextView {

  /**
   * Constructor which takes in a given TriangleSolitaireModel for the view to represent.
   * Throws an IllegalArgumentException error if the provided model is null.
   *
   * @param model The TriangleSolitaireModel for this view to represent
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * Constructor which takes in a given TriangleSolitaireModel for the view to represent as well as
   * an appendable object to send the view to. Throws an IllegalArgumentException if
   * the provided model or appendable object are null.
   * @param model The TriangleSolitaireModel for the view to represent
   * @param output The appendable object to send the view to
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable output) {
    super(model,output);
  }

  /**
   * Helper method which adds spaces to the board to align everything correctly to create a
   * triangle. The number of spaces needed is found by subtracting one from the boardSize and
   * then subtracting whatever row number the board is currently on to create a triangle.
   * @param row The row number the board is currently on
   * @return A string which represents the number of spaces needed to align everything correctly
   */
  private String spaceAlign(int row) {
    int spaceNum = (this.model.getBoardSize() - 1) - row;
    String result = "";
    for (int i = 0; i < spaceNum; i++) {
      result += " ";
    }

    return result;
  }

  @Override
  public String toString() {
    this.board = "";
    MarbleSolitaireModelState.SlotState  marble = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState  empty = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState  invalid = MarbleSolitaireModelState.SlotState.Invalid;

    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        if (j == 0) {
          if (this.model.getSlotAt(i,j) == marble && j + 1 == this.model.getBoardSize()) {
            this.board += this.spaceAlign(i) + "O";
            break;
          } else if (this.model.getSlotAt(i,j) == marble && this.model.getSlotAt(i,j + 1) ==
                  invalid) {
            this.board += this.spaceAlign(i) + "O" + "\n";
            break;
          } else if (this.model.getSlotAt(i,j) == marble && (this.model.getSlotAt(i,j + 1) ==
                  marble || this.model.getSlotAt(i,j + 1) == empty)) {
            this.board += this.spaceAlign(i) + "O" + " ";
          } else if (this.model.getSlotAt(i,j) == empty && j + 1 == this.model.getBoardSize()) {
            this.board += this.spaceAlign(i) + "_";
            break;
          } else if (this.model.getSlotAt(i,j) == empty && this.model.getSlotAt(i,j + 1) ==
                  invalid) {
            this.board += this.spaceAlign(i) + "_" + "\n";
            break;
          } else if (this.model.getSlotAt(i,j) == empty && (this.model.getSlotAt(i,j + 1) ==
                  marble || this.model.getSlotAt(i,j + 1) == empty)) {
            this.board += this.spaceAlign(i) + "_" + " ";
          }
        } else if (this.model.getSlotAt(i,j) == marble) {
          if (j + 1 == this.model.getBoardSize() && i + 1 == this.model.getBoardSize()) {
            this.board += "O";
            break;
          } else if (j + 1 == this.model.getBoardSize()) {
            this.board += "O" + "\n";
          } else if (this.model.getSlotAt(i, j + 1) == invalid && i + 1 ==
                  this.model.getBoardSize()) {
            board += "O";
            break;
          } else if (this.model.getSlotAt(i, j + 1) == invalid) {
            board += "O" + "\n";
            break;
          } else {
            board += "O" + " ";
          }
        } else if (this.model.getSlotAt(i,j) == empty) {
          if (j + 1 == this.model.getBoardSize() && i + 1 == this.model.getBoardSize()) {
            this.board += "_";
            break;
          } else if (j + 1 == this.model.getBoardSize()) {
            this.board += "_" + "\n";
          } else if (this.model.getSlotAt(i, j + 1) == invalid && i + 1 ==
                  this.model.getBoardSize()) {
            board += "_";
            break;
          } else if (this.model.getSlotAt(i, j + 1) == invalid) {
            board += "_" + "\n";
            break;
          } else {
            board += "_" + " ";
          }
        }
      }
    }
    return this.board;
  }
}
