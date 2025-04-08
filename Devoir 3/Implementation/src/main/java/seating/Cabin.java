package seating;

public class Cabin extends Seating {

  private int capacity;

  public Cabin(int rowNum, char columnLetter, int capacity) {
    super(rowNum, columnLetter);
    this.capacity = capacity;
  }
}