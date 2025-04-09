package itinerary;

import company.Company;
import hub.Hub;
import java.util.ArrayList;
import java.util.Calendar;
import vehicle.Vehicle;
import visitor.IVisitable;
import visitor.IVisitor;

public class Itinerary implements IVisitable {

  public String id;
  public int price;

  private Calendar departureDate;
  private Calendar arrivalDate;
  private boolean isAvailable;
  private ArrayList<Hub> hubs;
  private Company company;

  private Vehicle vehicle;


  public Itinerary(String id, int price, Calendar departureDate, Calendar arrivalDate,
      boolean isAvailable, ArrayList<Hub> hubs, Company company, Vehicle vehicle) {
    this.id = id;
    this.price = price;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.isAvailable = isAvailable;
    this.hubs = hubs;
    this.company = company;
    this.vehicle = vehicle;
  }

  public Itinerary getDetails() {
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Calendar getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(Calendar departureDate) {
    this.departureDate = departureDate;
  }

  public Calendar getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(Calendar arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public ArrayList<Hub> getHubs() {
    return hubs;
  }

  public void setHubs(ArrayList<Hub> hubs) {
    this.hubs = hubs;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  public Vehicle getVehicle() {
    return vehicle;
  }
}