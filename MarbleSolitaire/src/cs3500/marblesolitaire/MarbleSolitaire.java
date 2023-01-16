package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class which represents a playable marble solitaire game.
 */
public final class MarbleSolitaire {

  /**
   * Main method which allows us to run a game of marble solitaire on the command line and
   * accept inputs from the user.
   * @param args The arguments to be fed to the command line
   */
  public static void main(String[] args) {
    if (args.length == 1) {
      String shape = args[0];
      switch (shape) {
        case "english":
          MarbleSolitaireModel engModel = new EnglishSolitaireModel();
          Readable engInput = new InputStreamReader(System.in);
          Appendable engOutput = System.out;
          MarbleSolitaireView engView = new MarbleSolitaireTextView(engModel, engOutput);

          MarbleSolitaireController engController = new MarbleSolitaireControllerImpl(engModel,
                  engView, engInput);
          engController.playGame();
          break;
        case "european":
          MarbleSolitaireModel euModel = new EuropeanSolitaireModel();
          Readable euInput = new InputStreamReader(System.in);
          Appendable euOutput = System.out;
          MarbleSolitaireView euView = new MarbleSolitaireTextView(euModel, euOutput);

          MarbleSolitaireController euController = new MarbleSolitaireControllerImpl(euModel,
                  euView, euInput);
          euController.playGame();
          break;
        case "triangular":
          MarbleSolitaireModel triModel = new TriangleSolitaireModel();
          Readable triInput = new InputStreamReader(System.in);
          Appendable triOutput = System.out;
          MarbleSolitaireView triView = new TriangleSolitaireTextView(triModel, triOutput);

          MarbleSolitaireController triController = new MarbleSolitaireControllerImpl(triModel,
                  triView, triInput);
          triController.playGame();
          break;
        default:
          throw new IllegalArgumentException("Invalid board shape argument");
      }
    } else if (args.length == 3) {
      String shape = args[0];
      int size = Integer.parseInt(args[2]);
      switch (shape) {
        case "english":
          MarbleSolitaireModel engModel = new EnglishSolitaireModel(size);
          Readable engInput = new InputStreamReader(System.in);
          Appendable engOutput = System.out;
          MarbleSolitaireView engView = new MarbleSolitaireTextView(engModel, engOutput);

          MarbleSolitaireController engController = new MarbleSolitaireControllerImpl(engModel,
                  engView, engInput);
          engController.playGame();
          break;
        case "european":
          MarbleSolitaireModel euModel = new EuropeanSolitaireModel(size);
          Readable euInput = new InputStreamReader(System.in);
          Appendable euOutput = System.out;
          MarbleSolitaireView euView = new MarbleSolitaireTextView(euModel, euOutput);

          MarbleSolitaireController euController = new MarbleSolitaireControllerImpl(euModel,
                  euView, euInput);
          euController.playGame();
          break;
        case "triangular":
          MarbleSolitaireModel triModel = new TriangleSolitaireModel(size);
          Readable triInput = new InputStreamReader(System.in);
          Appendable triOutput = System.out;
          MarbleSolitaireView triView = new TriangleSolitaireTextView(triModel, triOutput);

          MarbleSolitaireController triController = new MarbleSolitaireControllerImpl(triModel,
                  triView, triInput);
          triController.playGame();
          break;
        default:
          throw new IllegalArgumentException("Invalid board shape argument");
      }
    } else if (args.length == 4) {
      String shape = args[0];
      int row = Integer.parseInt(args[2]);
      int col = Integer.parseInt(args[3]);
      switch (shape) {
        case "english":
          MarbleSolitaireModel engModel = new EnglishSolitaireModel(row, col);
          Readable engInput = new InputStreamReader(System.in);
          Appendable engOutput = System.out;
          MarbleSolitaireView engView = new MarbleSolitaireTextView(engModel, engOutput);

          MarbleSolitaireController engController = new MarbleSolitaireControllerImpl(engModel,
                  engView, engInput);
          engController.playGame();
          break;
        case "european":
          MarbleSolitaireModel euModel = new EuropeanSolitaireModel(row, col);
          Readable euInput = new InputStreamReader(System.in);
          Appendable euOutput = System.out;
          MarbleSolitaireView euView = new MarbleSolitaireTextView(euModel, euOutput);

          MarbleSolitaireController euController = new MarbleSolitaireControllerImpl(euModel,
                  euView, euInput);
          euController.playGame();
          break;
        case "triangular":
          MarbleSolitaireModel triModel = new TriangleSolitaireModel(row, col);
          Readable triInput = new InputStreamReader(System.in);
          Appendable triOutput = System.out;
          MarbleSolitaireView triView = new TriangleSolitaireTextView(triModel, triOutput);

          MarbleSolitaireController triController = new MarbleSolitaireControllerImpl(triModel,
                  triView, triInput);
          triController.playGame();
          break;
        default:
          throw new IllegalArgumentException("Invalid board shape argument");
      }
    }
  }
}
