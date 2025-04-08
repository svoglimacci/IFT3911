package reservation;

import itinerary.Itinerary;

public class Reservation {

  private int reservationNumber;
  private Itinerary itinerary;

  private Transaction payment;
  private Transaction refund;

  public Reservation(int reservationNumber, Itinerary itinerary) {
    this.reservationNumber = reservationNumber;
    this.itinerary = itinerary;
  }

  public int getReservationNumber() {
    return reservationNumber;
  }



}