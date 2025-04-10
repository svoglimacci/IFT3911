package reservation;

import itinerary.Itinerary;
import java.util.UUID;
import seating.Seating;
import user.Client;

public class Reservation {

  private int reservationNumber;
  private Itinerary itinerary;
  private Seating seating;
  private Client client;
  private Payment payment;
  private Refund refund;

  public Reservation(Seating seating, Itinerary itinerary, Client client) {
    this.reservationNumber = createReservationNumber();
    this.client = client;
    this.seating = seating;
    this.itinerary = itinerary;
  }

  private int createReservationNumber() {
    return (int) (Math.random() * (9999999 - 1000000 + 1) + 1000000);
  }


  public int getReservationNumber() {
    return reservationNumber;
  }

  public Itinerary getItinerary() {
    return itinerary;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Seating getSeating() {
    return seating;
  }
}