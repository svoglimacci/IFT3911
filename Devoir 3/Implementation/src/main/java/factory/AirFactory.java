package factory;

import company.Airline;
import company.Company;
import hub.Airport;
import hub.Hub;
import itinerary.Flight;
import itinerary.Itinerary;
import section.PlaneSection;
import section.Section;
import vehicle.Airplane;
import vehicle.Vehicle;

public class AirFactory extends TravelFactory {

  private static final AirFactory instance = new AirFactory();

  public AirFactory() {
    super();
  }

  @Override
  public Flight createItinerary() {
    return null;
  }


  @Override
  public Airline createCompany(String id) {

    return new Airline(id);
  }

  @Override
  public Airplane createVehicle() {
    return null;
  }

  @Override
  public Airport createHub() {
    return null;
  }

  @Override
  public PlaneSection createSection() {
    return null;
  }

  public static AirFactory getInstance() {
    return instance;
  }
}