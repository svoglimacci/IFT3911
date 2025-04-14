package section;

import java.util.ArrayList;
import seating.Available;
import seating.PlaneSeat;
import seating.Seating;
import seating.TrainSeat;

public class TrainSection extends Section {

  private ArrayList<Seating> seatings;

  public TrainSection(TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
        this.seatings = createSeating(layout, nbSeats);
  }

    private ArrayList<Seating> createSeating(Layout layout, int nbSeats) {
    seatings = new ArrayList<>();
    int rows;
    int cols = 3;

    rows = nbSeats / cols;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        boolean isWindowSeat = (j == 0 || j == cols - 1);
        TrainSeat seat = new TrainSeat(i + 1, (char) ('A' + j), isWindowSeat);
        seatings.add(seat);
      }
    }

    return seatings;

  }

  @Override
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