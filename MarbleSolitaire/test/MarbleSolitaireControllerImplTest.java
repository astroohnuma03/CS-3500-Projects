import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireMockModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for controller implementation.
 */
public class MarbleSolitaireControllerImplTest {

  @Test
  public void testControllerConstructor() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new StringBuilder();
    Readable input = new StringReader("1 2 4 3");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
    model = null;

    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testControllerConstructor2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new StringBuilder();
    Readable input = new StringReader("1 2 4 3");
    MarbleSolitaireView view = null;

    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided view cannot be null", e.getMessage());
    }
  }

  @Test
  public void testControllerConstructor3() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new StringBuilder();
    Readable input = null;
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);

    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided readable object cannot be null", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() {
    Appendable output = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed.");
    }

    String testBoard =
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";

    assertEquals(testBoard + "\n", output.toString());
  }

  @Test
  public void testRenderMessage() {
    Appendable output = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
    try {
      view.renderMessage("This is a test message.");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable Failed.");
    }

    assertEquals("This is a test message.\n", output.toString());
  }

  @Test
  public void testMockModelController() {
    Readable inputStream = new StringReader("2 4 4 4 q");
    Appendable uselessOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mockModel = new EnglishSolitaireMockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, uselessOutput);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, inputStream);
    controller.playGame();
    assertEquals("fromRow = 1, fromCol = 3, toRow = 3, toCol = 3\n", log.toString());
  }

  @Test
  public void testMockModelController2() {
    Readable inputStream = new StringReader("-2 dfvnsdkjds -32 5 sof 3 DWwada 2 adwd 3 q");
    Appendable uselessOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mockModel = new EnglishSolitaireMockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, uselessOutput);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, inputStream);
    controller.playGame();
    assertEquals("fromRow = 4, fromCol = 2, toRow = 1, toCol = 2\n", log.toString());
  }

  @Test
  public void testMockModelController3() {
    Readable inputStream = new StringReader("3 2 5 7 5 3 2 4 q");
    Appendable uselessOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mockModel = new EnglishSolitaireMockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mockModel, uselessOutput);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, inputStream);
    controller.playGame();
    assertEquals("fromRow = 2, fromCol = 1, toRow = 4, toCol = 6\n" +
            "fromRow = 4, fromCol = 2, toRow = 1, toCol = 3\n", log.toString());
  }

  /**
   * Takes in a list of strings, a start value, an end value and creates a string made up of the
   * list of strings, from the start value in the given array to the end value.
   *
   * @param outputArr A list of strings which represents every line of the output from our
   *                  controller running playGame()
   * @param start     The place to start creating a string from in the given array
   * @param end       The place to stop creating a string from in the given array
   * @return A string which represents a specific section of the output from the controller
   *         running playGame()
   */
  private String outputCreator(String[] outputArr, int start, int end) {
    String output = "";
    for (int i = start; i <= end; i++) {
      output += outputArr[i] + "\n";
    }
    return output;
  }

  @Test
  public void testControllerOutput() {
    Readable inputStream = new StringReader("2 4 4 4 5 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 7 4 5 4");
    Appendable outputStream = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, outputStream);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, inputStream);
    controller.playGame();
    String[] outputArr = outputStream.toString().split("\n");

    String startBoard =
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";
    String endBoard =
            "    O O O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ O _ _\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O _ O";

    // testing if the beginning of the playGame() method outputs correctly
    assertEquals(startBoard + "\nScore: 32\nEnter from row, from col, to row, to col:\n",
            this.outputCreator(outputArr, 0, 8));

    // testing if a game over outputs correctly
    assertEquals("Game over!\n" + endBoard + "\nScore: 26\n",
            this.outputCreator(outputArr, 54, 62));

    // testing if entire output is correct as well as intermediate outputs between
    // the start and end
    // NOTE: This feels like a shorter and more efficient way to test the intermediate
    // outputs than just typing them all out, but let me know if this isn't a good way to do this
    assertEquals(startBoard + "\nScore: 32\nEnter from row, from col, to row, to col:\n"
                    + this.outputCreator(outputArr, 9, 53)
                    + "Game over!\n" + endBoard + "\nScore: 26\n",
            this.outputCreator(outputArr, 0, 62));
  }

  @Test
  public void testControllerOutputWon() {
    Readable inputStream = new StringReader("4 6 4 4 2 5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 " +
            "3 7 3 5 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 " +
            "1 5 3 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5 6 5 " +
            "6 3 6 3 4 3 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4");
    Appendable outputStream = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, outputStream);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, inputStream);
    controller.playGame();
    String[] outputArr = outputStream.toString().split("\n");

    String wonBoard =
            "    _ _ _\n" +
                    "    _ _ _\n" +
                    "_ _ _ _ _ _ _\n" +
                    "_ _ _ O _ _ _\n" +
                    "_ _ _ _ _ _ _\n" +
                    "    _ _ _\n" +
                    "    _ _ _";

    // testing if the last output for a won game is correct
    assertEquals("Game over!\n" + wonBoard + "\nScore: 1\n",
            this.outputCreator(outputArr, 279, 287));

    // testing if the entire output for a won game is correct
    assertEquals(this.outputCreator(outputArr, 0, 278) +
                    "Game over!\n" + wonBoard + "\nScore: 1\n",
            this.outputCreator(outputArr, 0, 287));
  }

  @Test
  public void testControllerOutputInvalid() {
    Readable inputStream = new StringReader("2 4 4 4 13 8 4 3 4 4 4 7 4 1 4 3 4 4 2 4 q");
    Appendable outputStream = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, outputStream);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, inputStream);
    controller.playGame();
    String[] outputArr = outputStream.toString().split("\n");

    String quitBoard =
            "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";

    // testing if controller outputs correct message when given a position off the board
    assertEquals("Invalid Move. Play again. Row and column numbers for the from and " +
            "to slots are off the board\n", this.outputCreator(outputArr, 18, 18));

    // testing if controller outputs correct message when given a from and to position that are not
    // in the correct position to perform a successful move
    assertEquals("Invalid Move. Play again. From slot is not in the correct position " +
            "to move to to slot\n", this.outputCreator(outputArr, 28, 28));

    // testing if controller outputs correct message when given a from or to slot which is not
    // in the correct SlotState
    assertEquals("Invalid Move. Play again. From or to slot is not the correct SlotState\n",
            this.outputCreator(outputArr, 38, 38));

    // testing if controller outputs correct message when given a from and to slot with no marble
    // in between
    assertEquals("Invalid Move. Play again. There must be a marble in between the from " +
            "and to slots\n", this.outputCreator(outputArr, 48, 48));

    // testing if the last output for a quit game is correct
    assertEquals("Game quit!\nState of game when quit:\n" + quitBoard + "\nScore: 31\n",
            this.outputCreator(outputArr, 58, 67));

    // testing if the entire output for a quit game is correct
    assertEquals(this.outputCreator(outputArr, 0, 57) +
                    "Game quit!\nState of game when quit:\n" + quitBoard + "\nScore: 31\n",
            this.outputCreator(outputArr, 0, 67));
  }

  @Test
  public void testIOException() {
    Readable inputStream = new StringReader("1 3 3 3");
    Appendable outputStream = new FakeTestAppendable();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, outputStream);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, inputStream);

    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Appendable Failed", e.getMessage());
    }
  }

  @Test
  public void testGameDoesntEndException() {
    Readable inputStream = new StringReader("2 4 4 4");
    Appendable outputStream = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, outputStream);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, inputStream);

    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Game is not over, but inputs are empty.", e.getMessage());
    }
  }
}