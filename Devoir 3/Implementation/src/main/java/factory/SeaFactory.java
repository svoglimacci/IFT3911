package factory;

import company.Company;
import hub.Hub;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.Section;
import vehicle.Vehicle;

public class SeaFactory extends TravelFactory {

  private static final SeaFactory instance = new SeaFactory();

  private SeaFactory() {
    super();
  }

  @Override
  public Itinerary createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company,  Vehicle vehicle) {
    return null;
  }

  @Override
  public Company createCompany(String id) {
    return null;
  }

  @Override
  public Vehicle createVehicle() {
    return null;
  }

  @Override
  public Hub createHub(String id, String city) {
    return null;
  }

  @Override
  public Section createSection() {
    return null;
  }

  public static SeaFactory getInstance() {
    return instance;
  }
}