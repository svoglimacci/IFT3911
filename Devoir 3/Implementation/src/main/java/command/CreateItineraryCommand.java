package command;

import company.Company;
import factory.AirFactory;
import hub.Hub;
import itinerary.Flight;
import java.util.ArrayList;
import java.util.Calendar;
import repository.Repository;
import vehicle.Vehicle;

public class CreateItineraryCommand implements ICommand {

  private String id;
  private String companyId;
  private String vehicleId;
  private String[] hubs;
  private Calendar departureDate;
  private Calendar arrivalDate;
  private int price;
  private TravelType travelType;
  private Repository repository;


  public CreateItineraryCommand(String id, String companyId, String vehicleId, String[] hubs, Calendar departureDate, Calendar arrivalDate, int price, TravelType travelType, Repository repository) {

    this.id = id;
    this.companyId = companyId;
    this.vehicleId = vehicleId;
    this.hubs = hubs;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.price = price;
    this.travelType = travelType;
    this.repository = repository;
  }

  @Override
  public void execute() {
    if (travelType == TravelType.AIR) {
      AirFactory airFactory = AirFactory.getInstance();
      ArrayList<Hub> hubs = new ArrayList<>();

      // get hubs from repo
      for (String hubId : this.hubs) {
        Hub hub = repository.getHub(hubId);
        if (hub != null) {
          hubs.add(hub);
        }
      }

      // get company from repo
      Company company = repository.getCompany(companyId);
      Vehicle vehicle = repository.getVehicle(vehicleId);




      Flight flight = airFactory.createItinerary(id, hubs, departureDate, arrivalDate, price, company, vehicle);
      repository.addItinerary(flight, travelType);
    }
  }

  @Override
  public void undo() {
    repository.deleteItinerary(id);
  }
}