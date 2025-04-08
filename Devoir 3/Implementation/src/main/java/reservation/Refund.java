package reservation;

public class Refund extends Transaction {

  public Refund(int amount, Reservation reservation) {
    super(amount, reservation);
  }
}