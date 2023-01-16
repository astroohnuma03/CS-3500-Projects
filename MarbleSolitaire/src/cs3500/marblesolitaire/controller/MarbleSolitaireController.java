package cs3500.marblesolitaire.controller;

/**
 * This interface represents the controller for the marble solitaire model and will allow a user
 * to play a game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of marble solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output
   */
  void playGame() throws IllegalStateException;
}
