package cs3500.marblesolitaire.model.hw02;

/**
 * Class which represents an individual marble in a game of english solitaire.
 */
public class Slot {
  private final int row;
  private final int column;
  private final MarbleSolitaireModelState.SlotState state;

  /**
   * Constructor which represents a slot with the given row number, column number, and state.
   * @param row the row number of the slot
   * @param column the column number of the slot
   * @param state the SlotState of the slot
   */
  public Slot(int row, int column, MarbleSolitaireModelState.SlotState state) {
    this.row = row;
    this.column = column;
    this.state = state;
  }

  /**
   * Returns the slot state of this slot.
   *
   * @return the slot state of this slot
   */
  public MarbleSolitaireModelState.SlotState getSlot() {
    return this.state;
  }

  /**
   * Custom equals function for Slot.
   *
   * @param other object to check for equality
   * @return true if this slot and the given slot are the same, false if not
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Slot)) {
      return false;
    }
    Slot o = (Slot) other;
    return this.row == o.row && this.column == o.column && this.state == o.state;
  }

  /**
   * Custom hashcode function.
   *
   * @return a jumbled hashcode value
   */
  @Override
  public int hashCode() {
    return this.row + this.column * 1000;
  }
}
