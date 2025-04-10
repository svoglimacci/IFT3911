package command;

import repository.Repository;

public class AssignPricesCommand implements ICommand{

  private String id;
  private int price;

  private TravelType travelType;
  private Repository repository;

  public AssignPricesCommand(String id, int price, TravelType travelType, Repository repository ) {
    this.id = id;
    this.price = price;
    this.repository = repository;
    this.travelType = travelType;
  }

  @Override
  public void execute() {
    repository.assignPrice(id, price, travelType);
  }

}