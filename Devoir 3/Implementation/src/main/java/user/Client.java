package user;

import itinerary.Itinerary;
import java.util.ArrayList;
import reservation.CreditCard;
import reservation.Reservation;
import seating.Seating;

public class Client  extends User{

 ArrayList<Reservation> reservations = new ArrayList<>();
  ArrayList<CreditCard> creditCards = new ArrayList<>();

  public Client(String username, String password) {

    super(username, password);
  }

  public void makeReservation(Seating seating, Itinerary itinerary, Client client){

    Reservation reservation = new Reservation(seating, itinerary, client);
    reservations.add(reservation);
    System.out.println("Reservation made with number " + reservation.getReservationNumber() + " for"
        + " itinerary " + itinerary.getId() + " and seating: " + seating.getId() + " for client " + client.getUsername() + ".");

  }

  public String getUsername() {
    return super.username;
  }

  public ArrayList<Reservation> getReservations() {
    return reservations;
  }


  public Reservation getReservation(int reservationNumber) {
    for (Reservation reservation : reservations) {
      if (reservation.getReservationNumber() == reservationNumber) {
        return reservation;
      }
    }
    return null;
  }

  public CreditCard getCreditCard(String ccNumber) {
    for (CreditCard creditCard : creditCards) {
      if (creditCard.getCcNumber().equals(ccNumber)) {
        return creditCard;
      }
    }
    return null;
  }
}