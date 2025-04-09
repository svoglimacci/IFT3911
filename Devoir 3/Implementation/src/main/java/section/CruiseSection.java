package section;

import java.util.ArrayList;
import seating.PlaneSeat;

public class CruiseSection extends Section {

  public CruiseSection( TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
  }

  @Override
  public ArrayList<PlaneSeat> getSeatings() {
    return null;
  }


}