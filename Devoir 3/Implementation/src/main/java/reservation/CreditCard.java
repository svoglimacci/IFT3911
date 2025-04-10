package reservation;

import java.util.Calendar;

import java.util.ArrayList;

public class CreditCard {

  private String cardHolderName;
  private int number;
  private int ccv;
  private Calendar expiration;

  private ArrayList<Transaction> transactions;

  public CreditCard(String cardHolderName, int number, int ccv, Calendar expiration) {
    this.cardHolderName = cardHolderName;
    this.number = number;
    this.ccv = ccv;
    this.expiration = expiration;
    this.transactions = new ArrayList<>();
  }


  public Object getCcNumber() {
    return number;
  }
}