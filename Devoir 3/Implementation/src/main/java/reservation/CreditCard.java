package reservation;

import java.util.Calendar;

import java.util.ArrayList;

public class CreditCard {

  private int amount;
  private String cardHolderName;
  private String number;
  private String ccv;
  private String expiration;

  private ArrayList<Transaction> transactions;

  public CreditCard(String cardHolderName, String number, String ccv, String expiration) {
    this.amount = 5000;
    this.cardHolderName = cardHolderName;
    this.number = number;
    this.ccv = ccv;
    this.expiration = expiration;
    this.transactions = new ArrayList<>();
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }


  public String getCcNumber() {
    return number;
  }
}