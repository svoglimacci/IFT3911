package command;

import company.Airline;
import company.CruiseLine;
import company.Railway;
import factory.AirFactory;
import factory.GroundFactory;
import factory.SeaFactory;
import repository.Repository;
import vehicle.CruiseShip;
import vehicle.Train;

public class CreateCompanyCommand implements ICommand {
   private String id;
  private TravelType travelType;
  private Repository repository;

  public CreateCompanyCommand(String id, TravelType travelType, Repository repository) {
    this.id = id;
    this.travelType = travelType;
    this.repository = repository;
  }


  @Override
  public void execute() {

    if (travelType == TravelType.AIR) {
      AirFactory airFactory = AirFactory.getInstance();
      Airline airline = airFactory.createCompany(id);
      repository.addCompany(airline, travelType);
    }

    if (travelType == TravelType.SEA) {
      SeaFactory seaFactory = SeaFactory.getInstance();
      CruiseLine cruiseLine = seaFactory.createCompany(id);
      repository.addCompany(cruiseLine, travelType);

    }

    if (travelType == TravelType.GROUND) {
      GroundFactory groundFactory = GroundFactory.getInstance();
      Railway railway = groundFactory.createCompany(id);
      repository.addCompany(railway, travelType);

    }
  }

  @Override
  public void undo() {
      repository.deleteCompany(id);
  }
}