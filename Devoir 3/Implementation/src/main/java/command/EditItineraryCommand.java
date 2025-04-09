package command;

import java.util.Calendar;
import repository.Repository;



public class EditItineraryCommand implements ICommand {
   private String id;
  private String newId;
  private String newCompany;
  private String[] newHubs;
  private Calendar newDepartureDate;
  private Calendar newArrivalDate;
  private int newPrice;

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
      repository.updateItinerary(id, newId, newCompany, newHubs, newDepartureDate, newArrivalDate, newPrice);
    }
  }