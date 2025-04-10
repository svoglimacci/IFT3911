package visitor;

import hub.Hub;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.Section;
import vehicle.Vehicle;

public class AdminTripVisitor implements IVisitor{

  @Override
  public void visit(Itinerary itinerary) {
     // YUL-YYZ:[AIRCAN]AC481(2014.11.28:06.00-2014.11.28:07:24)|PS(0/12)474.00|AM(5/16)355.50|EL(150/200)237.00

    StringBuilder sb = new StringBuilder();

    // YUL-YYZ
    for (Hub hub : itinerary.getHubs()) {
      sb.append(hub.getId()).append("-");
    }
    sb.deleteCharAt(sb.length() - 1);

   // :[AIRCAN]
    sb.append(":[").append(itinerary.getCompany().getId()).append("]");

    // AC481
    sb.append(itinerary.getVehicle().getId());

    // (2014.11.28:06.00-2014.11.28:07:24)
    String departureDate = itinerary.getDepartureDate().get(Calendar.YEAR) + "." + (itinerary.getDepartureDate()
        .get(Calendar.MONTH)) + "." + itinerary.getDepartureDate().get(
        Calendar.DAY_OF_MONTH) + ":" + itinerary.getDepartureDate().get(Calendar.HOUR_OF_DAY) + "." + itinerary.getDepartureDate().get(Calendar.MINUTE);
    String arrivalDate = itinerary.getArrivalDate().get(Calendar.YEAR) + "." + (itinerary.getArrivalDate().get(Calendar.MONTH)) + "." + itinerary.getArrivalDate().get(
        Calendar.DAY_OF_MONTH) + ":" + itinerary.getArrivalDate().get(Calendar.HOUR_OF_DAY) + "." + itinerary.getArrivalDate().get(Calendar.MINUTE);

    sb.append("(").append(departureDate).append("-").append(arrivalDate).append(")");

    // |PS(0/12)474.00|AM(5/16)355.50|EL(150/200)237.00
    Vehicle vehicle = itinerary.getVehicle();
    ArrayList<Section> sections = vehicle.getSections();

    sb.append("|");
    for (Section section : sections) {
      double price = section.getRate() * itinerary.getPrice();
      sb.append(section.getTravelClass().getId()).append(section.getLayout().getCode()).append("(").append(section.getAvailableSeats().size()).append("/").append(section.getSeatings().size()).append(")").append(price).append("|");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.println(sb.toString());



  }
}