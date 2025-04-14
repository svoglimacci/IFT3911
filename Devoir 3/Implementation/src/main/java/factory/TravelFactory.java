package factory;


import command.TravelType;
import company.Airline;
import company.Company;
import hub.Airport;
import java.util.ArrayList;
import java.util.Calendar;
import section.Layout;
import section.PlaneSection;
import section.Section;
import section.TravelClass;
import vehicle.Vehicle;
import hub.Hub;
import itinerary.Itinerary;

public abstract class TravelFactory {


  TravelFactory() {}

  public static TravelFactory getInstance(TravelType type) {
    return switch (type) {
      case SEA -> SeaFactory.getInstance();
      case GROUND -> GroundFactory.getInstance();
      case AIR -> AirFactory.getInstance();
      default -> throw new IllegalArgumentException("Invalid travel type: " + type);
    };
  }

  public abstract Itinerary createItinerary(String id, ArrayList<Hub> hubs, Calendar departureDate, Calendar arrivalDate, int price, Company company, Vehicle vehicle);

  public abstract Company createCompany(String id);

  public abstract Vehicle createVehicle(String id);

  public abstract Hub createHub(String id, String city);


  public TravelType getTravelType() {
    if (this instanceof SeaFactory) {
      return TravelType.SEA;
    } else if (this instanceof GroundFactory) {
      return TravelType.GROUND;
    } else if (this instanceof AirFactory) {
      return TravelType.AIR;
    } else {
      throw new IllegalStateException("Unknown travel factory type: " + this.getClass().getName());
    }
  }

  public abstract Section createSection(TravelClass travelclass, Layout layout, int nbSeats);
}