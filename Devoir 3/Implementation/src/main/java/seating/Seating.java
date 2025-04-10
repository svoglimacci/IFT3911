package seating;

import user.Client;

public class Seating {

  private  SeatingState state;
  private int rowNum;
  private char columnLetter;
  private Client client;


  public Seating(int rowNum, char columnLetter) {
    this.rowNum = rowNum;
    this.columnLetter = columnLetter;
    this.state = new Available(this);
    this.client = null;
  }

  public boolean assignSeat(Client client) {
    if (state instanceof Available) {
      this.client = client;
      state = new Confirmed(this);
      return true;
    }
    return false;
  }

  public void cancel() {
    state.cancel();
  }

  public void reserve() {
    state.reserve();
  }

  public Object getState() {
    return state;
  }

  public int getRow() {
    return rowNum;
  }

  public char getColumn() {
    return columnLetter;
  }


  public void setState(Reserved reserved) {
    this.state = reserved;
  }

  public String getId() {
    return String.format("%d%c", rowNum, columnLetter);
  }
}