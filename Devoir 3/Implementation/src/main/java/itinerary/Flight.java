package itinerary;
import company.Company;
import hub.Hub;
import java.util.ArrayList;
import java.util.Calendar;

public class Flight extends Itinerary {


  public Flight(String id, int price, Calendar departureDate, Calendar arrivalDate,
      boolean isAvailable, ArrayList<Hub> hubs, Company company) {
    super(id, price, departureDate, arrivalDate, isAvailable, hubs, company);
  }
}