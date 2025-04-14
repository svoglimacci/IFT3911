package factory;

import company.Company;
import company.CruiseLine;
import hub.Hub;
import hub.Port;
import itinerary.Cruise;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.CruiseSection;
import section.Layout;
import section.Section;
import section.TravelClass;
import vehicle.CruiseShip;
import vehicle.Vehicle;

public class SeaFactory extends TravelFactory {

  private static final SeaFactory instance = new SeaFactory();

  private SeaFactory() {
    super();
  }

  @Override
  public Cruise createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company,  Vehicle vehicle) {
    return new Cruise(id, price, departureDate, arrivalDate, false, hubs, company, vehicle);
  }

  @Override
  public CruiseLine createCompany(String id) {
    return new CruiseLine(id);
  }

  @Override
  public CruiseShip createVehicle(String id) {
    return new CruiseShip(id);
  }

  @Override
  public Port createHub(String id, String city) {
    return new Port(id, city);
  }

  @Override
  public CruiseSection createSection(TravelClass travelclass, Layout layout, int nbSeats) {
    return new CruiseSection(travelclass, layout, nbSeats);
  }

  public static SeaFactory getInstance() {
    return instance;
  }
}