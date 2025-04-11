package company;

import command.TravelType;
import itinerary.Itinerary;
import java.util.ArrayList;
import vehicle.Vehicle;

public class Company {
  private String id;

  public Company(String id) {
    ArrayList<Itinerary> itineraries = new ArrayList<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public TravelType getTravelType() {
    return null;
  }
}