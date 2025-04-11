package controller;

import command.AssignPricesCommand;
import command.CreateCompanyCommand;
import command.CreateHubCommand;
import command.CreateItineraryCommand;
import command.CreateSectionCommand;
import command.DeleteCompanyCommand;
import command.DeleteHubCommand;
import command.DeleteItineraryCommand;
import command.EditCompanyCommand;
import command.EditHubCommand;
import command.EditItineraryCommand;
import command.Invoker;
import command.TravelType;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import repository.Repository;
import section.Layout;

public class AdminController {

  private final Repository repository;
  private final Invoker invoker;

  public AdminController(Repository repository) {
    this.repository = repository;
    this.invoker = new Invoker();
  }

  public boolean undo() {
    return invoker.undo();
  }



  public boolean createCompany(String id, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      CreateCompanyCommand createCompanyCommand = new CreateCompanyCommand(id, travelType,
          repository);
      invoker.executeCommand(createCompanyCommand);
      return true;
    }
    return false;
  }

  public boolean createHub(String id, String city, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      CreateHubCommand createHubCommand = new CreateHubCommand(id, city, travelType, repository);
      invoker.executeCommand(createHubCommand);
      return true;
    }
    return false;
  }


  public boolean editCompany(String id, String newId) {
    EditCompanyCommand editCompanyCommand = new EditCompanyCommand(id, newId, repository);
    invoker.executeCommand(editCompanyCommand);
    return true;
  }

  public boolean editHub(String id, String newId, String newCity) {
    EditHubCommand editHubCommand = new EditHubCommand(id, newId, newCity, repository);
    invoker.executeCommand(editHubCommand);
    return true;
  }

  public boolean deleteCompany(String id) {
    DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(id, repository);
    invoker.executeCommand(deleteCompanyCommand);
    return true;
  }

  public boolean deleteHub(String id) {
    DeleteHubCommand deleteHubCommand = new DeleteHubCommand(id, repository);
    invoker.executeCommand(deleteHubCommand);
    return true;
  }

  public boolean createItinerary(String id, String companyId, String vehicleId, String[] hubs, Calendar departureDate,
      Calendar arrivalDate, int price, TravelType travelType) {
    CreateItineraryCommand createItineraryCommand = new CreateItineraryCommand(id, companyId, vehicleId, hubs,
        departureDate, arrivalDate, price, travelType, repository);
    invoker.executeCommand(createItineraryCommand);
    return true;
  }

  public boolean editItinerary(String id, String newId, String newCompany, String[] newHubs,
      Calendar newDepartureDate, Calendar newArrivalDate, int newPrice) {
    EditItineraryCommand editItineraryCommand = new EditItineraryCommand(id, newId, newCompany,
        newHubs, newDepartureDate, newArrivalDate, newPrice, repository);
    invoker.executeCommand(editItineraryCommand);
    return true;
  }

  public boolean deleteItinerary(String id) {
    DeleteItineraryCommand deleteItineraryCommand = new DeleteItineraryCommand(id, repository);
    invoker.executeCommand(deleteItineraryCommand);
    return true;
  }

  public boolean createSection(String vehicleId, String travelClass, Layout layout, int nbSeats,
      TravelType travelType) {
    CreateSectionCommand createSectionCommand = new CreateSectionCommand(vehicleId, travelClass,
        layout, nbSeats, travelType, repository);
    invoker.executeCommand(createSectionCommand);
    return true;
  }

  public ArrayList<Itinerary> displayItineraries(TravelType travelType) {
    return repository.getItineraries(travelType);
  }

  public boolean assignPrices(String itineraryId, int price, TravelType travelType) {
    AssignPricesCommand assignPricesCommand = new AssignPricesCommand(itineraryId, price, travelType, repository);
    invoker.executeCommand(assignPricesCommand);
    return true;
  }

  public void save() {
    invoker.save(repository);
  }

  public void restore() {
    invoker.restore(repository);
  }

}