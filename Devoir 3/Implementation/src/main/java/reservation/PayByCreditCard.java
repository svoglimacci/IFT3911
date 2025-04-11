package reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PayByCreditCard implements PayStrategy {

  private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private CreditCard creditCard;

  @Override
  public void setPaymentDetails() {

    try {
      System.out.print("Enter card holder name: ");
      String cardHolderName = reader.readLine();
      System.out.print("Enter card number: ");
      String cardNumber = reader.readLine();
      System.out.print("Enter card expiration date (MM/YY): ");
      String expirationDate = reader.readLine();
      System.out.print("Enter card CVV: ");
      String cvv = reader.readLine();

      creditCard = new CreditCard(cardHolderName, cardNumber, cvv, expirationDate);
    } catch (IOException e) {
      System.out.println("Error reading card details");
    }
  }

  @Override
  public boolean pay(int amount) {
    if (creditCard.getAmount() >= amount) {
      creditCard.setAmount(creditCard.getAmount() - amount);
      System.out.println("Payment of " + amount + " successful.");
      return true;
    } else {
      System.out.println("Insufficient funds.");
      return false;
    }

  }
}