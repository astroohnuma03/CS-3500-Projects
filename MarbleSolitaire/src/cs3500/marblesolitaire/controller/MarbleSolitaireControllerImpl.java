package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents the controller that will run a game of marble solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable input;
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;

  /**
   * Default constructor which takes in a MarbleSolitaireModel, a MarbleSolitaireView,
   * and a Readable object.
   * @param model The model to run the controller with
   * @param view The view representing the model to run the controller with
   * @param input The readable input the controller will take in to run the game
   */
  public MarbleSolitaireControllerImpl(
          MarbleSolitaireModel model, MarbleSolitaireView view, Readable input) {
    if (model == null) {
      throw new IllegalArgumentException("Provided model cannot be null");
    }
    if (view == null) {
      throw new IllegalArgumentException("Provided view cannot be null");
    }
    if (input == null) {
      throw new IllegalArgumentException("Provided readable object cannot be null");
    }

    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Takes a given readable input via the scanner and finds if it contains a
   * valid move and produces the move as a string array if so.
   *
   * @param s The scanner object to read inputs from
   * @return A string array that represents a valid move
   */
  private Integer[] readInput(Scanner s) {
    ArrayList<Integer> sArray = new ArrayList<Integer>(4);
    while (s.hasNext() && sArray.size() < 4) {
      if (s.hasNextInt()) {
        int num = s.nextInt();
        if (num > 0) {
          sArray.add(num);
        }
      } else if (s.next().equalsIgnoreCase("q")) {
        sArray = null;
        break;
      }
    }
    if (sArray == null) {
      return null;
    } else {
      Integer[] inputs = new Integer[sArray.size()];
      inputs = sArray.toArray(inputs);
      return inputs;
    }
  }

  @Override// REMINDER: Fix IOException issue with renderBoard and renderMessage
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(this.input);
    boolean quit = false;
    try {
      while (!this.model.isGameOver()) {

        if (!scanner.hasNext()) {
          throw new IllegalStateException("Game is not over, but inputs are empty.");
        }

        this.view.renderBoard();

        this.view.renderMessage("Score: " + this.model.getScore());

        this.view.renderMessage("Enter from row, from col, to row, to col:");

        Integer[] inputs = this.readInput(scanner);

        if (inputs == null) {
          quit = true;
          scanner.close();
          break;
        } else if (inputs.length < 4) {
          this.view.renderMessage("Not enough valid inputs. Please reenter values:");
        } else {
          int fromRow = inputs[0] - 1;
          int fromCol = inputs[1] - 1;
          int toRow = inputs[2] - 1;
          int toCol = inputs[3] - 1;

          try {
            this.model.move(fromRow, fromCol, toRow, toCol);
          } catch (IllegalArgumentException e) {

            if (e.getMessage().equals(
                    "Row and column numbers for the from and to slots are off the board")) {
              this.view.renderMessage("Invalid Move. Play again. " + e.getMessage());
            } else if (e.getMessage().equals(
                    "From slot is not in the correct position to move to to slot")) {
              this.view.renderMessage("Invalid Move. Play again. " + e.getMessage());
            } else if (e.getMessage().equals("From or to slot is not the correct SlotState" )) {
              this.view.renderMessage("Invalid Move. Play again. " + e.getMessage());
            } else if (e.getMessage().equals(
                    "There must be a marble in between the from and to slots")) {
              this.view.renderMessage("Invalid Move. Play again. " + e.getMessage());
            }
          }
        }
      }
      if (quit) {
        this.view.renderMessage("Game quit!");

        this.view.renderMessage("State of game when quit:");
      } else {
        scanner.close();
        this.view.renderMessage("Game over!");
      }
      this.view.renderBoard();
      this.view.renderMessage("Score: " + this.model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed");
    }
  }
}
