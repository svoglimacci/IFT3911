package section;

import java.util.ArrayList;
import seating.Available;
import seating.PlaneSeat;
import seating.Seating;

public class PlaneSection extends Section {

  private ArrayList<Seating> seatings;




  public PlaneSection(TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
    this.seatings = createSeating(layout, nbSeats);

  }
  private ArrayList<Seating> createSeating(Layout layout, int nbSeats) {
    seatings = new ArrayList<>();
    int rows;
    int cols;

    switch (layout) {
      case NARROW -> cols = 3;
      case COMFORT -> cols = 4;
      case MEDIUM -> cols = 6;
      case LARGE -> cols = 10;
      default -> throw new IllegalArgumentException("Invalid layout");
    }
    rows = nbSeats / cols;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        boolean isWindowSeat = (j == 0 || j == cols - 1);
        PlaneSeat seat = new PlaneSeat(i + 1, (char) ('A' + j), isWindowSeat);
        seatings.add(seat);
      }
    }

    return seatings;
  }


  public ArrayList<Seating> getSeatings() {
    return seatings;
  }

  public ArrayList<Seating> getAvailableSeats() {
    ArrayList<Seating> availableSeats = new ArrayList<>();
    for (Seating seating : seatings) {
      if (seating.getState() instanceof Available) {
        availableSeats.add(seating);
      }
    }
    return availableSeats;
  }



}