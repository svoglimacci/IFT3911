package command;

import factory.AirFactory;
import factory.GroundFactory;
import factory.SeaFactory;
import repository.Repository;
import section.CruiseSection;
import section.Layout;
import section.PlaneSection;
import section.TrainSection;
import section.TravelClass;

public class CreateSectionCommand implements ICommand {

  private String vehicleId;
  private TravelClass travelClass;
  private Layout layout;
  private int nbSeats;

  private TravelType travelType;
  private Repository repository;

  public CreateSectionCommand(String vehicleId, String travelClass, Layout layout, int nbSeats, TravelType travelType, Repository repository) {
    this.vehicleId = vehicleId;
    this.travelClass = repository.getTravelClass(travelClass);
    this.layout = layout;
    this.nbSeats = nbSeats;
    this.repository = repository;
    this.travelType = travelType;
  }

  @Override
  public void execute() {
    if (travelType == TravelType.AIR) {
      AirFactory airFactory = AirFactory.getInstance();
      PlaneSection planeSection = airFactory.createSection(travelClass, layout, nbSeats);
      repository.createSection(vehicleId, planeSection);
    }

    if (travelType == TravelType.SEA) {
      SeaFactory seaFactory = SeaFactory.getInstance();
      CruiseSection cruiseSection = seaFactory.createSection(travelClass, layout, nbSeats);
      repository.createSection(vehicleId, cruiseSection);
    }

    if(travelType == TravelType.GROUND) {
      GroundFactory groundFactory = GroundFactory.getInstance();
      TrainSection trainSection = groundFactory.createSection(travelClass, layout, nbSeats);
      repository.createSection(vehicleId, trainSection);
    }
}

    @Override
  public void undo() {
    repository.deleteSection(vehicleId, travelClass.getId(), travelType);
  }




}