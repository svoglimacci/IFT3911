package factory;


import company.Company;
import company.Railway;
import hub.Hub;
import hub.TrainStation;
import itinerary.Itinerary;
import itinerary.TrainRide;
import java.util.ArrayList;
import java.util.Calendar;
import section.Layout;
import section.Section;
import section.TrainSection;
import section.TravelClass;
import vehicle.Airplane;
import vehicle.Train;
import vehicle.Vehicle;

public class GroundFactory extends TravelFactory {

  private static final GroundFactory instance = new GroundFactory();

  private GroundFactory() {
    super();
  }

  @Override
  public TrainRide createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company,  Vehicle vehicle) {
    return new TrainRide(id, price, departureDate, arrivalDate, false, hubs, company, vehicle);
  }

  @Override
  public Railway createCompany(String id) {
    return new Railway(id);
  }

  @Override
  public Train createVehicle(String id) {
    return new Train(id);
  }

  @Override
  public TrainStation createHub(String id, String city) {
    return new TrainStation(id, city);
  }

  @Override
  public TrainSection createSection(TravelClass travelclass, Layout layout, int nbSeats ) {
    return new TrainSection(travelclass, layout, nbSeats);
  }

  public static GroundFactory getInstance() {
    return instance;
  }

}