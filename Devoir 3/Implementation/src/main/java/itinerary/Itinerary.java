package itinerary;

import company.Company;
import hub.Hub;
import java.util.ArrayList;
import java.util.Calendar;
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



  public Itinerary(String id, int price, Calendar departureDate, Calendar arrivalDate,
      boolean isAvailable, ArrayList<Hub> hubs, Company company) {
    this.id = id;
    this.price = price;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.isAvailable = isAvailable;
    this.hubs = hubs;
    this.company = company;
  }

  public Itinerary getDetails() {
    return this;
  }

  public String getId() {
    return id;
  }

  public int getPrice() {
    return price;
  }

  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

}