package reservation;

public class Payment extends Transaction {

  public Payment(int amount, Reservation reservation, CreditCard creditCard) {
    super(amount, reservation);
  }
}