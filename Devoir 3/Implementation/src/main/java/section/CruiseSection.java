package section;

import itinerary.Cruise;
import java.util.ArrayList;
import seating.Available;
import seating.Cabin;
import seating.PlaneSeat;
import seating.Seating;

public class CruiseSection extends Section {

  private ArrayList<Seating> seatings;

  public CruiseSection( TravelClass travelClass, Layout layout, int nbSeats) {
    super(travelClass, layout, nbSeats);
    this.seatings = createSeating(travelClass, nbSeats);
  }

    private ArrayList<Seating> createSeating(TravelClass travelClass, int nbSeats) {
    seatings = new ArrayList<>();

      int nbCabins;
      int capacity;
      char travelClassId = travelClass.getId().charAt(0);

      switch (travelClassId) {
        case 'I' -> capacity = 4;
        case 'O' -> capacity = 2;
        case 'S' -> capacity = 5;
        case 'F', 'D' -> capacity = 6;
        default -> throw new IllegalArgumentException("Invalid travel class");
      }
      nbCabins = nbSeats / capacity;

      for (int i = 0; i < nbCabins; i++) {
        Cabin cabin = new Cabin(i + 1, travelClassId, capacity);
        seatings.add(cabin);
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