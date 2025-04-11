package command;

import java.util.Calendar;
import repository.Repository;



public class EditItineraryCommand implements ICommand {
   private String id;
  private String newId;
  private String newCompany;
  private String oldCompany;
  private String[] newHubs;
  private String[] oldHubs;
  private Calendar newDepartureDate;
  private Calendar oldDepartureDate;
  private Calendar newArrivalDate;
  private Calendar oldArrivalDate;
  private int newPrice;
  private int oldPrice;

  private Repository repository;

  public EditItineraryCommand(String id, String newId, String newCompany, String[] newHubs, Calendar newDepartureDate, Calendar newArrivalDate, int newPrice, Repository repository) {
    this.id = id;
    this.newId = newId;
    this.newCompany = newCompany;
    this.newHubs = newHubs;
    this.newDepartureDate = newDepartureDate;
    this.newArrivalDate = newArrivalDate;
    this.newPrice = newPrice;
    this.repository = repository;
  }

  @Override
  public void execute() {
    this.oldCompany = repository.getItinerary(id).getCompany().getId();
    this.oldHubs = new String[repository.getItinerary(id).getHubs().size()];
    for (int i = 0; i < repository.getItinerary(id).getHubs().size(); i++) {
      this.oldHubs[i] = repository.getItinerary(id).getHubs().get(i).getId();
    }

    this.oldDepartureDate = repository.getItinerary(id).getDepartureDate();
    this.oldArrivalDate = repository.getItinerary(id).getArrivalDate();
    this.oldPrice = repository.getItinerary(id).getPrice();

      repository.updateItinerary(id, newId, newCompany, newHubs, newDepartureDate, newArrivalDate, newPrice);
    }

  @Override
  public void undo() {
    repository.updateItinerary(newId, id, oldCompany, oldHubs, oldDepartureDate, oldArrivalDate,
        oldPrice);
  }
  }