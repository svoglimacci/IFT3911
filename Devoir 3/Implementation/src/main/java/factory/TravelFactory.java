package factory;


import command.TravelType;
import company.Company;
import section.Section;
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

  public abstract Itinerary createItinerary();

  public abstract Company createCompany(String id);

  public abstract Vehicle createVehicle();

  public abstract Hub createHub();

  public abstract Section createSection();

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
}