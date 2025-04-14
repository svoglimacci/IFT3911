package command;

import company.Company;
import factory.AirFactory;
import factory.GroundFactory;
import factory.SeaFactory;
import hub.Hub;
import itinerary.Cruise;
import itinerary.Flight;
import itinerary.TrainRide;
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

      for (String hubId : this.hubs) {
        Hub hub = repository.getHub(hubId);
        if (hub != null) {
          hubs.add(hub);
        }
      }

      Company company = repository.getCompany(companyId);
      Vehicle vehicle = repository.getVehicle(vehicleId);




      Flight flight = airFactory.createItinerary(id, hubs, departureDate, arrivalDate, price, company, vehicle);
      repository.addItinerary(flight);
    }

    if (travelType == TravelType.SEA) {
      SeaFactory seaFactory = SeaFactory.getInstance();
      ArrayList<Hub> hubs = new ArrayList<>();

      for (String hubId : this.hubs) {
        Hub hub = repository.getHub(hubId);
        if (hub != null) {
          hubs.add(hub);
        }
      }

      Company company = repository.getCompany(companyId);
      Vehicle vehicle = repository.getVehicle(vehicleId);

      Cruise cruise = seaFactory.createItinerary(id, hubs, departureDate, arrivalDate, price, company, vehicle);
      repository.addItinerary(cruise);

    }

    if (travelType == TravelType.GROUND) {
      GroundFactory groundFactory = GroundFactory.getInstance();
      ArrayList<Hub> hubs = new ArrayList<>();

      for (String hubId : this.hubs) {
        Hub hub = repository.getHub(hubId);
        if (hub != null) {
          hubs.add(hub);
        }
      }

      Company company = repository.getCompany(companyId);
      Vehicle vehicle = repository.getVehicle(vehicleId);
      TrainRide trainRide = groundFactory.createItinerary(id, hubs, departureDate, arrivalDate, price, company, vehicle);
      repository.addItinerary(trainRide);
    }
  }

  @Override
  public void undo() {
    repository.deleteItinerary(id);
  }
}