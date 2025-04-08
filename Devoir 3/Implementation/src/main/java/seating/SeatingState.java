package seating;

public abstract class SeatingState {

  Seating seating;

  SeatingState(Seating seating) {
    this.seating = seating;
  }


  public void cancel() {

  }

  public void reserve() {

  }
}