package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class which represents different forms of the viewable form of a marble solitaire model.
 */
public abstract class ASolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable output;
  protected String board;

  /**
   * Constructor which takes in a given model for the view to represent.
   * Throws an IllegalArgumentException error if the provided model is null.
   *
   * @param model The model for this view to represent
   */
  public ASolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Provided model is null");
    }

    this.output = System.out;
    this.model = model;
    this.board = "";
  }

  /**
   * Constructor which takes in a given model for the view to represent as well as
   * an appendable object to send the view to. Throws an IllegalArgumentException if
   * the provided model or appendable object are null.
   * @param model The model for the view to represent
   * @param output The appendable object to send the view to
   */
  public ASolitaireTextView(MarbleSolitaireModelState model, Appendable output) {
    if (output == null) {
      throw new IllegalArgumentException("Appendable output cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Provided model cannot be null");
    }

    this.output = output;
    this.model = model;
    this.board = "";
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      output.append(this.toString());
      output.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      output.append(message);
      output.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed");
    }
  }
}
