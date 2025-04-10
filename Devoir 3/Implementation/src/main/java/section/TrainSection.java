package section;

import java.util.ArrayList;
import seating.Seating;

public class TrainSection extends Section {

  public TrainSection(TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
  }

  @Override
  public ArrayList<Seating> getSeatings() {
    return null;
  }


}