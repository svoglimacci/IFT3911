package controller;

import command.Invoker;
import command.TravelType;
import itinerary.Itinerary;
import java.util.ArrayList;
import repository.Repository;
import user.Client;

public class ClientController {

  private final Repository repository;

  private final Invoker invoker;

  public ClientController(Repository repository) {
    this.repository = repository;
    this.invoker = new Invoker();
  }


  public void undo() {
    invoker.undoLast();
  }


  public ArrayList<Itinerary> displayItineraries(TravelType travelType) {
    return repository.getItineraries(travelType);
  }


  public boolean reserveSeat(Client client, String itineraryId, String travelClass, TravelType travelType, boolean isWindowSeat) {
    return repository.reserveSeat(client, itineraryId, travelClass, travelType, isWindowSeat);
  }

  public boolean paySeat(Client client, int reservationNumber, String name, String email, String passport, String ccNumber) {
    return repository.paySeat(client, reservationNumber, name, email, passport, ccNumber);
  }
}