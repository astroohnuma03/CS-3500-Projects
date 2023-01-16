import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.Slot;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for all classes and methods relating to HW4.
 */
public class AbstractSolitaireModelTestHW4 {
  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;

  private MarbleSolitaireModel modelEU1;
  private MarbleSolitaireModel modelEU2;
  private MarbleSolitaireModel modelEU3;
  private MarbleSolitaireModel modelEU4;

  private MarbleSolitaireModel modelTRI1;
  private MarbleSolitaireModel modelTRI2;
  private MarbleSolitaireModel modelTRI3;
  private MarbleSolitaireModel modelTRI4;
  private MarbleSolitaireModel modelTRI5;

  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;

  @Before
  public void setUp() {

    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(5);
    model3 = new EnglishSolitaireModel(5, 6, 3);

    modelEU1 = new EuropeanSolitaireModel();
    modelEU2 = new EuropeanSolitaireModel(5);
    modelEU3 = new EuropeanSolitaireModel(5, 3, 4);
    modelEU4 = new EuropeanSolitaireModel(0, 2);

    modelTRI1 = new TriangleSolitaireModel();
    modelTRI2 = new TriangleSolitaireModel(7);
    modelTRI3 = new TriangleSolitaireModel(4, 2, 2);
    modelTRI4 = new TriangleSolitaireModel(4, 2);
    modelTRI5 = new TriangleSolitaireModel(4, 2, 0);

    view1 = new MarbleSolitaireTextView(modelEU1);
    view2 = new TriangleSolitaireTextView(modelTRI1);
  }

  @Test
  public void testConstructorsEuropean() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(3, 3));
    assertEquals(7, modelEU1.getBoardSize());
    assertEquals(36, modelEU1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU2.getSlotAt(6, 6));
    assertEquals(13, modelEU2.getBoardSize());
    assertEquals(128, modelEU2.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU3.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU4.getSlotAt(0, 2));
  }

  @Test
  public void testConstructorsTriangle() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI1.getSlotAt(0, 0));
    assertEquals(5, modelTRI1.getBoardSize());
    assertEquals(14, modelTRI1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI2.getSlotAt(0, 0));
    assertEquals(7, modelTRI2.getBoardSize());
    assertEquals(27, modelTRI2.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEUConstructorException1() {
    new EuropeanSolitaireModel(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEUConstructorException2() {
    new EuropeanSolitaireModel(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEUConstructorException3() {
    new EuropeanSolitaireModel(5, 13, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEUConstructorException4() {
    new EuropeanSolitaireModel(4, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTRIConstructorException1() {
    new TriangleSolitaireModel(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTRIConstructorException2() {
    new TriangleSolitaireModel(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTRIConstructorException3() {
    new TriangleSolitaireModel(4, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTRIConstructorException4() {
    new TriangleSolitaireModel(0, 2, 3);
  }

  @Test
  public void testGetSlot() {
    Slot slot1 = new Slot(0, 0, MarbleSolitaireModelState.SlotState.Marble);
    Slot slot2 = new Slot(1, 1, MarbleSolitaireModelState.SlotState.Empty);
    Slot slot3 = new Slot(2, 2, MarbleSolitaireModelState.SlotState.Invalid);

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, slot1.getSlot());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, slot2.getSlot());
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, slot3.getSlot());
  }

  @Test
  public void testSlotEquals() {
    Slot slot1 = new Slot(0, 1, MarbleSolitaireModelState.SlotState.Marble);
    Slot slot2 = new Slot(2, 1, MarbleSolitaireModelState.SlotState.Empty);
    Slot slot3 = new Slot(0, 1, MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(slot1, slot3);
    assertNotEquals(slot1, slot2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtException1() {
    model1.getSlotAt(8, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtException2() {
    model1.getSlotAt(2, -2);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(6, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(2, 9));
  }

  @Test
  public void testGetSlotAtEU() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU2.getSlotAt(2, 2));
  }

  @Test
  public void testGetSlotAtTRI() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelTRI1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI3.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, modelTRI2.getSlotAt(2, 6));
  }

  @Test
  public void testMove() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    model1.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 6));
    model2.move(6, 4, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 6));
  }

  @Test
  public void testMove2() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    model1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 6));
    model2.move(6, 8, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(6, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(6, 6));
  }

  @Test
  public void testMoveEU() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU4.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU4.getSlotAt(0, 2));
    modelEU4.move(2, 2, 0, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU4.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU4.getSlotAt(0, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(3, 3));
    modelEU1.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(3, 3));
  }

  @Test
  public void testMoveEU2() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(3, 3));
    modelEU1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU1.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU2.getSlotAt(6, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU2.getSlotAt(6, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU2.getSlotAt(6, 6));
    modelEU2.move(6, 8, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU2.getSlotAt(6, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEU2.getSlotAt(6, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEU2.getSlotAt(6, 6));
  }

  @Test
  public void testMoveTRI() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI1.getSlotAt(0, 0));
    modelTRI1.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI1.getSlotAt(0, 0));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI2.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI2.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI2.getSlotAt(0, 0));
    modelTRI2.move(2, 2, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI2.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI2.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI2.getSlotAt(0, 0));
  }

  @Test
  public void testMoveTRI2() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 2));
    modelTRI4.move(4, 0, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 1));
    modelTRI4.move(4, 3, 4, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI4.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI4.getSlotAt(4, 1));
  }

  @Test
  public void testMoveTRI3() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI3.getSlotAt(2, 2));
    modelTRI3.move(0, 0, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI3.getSlotAt(2, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI5.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI5.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI5.getSlotAt(2, 0));
    modelTRI5.move(0, 0, 2, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI5.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTRI5.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTRI5.getSlotAt(2, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException1() {
    model1.move(10, 11, 4, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException2() {
    model1.move(3, 4, -2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException3() {
    model1.move(6, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException4() {
    model1.move(4, 4, 2, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException5() {
    model1.move(3, 1, 3, 3);
    model1.move(3, 3, 3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException6() {
    model1.move(3, 3, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveException7() {
    model1.move(1, 3, 3, 3);
    model1.move(4, 5, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI() {
    modelTRI1.move(5, 5, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI2() {
    modelTRI1.move(2, 1, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI3() {
    modelTRI1.move(1, 0, 4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI4() {
    modelTRI1.move(1, 0, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI5() {
    modelTRI1.move(2, 0, 0, 0);
    modelTRI1.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTRI6() {
    modelTRI1.move(0, 0, 2, 0);
  }

  @Test
  public void testIsGameOver() {
    assertFalse(model1.isGameOver());
    model1.move(1, 3, 3, 3);
    model1.move(4, 3, 2, 3);
    model1.move(3, 1, 3, 3);
    model1.move(3, 4, 3, 2);
    model1.move(3, 6, 3, 4);
    model1.move(6, 3, 4, 3);
    assertTrue(model1.isGameOver());
  }

  @Test
  public void testIsGameOverEU() {
    assertFalse(modelEU4.isGameOver());
    modelEU4.move(0, 4, 0, 2);
    modelEU4.move(2, 3, 0, 3);
    modelEU4.move(1, 1, 1, 3);
    modelEU4.move(2, 1, 2, 3);
    modelEU4.move(2, 4, 0, 4);
    modelEU4.move(2, 6, 2, 4);
    modelEU4.move(4, 5, 2, 5);
    modelEU4.move(2, 4, 2, 6);
    modelEU4.move(3, 3, 3, 5);
    modelEU4.move(3, 1, 3, 3);
    modelEU4.move(5, 1, 3, 1);
    modelEU4.move(5, 2, 3, 2);
    modelEU4.move(5, 4, 3, 4);
    assertTrue(modelEU4.isGameOver());
  }

  @Test
  public void testIsGameOverTRI() {
    assertFalse(modelTRI1.isGameOver());
    modelTRI1.move(2, 0, 0, 0);
    modelTRI1.move(4, 0, 2, 0);
    modelTRI1.move(3, 2, 1, 0);
    modelTRI1.move(1, 0, 3, 0);
    modelTRI1.move(3, 0, 3, 2);
    modelTRI1.move(4, 3, 2, 1);
    modelTRI1.move(1, 1, 3, 1);
    modelTRI1.move(4, 1, 2, 1);
    modelTRI1.move(2, 2, 2, 0);
    modelTRI1.move(4, 4, 2, 2);
    assertTrue(modelTRI1.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(13, model2.getBoardSize());

    assertEquals(5, modelTRI1.getBoardSize());
    assertEquals(7, modelTRI2.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(32, model1.getScore());
    assertEquals(104, model2.getScore());
    model1.move(1, 3, 3, 3);
    model1.move(4, 3, 2, 3);
    model1.move(3, 1, 3, 3);
    model1.move(3, 4, 3, 2);
    model1.move(3, 6, 3, 4);
    model1.move(6, 3, 4, 3);
    assertEquals(26, model1.getScore());

    assertEquals(36, modelEU1.getScore());
    assertEquals(128, modelEU2.getScore());

    assertEquals(14, modelTRI1.getScore());
    assertEquals(27, modelTRI2.getScore());
  }

  @Test
  public void testToStringEU() {
    String testBoard =
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(testBoard, view1.toString());
  }

  @Test
  public void testToStringEU2() {
    String testBoard =
            "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    modelEU1.move(1, 3, 3, 3);
    assertEquals(testBoard, view1.toString());
  }

  @Test
  public void testToStringTRI() {
    String testBoard = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(testBoard, view2.toString());
  }

  @Test
  public void testToStringTRI2() {
    String testBoard = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    modelTRI1.move(2, 0, 0, 0);
    assertEquals(testBoard, view2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewException() {
    new MarbleSolitaireTextView(null);
  }
}