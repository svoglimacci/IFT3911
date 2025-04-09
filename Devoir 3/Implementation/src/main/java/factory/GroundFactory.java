package factory;


import company.Company;
import hub.Hub;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.Section;
import vehicle.Vehicle;

public class GroundFactory extends TravelFactory {

  private static final GroundFactory instance = new GroundFactory();

  private GroundFactory() {
    super();
  }

  @Override
  public Itinerary createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company,  Vehicle vehicle) {
    return null;
  }

  @Override
  public Company createCompany(String id) {
    return new Company(id);
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

  public static GroundFactory getInstance() {
    return instance;
  }

}