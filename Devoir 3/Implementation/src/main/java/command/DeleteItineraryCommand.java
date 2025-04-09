package command;

import repository.Repository;
import itinerary.Itinerary;

public class DeleteItineraryCommand implements ICommand {

  private String id;
  private Repository repository;

  public DeleteItineraryCommand(String id, Repository repository) {
    this.id = id;
    this.repository = repository;
  }

  @Override
  public void execute() {
    repository.deleteItinerary(id);
  }

}