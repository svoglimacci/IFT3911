package section;

import java.util.ArrayList;
import seating.PlaneSeat;

public class PlaneSection extends Section {

  private ArrayList<PlaneSeat> seatings;




  public PlaneSection(TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
    this.seatings = createSeating(layout);

  }
  private ArrayList<PlaneSeat> createSeating(Layout layout) {
    seatings = new ArrayList<>();
    int rows = 100;
    int cols;

    // Determine the number of columns based on the layout
    switch (layout) {
      case NARROW -> cols = 3;
      case COMFORT -> cols = 4;
      case MEDIUM -> cols = 6;
      case LARGE -> cols = 10;
      default -> throw new IllegalArgumentException("Invalid layout");
    }

    // Generate seats
    for (int row = 1; row <= rows; row++) {
      for (int col = 1; col <= cols; col++) {
        char columnLetter = (char) ('A' + col - 1);

        // aisles
        if ((layout == Layout.NARROW && col == 2) ||
            (layout == Layout.COMFORT && col == 3) ||
            (layout == Layout.MEDIUM && col == 4) ||
            (layout == Layout.LARGE && (col == 4 || col == 8))) {
          continue;
        }

        // Add seat to the list
        seatings.add(new PlaneSeat(row, columnLetter));
      }
    }

    return seatings;
  }

  public void setSeatings(ArrayList<PlaneSeat> seatings) {
    this.seatings = seatings;
  }

  @Override
  public ArrayList<PlaneSeat> getSeatings() {
    return seatings;
  }



}