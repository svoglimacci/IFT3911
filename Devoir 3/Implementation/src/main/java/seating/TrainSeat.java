package seating;

public class TrainSeat extends Seating {

  public TrainSeat(int rowNum, char columnLetter,  boolean isWindowSeat) {
    super(rowNum, columnLetter);
    this.isWindowSeat = isWindowSeat;
  }

  private final boolean isWindowSeat;

  public boolean isWindowSeat() {
    return isWindowSeat;
  }

}