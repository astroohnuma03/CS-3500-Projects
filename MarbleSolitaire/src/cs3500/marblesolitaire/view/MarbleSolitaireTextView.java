package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class which represents a viewable form of the marble solitaire model.
 * WHAT CHANGED: The renderBoard and renderMessage methods have been abstracted out to
 * ASolitaireTextView as those methods will be the same for all views.
 */
public class MarbleSolitaireTextView extends ASolitaireTextView {

  /**
   * Constructor which takes in a given model for the view to represent.
   * Throws an IllegalArgumentException error if the provided model is null.
   * NOTE: This constructor has been changed to simply super the ASolitaireTextView as the
   * constructor there does the same thing that this constructor originally did.
   *
   * @param model The model for this view to represent
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * Constructor which takes in a given model for the view to represent as well as
   * an appendable object to send the view to. Throws an IllegalArgumentException if
   * the provided model or appendable object are null.
   * NOTE: This constructor has been changed to simply super the ASolitaireTextView as the
   * constructor there does the same thing that this constructor originally did.
   * @param model The model for the view to represent
   * @param output The appendable object to send the view to
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable output) {
    super(model,output);
  }

  @Override
  public String toString() {
    this.board = "";
    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          board += " " + " ";
        } else if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          if (j + 1 == this.model.getBoardSize()) {
            board += "O" + "\n";
          } else if (this.model.getSlotAt(i, j + 1) ==
                  MarbleSolitaireModelState.SlotState.Invalid && i + 1 ==
                  this.model.getBoardSize()) {
            board += "O";
            break;
          } else if (this.model.getSlotAt(i, j + 1) ==
                  MarbleSolitaireModelState.SlotState.Invalid) {
            board += "O" + "\n";
            break;
          } else {
            board += "O" + " ";
          }
        } else {
          if (j + 1 == this.model.getBoardSize()) {
            board += "_" + "\n";
          } else if (this.model.getSlotAt(i, j + 1) ==
                  MarbleSolitaireModelState.SlotState.Invalid && i ==
                  this.model.getBoardSize() - 1) {
            board += "_";
            break;
          } else if (this.model.getSlotAt(i, j + 1) ==
                  MarbleSolitaireModelState.SlotState.Invalid) {
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