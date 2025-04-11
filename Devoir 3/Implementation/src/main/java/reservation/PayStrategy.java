package reservation;

public interface PayStrategy {
    boolean pay(int amount);
    void setPaymentDetails();

}