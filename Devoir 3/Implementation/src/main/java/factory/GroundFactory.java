package factory;


import company.Company;
import hub.Hub;
import itinerary.Itinerary;
import section.Section;
import vehicle.Vehicle;

public class GroundFactory extends TravelFactory {

  private static final GroundFactory instance = new GroundFactory();

  private GroundFactory() {
    super();
  }

  @Override
  public Itinerary createItinerary() {
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
  public Hub createHub() {
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