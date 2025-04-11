package command;

import itinerary.Itinerary;
import repository.Repository;

public class AssignPricesCommand implements ICommand{

  private String id;
  private int price;

  private int oldPrice;

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
    this.oldPrice = repository.getItinerary(id).getPrice();
    repository.assignPrice(id, price);
  }

  @Override
  public void undo() {
    repository.assignPrice(id, oldPrice);

  }

}