package view;

import command.TravelType;
import controller.ClientController;
import itinerary.Itinerary;
import java.util.ArrayList;
import repository.Repository;
import user.Client;
import visitor.ClientTripVisitor;

public class ClientView implements IObserver {

  private final ClientController controller;

  public ClientView(Repository repository,  ClientController controller) {
    this.controller = controller;
    repository.addObserver(this);
  }

  @Override
  public void update() {

  }

  public void handleDisplayItineraries(TravelType travelType) {
    ArrayList<Itinerary> itineraries =  controller.displayItineraries(travelType);
    ClientTripVisitor clientTripVisitor = new ClientTripVisitor();
    for (Itinerary itinerary : itineraries) {
      itinerary.accept(clientTripVisitor);
    }
  }

  public void handleReserveSeat(Client client, String itineraryId, String travelClass, TravelType travelType, boolean isWindowSeat) {
    boolean res = controller.reserveSeat(client, itineraryId, travelClass, travelType, isWindowSeat);
    if (res) {
      System.out.println("Seat reserved successfully");
    } else {
      System.out.println("Failed to reserve seat");
    }
  }

  public void handlePaySeat(Client client, int reservationNumber, String name, String email, String passport) {
    boolean res = controller.paySeat(client, reservationNumber, name, email, passport);
    if (res) {
      System.out.println("Payment successful");
    } else {
      System.out.println("Payment failed");
    }
  }
}