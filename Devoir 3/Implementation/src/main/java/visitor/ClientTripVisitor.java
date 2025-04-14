package visitor;

import hub.Hub;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.Section;
import vehicle.Vehicle;

public class ClientTripVisitor implements IVisitor{

  @Override
  public void visit(Itinerary itinerary) {


    StringBuilder sb = new StringBuilder();


    for (Hub hub : itinerary.getHubs()) {
      sb.append(hub.getId()).append("-");
    }
    sb.deleteCharAt(sb.length() - 1);


    sb.append(":[").append(itinerary.getCompany().getId()).append("]");


    sb.append(itinerary.getVehicle().getId());


    String departureDate = itinerary.getDepartureDate().get(Calendar.YEAR) + "." + (itinerary.getDepartureDate()
        .get(Calendar.MONTH)) + "." + itinerary.getDepartureDate().get(
        Calendar.DAY_OF_MONTH) + ":" + itinerary.getDepartureDate().get(Calendar.HOUR_OF_DAY) + "." + itinerary.getDepartureDate().get(Calendar.MINUTE);
    String arrivalDate = itinerary.getArrivalDate().get(Calendar.YEAR) + "." + (itinerary.getArrivalDate().get(Calendar.MONTH)) + "." + itinerary.getArrivalDate().get(
        Calendar.DAY_OF_MONTH) + ":" + itinerary.getArrivalDate().get(Calendar.HOUR_OF_DAY) + "." + itinerary.getArrivalDate().get(Calendar.MINUTE);

    sb.append("(").append(departureDate).append("-").append(arrivalDate).append(")");


    Vehicle vehicle = itinerary.getVehicle();
    ArrayList<Section> sections = vehicle.getSections();

    sb.append("|");
    for (Section section : sections) {
      double price = section.getRate() * itinerary.getPrice();
      sb.append(price);
            sb.append("|");
      sb.append(section.getTravelClass().getId());
      sb.append(section.getAvailableSeats().size());
          sb.append("|");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.println(sb.toString());



  }
}