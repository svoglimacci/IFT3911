package reservation;

public class Transaction {

  private int amount;
  private Reservation reservation;

  public Transaction(int amount, Reservation reservation) {
    this.amount = amount;
    this.reservation = reservation;
  }

  public boolean validate() {
    return true;
  }

}