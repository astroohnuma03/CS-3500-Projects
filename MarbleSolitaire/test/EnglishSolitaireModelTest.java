import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the EnglishSolitaireModel class.
 * WHAT CHANGED: Many of the tests for this model have been abstracted out to the
 * AbstractSolitaireModelTestHW4 test file. Any remaining tests are specific to the english version
 * of the solitaire model, such as constructors and rendering boards.
 */
public class EnglishSolitaireModelTest {
  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireModel model4;

  private MarbleSolitaireView view1;

  @Before
  public void setUp() {

    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(5);
    model3 = new EnglishSolitaireModel(5, 6, 3);
    model4 = new EnglishSolitaireModel(4, 4);

    view1 = new MarbleSolitaireTextView(model1);
  }

  @Test
  public void testConstructors() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3,3));
    assertEquals(7, model1.getBoardSize());
    assertEquals(32, model1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6,6));
    assertEquals(13, model2.getBoardSize());
    assertEquals(104, model2.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(6,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(4,4));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2() {
    new EnglishSolitaireModel(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3() {
    new EnglishSolitaireModel(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException4() {
    new EnglishSolitaireModel(5, 13, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException5() {
    new EnglishSolitaireModel(4, 2, 3);
  }

  @Test
  public void testToString() {
    String testBoard =
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";
    assertEquals(testBoard, view1.toString());
  }

  @Test
  public void testToString2() {
    String testBoard =
            "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";
    model1.move(1,3,3,3);
    assertEquals(testBoard, view1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewException() {
    new MarbleSolitaireTextView(null);
  }
}