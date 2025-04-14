package command;

import itinerary.Cruise;
import itinerary.Flight;
import repository.Repository;
import itinerary.Itinerary;

public class DeleteItineraryCommand implements ICommand {

  private String id;
  private Repository repository;
  private Itinerary oldItinerary;

  public DeleteItineraryCommand(String id, Repository repository) {
    this.id = id;
    this.repository = repository;

  }

  @Override
  public void execute() {
    this.oldItinerary = repository.getItinerary(id);
    repository.deleteItinerary(id);
  }

    @Override
  public void undo() {
    if (oldItinerary != null) {

      repository.addItinerary(oldItinerary);
    }
  }

}