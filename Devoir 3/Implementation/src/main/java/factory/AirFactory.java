package factory;

import company.Airline;
import company.Company;
import hub.Airport;
import hub.Hub;
import itinerary.Flight;
import java.util.ArrayList;
import java.util.Calendar;
import section.Layout;
import section.PlaneSection;
import section.TravelClass;
import vehicle.Airplane;
import vehicle.Vehicle;

public class AirFactory extends TravelFactory {

  private static final AirFactory instance = new AirFactory();


  public AirFactory() {
    super();
  }

  @Override
  public Flight createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company, Vehicle vehicle) {

    return new Flight(id, price, departureDate, arrivalDate, false, hubs, company,
        (Airplane) vehicle);

  }


  @Override
  public Airline createCompany(String id) {

    return new Airline(id);
  }

  @Override
  public Airplane createVehicle(String id) {
    return new Airplane(id);
  }

  @Override
  public Airport createHub(String id, String city) {
    return new Airport(id, city);
  }

  @Override
  public PlaneSection createSection(TravelClass travelclass, Layout layout, int nbSeats) {
    return new PlaneSection(travelclass, layout, nbSeats);
  }

  public static AirFactory getInstance() {
    return instance;
  }
}