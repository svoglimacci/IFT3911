package itinerary;

import company.Company;
import hub.Hub;
import java.util.ArrayList;
import java.util.Calendar;
import vehicle.Vehicle;

public class TrainRide extends Itinerary {

  public TrainRide(String id, int price, Calendar departureDate, Calendar arrivalDate,
      boolean isAvailable, ArrayList<Hub> hubs, Company company,  Vehicle vehicle) {
    super(id, price, departureDate, arrivalDate, isAvailable, hubs, company, vehicle);
  }
}