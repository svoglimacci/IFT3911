package command;

import company.Airline;
import factory.AirFactory;
import repository.Repository;

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
  }

  @Override
  public void undo() {
      repository.deleteCompany(id);
  }
}