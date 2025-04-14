package seating;

public class PlaneSeat extends Seating {

  private final boolean isWindowSeat;

  public PlaneSeat(int rowNum, char columnLetter, boolean isWindowSeat) {
    super(rowNum, columnLetter);
    this.isWindowSeat = isWindowSeat;
  }


  public boolean isWindowSeat() {
    return isWindowSeat;
  }
}