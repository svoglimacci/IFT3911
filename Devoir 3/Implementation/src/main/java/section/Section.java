package section;

import java.util.ArrayList;
import seating.Available;
import seating.Seating;

public class Section {

  private TravelClass travelClass;
  private Layout layout;
  private ArrayList<Seating> seatings;

  public double getRate() {
    return travelClass.getRate();
  }

  public TravelClass getTravelClass() {
    return travelClass;
  }

  public boolean addSeating(Seating seating) {
    if (seating != null) {
      seatings.add(seating);
      return true;
    }
    return false;
  }

  public boolean deleteSeating(Seating seating) {
    if (seating != null && seatings.contains(seating)) {
      seatings.remove(seating);
      return true;
    }
    return false;
  }

  public ArrayList<Seating> getSeating() {
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