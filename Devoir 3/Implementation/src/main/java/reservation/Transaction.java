package reservation;

public class Transaction {

  private int amount;
  private Reservation reservation;

  public void process(PayStrategy strategy) {
    strategy.setPaymentDetails();
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }


  public boolean validate() {
    return true;
  }

}